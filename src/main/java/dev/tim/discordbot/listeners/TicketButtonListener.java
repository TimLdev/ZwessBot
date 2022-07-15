package dev.tim.discordbot.listeners;

import dev.tim.discordbot.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;

public class TicketButtonListener extends ListenerAdapter {

    public void onButtonInteraction(ButtonInteractionEvent event) {

        event.deferEdit().queue();
        if (event.getButton().getId().equals("openTicket")) {
            int min = 1000;
            int max = 99999;
            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();
            Guild guild = event.getGuild();
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.GREEN);
            embed.setTitle(event.getUser().getName() + "'s Ticket");
            embed.setDescription("Een staff-lid helpt je zo snel mogelijk");
            guild.createTextChannel("ticket-" + random_int, guild.getCategoryById(Utils.TICKET_CATEGORY_ID))
                    .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .addPermissionOverride(guild.getRoleById(Utils.TICKET_SUPPORT_ROLE_ID), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_HISTORY), null)
                    .complete().sendMessageEmbeds(embed.build()).setActionRow(closeButton()).queue();
            EmbedBuilder embedLog = new EmbedBuilder();
            embedLog.setColor(new Color(255, 255, 255));
            embedLog.setTitle("Ticket Geopend");
            embedLog.addField("Gebruiker", event.getMember().getAsMention(), true);
            embedLog.addField("Datum", formatter.format(date), true);
            embedLog.setThumbnail(event.getMember().getEffectiveAvatarUrl());
            guild.getTextChannelById(Utils.LOGS_CHANNEL_ID).sendMessageEmbeds(embedLog.build()).queue();
        } else if (event.getButton().getId().equals("closeButton")) {
            event.getInteraction().getChannel().delete().queue();
        }
    }

    private Button closeButton() {
        return Button.danger("closeButton", "Sluiten");
    }

}
