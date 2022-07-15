package dev.tim.discordbot.commands.info;

import dev.tim.discordbot.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class BotCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(event.getName().equalsIgnoreCase("bot")){

            String zwess = event.getGuild().getMemberById(Utils.ZWESS_MEMBER_ID).getAsMention();

            event.deferReply().queue();
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(new Color(242, 127, 255));
            embed.setTitle("Bot Informatie");
            embed.addField("Developer", zwess, false);
            embed.addField("Hosting", "https://feroxhosting.nl", false);
            embed.addField("Gemaakt met", "Java (JDA)", false);
            embed.addField("Gemaakt op", "12/06/2022", false);
            embed.addField("Online gegaan op", "12/07/2022", false);
            embed.addField("Prefix", "Slash (/)", false);
            embed.addField("Ping", String.valueOf(event.getJDA().getGatewayPing()), false);
            embed.setFooter("Zwess Community", event.getGuild().getIconUrl());
            embed.setThumbnail(event.getGuild().getSelfMember().getEffectiveAvatarUrl());

            event.getHook().sendMessageEmbeds(embed.build()).queue();

        }
    }

}
