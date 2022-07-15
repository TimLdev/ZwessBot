package dev.tim.discordbot.listeners;

import dev.tim.discordbot.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MemberLeaveListener extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        String accountCreated = event.getMember().getTimeCreated().getDayOfMonth() + "/" + event.getMember().getTimeCreated().getMonthValue() + "/" + event.getMember().getTimeCreated().getYear();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        EmbedBuilder logEmbed = new EmbedBuilder();
        logEmbed.setColor(Color.RED);
        logEmbed.setTitle("Gebruiker geleaved");
        logEmbed.addField("Geleaved", event.getMember().getAsMention() + " (`" + event.getMember().getId() + "`)", false);
        logEmbed.addField("Geleaved op", dtf.format(now), false);
        logEmbed.addField("Account gemaakt", accountCreated, false);
        logEmbed.setThumbnail(event.getMember().getUser().getEffectiveAvatarUrl());
        event.getGuild().getTextChannelById(Utils.LOGS_CHANNEL_ID).sendMessageEmbeds(logEmbed.build()).queue();
    }

}
