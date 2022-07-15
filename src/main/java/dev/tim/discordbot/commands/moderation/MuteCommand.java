package dev.tim.discordbot.commands.moderation;

import dev.tim.discordbot.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MuteCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(event.getName().equalsIgnoreCase("mute")) {

            OptionMapping option1 = event.getOption("gebruiker");
            OptionMapping option2 = event.getOption("minuten");
            OptionMapping option3 = event.getOption("reden");

            if (event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {

                if (option1 == null || option2 == null || option3 == null) {
                    event.reply("Er is iets fout gegaan bij dit command!").setEphemeral(true).queue();
                    return;
                }

                Member member = option1.getAsMember();
                long minutes = option2.getAsInt();
                String reason = option3.getAsString();
                Role role = event.getGuild().getRoleById(Utils.MUTED_ROLE_ID);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                if(!member.getRoles().contains(role)){

                    event.getGuild().addRoleToMember(member, role).queue();
                    event.reply("`✅` **Gebruiker succesvol gemute!**").setEphemeral(true).queue();

                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setColor(new Color(255, 241, 0));
                    embed.setTitle("Gebruiker gemute!");
                    embed.setDescription(member.getAsMention() + " is succesvol gemute!");
                    embed.addField("Gemute door", event.getMember().getAsMention(), false);
                    embed.addField("Duur", minutes + " minuten", false);
                    embed.addField("Reden", reason, false);
                    embed.setThumbnail(member.getEffectiveAvatarUrl());
                    embed.setFooter("Zwess Community", event.getGuild().getIconUrl());
                    event.getChannel().sendMessageEmbeds(embed.build()).queue();

                    // LOGS EMBED
                    EmbedBuilder logEmbed = new EmbedBuilder();
                    logEmbed.setColor(new Color(255, 241, 0));
                    logEmbed.setTitle("Gebruiker gemute!");
                    logEmbed.addField("Gemute", member.getAsMention() + " (`" + member.getId() + "`)", false);
                    logEmbed.addField("Gemute door", event.getMember().getAsMention(), false);
                    logEmbed.addField("Duur", minutes + " minuten", false);
                    logEmbed.addField("Reden", reason, false);
                    logEmbed.addField("Datum", dtf.format(now), false);
                    logEmbed.setThumbnail(member.getEffectiveAvatarUrl());
                    event.getGuild().getTextChannelById(Utils.LOGS_CHANNEL_ID).sendMessageEmbeds(logEmbed.build()).queue();

                    new java.util.Timer().schedule(
                            new java.util.TimerTask(){
                                @Override
                                public void run(){
                                    event.getGuild().removeRoleFromMember(member, role).queue();

                                    EmbedBuilder logEmbed = new EmbedBuilder();
                                    logEmbed.setColor(new Color(138, 126, 0));
                                    logEmbed.setTitle("Gebruiker unmuted!");
                                    logEmbed.addField("Unmuted", member.getAsMention() + " (`" + member.getId() + "`)", false);
                                    logEmbed.addField("Gemute door", event.getMember().getAsMention(), false);
                                    logEmbed.addField("Duur van mute", minutes + " minuten", false);
                                    logEmbed.addField("Reden", reason, false);
                                    logEmbed.addField("Datum van mute", dtf.format(now), false);
                                    logEmbed.setThumbnail(member.getEffectiveAvatarUrl());
                                    event.getGuild().getTextChannelById(Utils.LOGS_CHANNEL_ID).sendMessageEmbeds(logEmbed.build()).queue();

                                }
                            },
                            minutes * 1000 * 60
                    );
                } else {
                    event.reply("`❌` Deze gebruiker is al gemute!").setEphemeral(true).queue();
                }

            } else {
                event.reply("`❌` Je hebt geen permissie voor dit command!").setEphemeral(true).queue();
            }
        }
    }
}
