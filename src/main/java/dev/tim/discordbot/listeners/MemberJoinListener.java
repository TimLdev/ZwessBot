package dev.tim.discordbot.listeners;

import dev.tim.discordbot.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class MemberJoinListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        String accountCreated = event.getMember().getTimeCreated().getDayOfMonth() + "/" + event.getMember().getTimeCreated().getMonthValue() + "/" + event.getMember().getTimeCreated().getYear();
        String joinedDate = event.getMember().getTimeJoined().getDayOfMonth() + "/" + event.getMember().getTimeJoined().getMonthValue() + "/" + event.getMember().getTimeJoined().getYear();
        String infoChannel = event.getGuild().getTextChannelById(Utils.INFORMATION_CHANNEL_ID).getAsMention();

        event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(Utils.MEMBER_ROLE_ID)).queue();

        EmbedBuilder logEmbed = new EmbedBuilder();
        logEmbed.setColor(Color.GREEN);
        logEmbed.setTitle("Gebruiker gejoined");
        logEmbed.addField("Gejoined", event.getMember().getAsMention() + " (`" + event.getMember().getId() + "`)", false);
        logEmbed.addField("Account gemaakt", accountCreated, false);
        logEmbed.addField("Gejoined op", joinedDate, false);
        logEmbed.addField("Discord members", event.getGuild().getMemberCount() + " members", false);
        logEmbed.setThumbnail(event.getMember().getUser().getEffectiveAvatarUrl());
        event.getGuild().getTextChannelById(Utils.LOGS_CHANNEL_ID).sendMessageEmbeds(logEmbed.build()).queue();

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(new Color(135, 255, 60));
        embed.setTitle("```\uD83D\uDC4B``` **| Welkom " + event.getMember().getEffectiveName() + "**");
        embed.setDescription("Leuk dat je bent gejoined " + event.getMember().getAsMention() + "!\nVeel plezier en we hopen dat je het naar je zin zult hebben!\nVoor informatie over de server kan je naar " + infoChannel + ".\n\nEr zitten nu **" + event.getGuild().getMemberCount() + "** gebruikers in deze server.");
        embed.setThumbnail(event.getMember().getUser().getEffectiveAvatarUrl());
        embed.setFooter("Zwess Community", event.getGuild().getIconUrl());
        event.getGuild().getTextChannelById(Utils.WELCOME_CHANNEL_ID).sendMessageEmbeds(embed.build()).queue();
    }

}
