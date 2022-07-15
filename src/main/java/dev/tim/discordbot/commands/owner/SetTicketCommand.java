package dev.tim.discordbot.commands.owner;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class SetTicketCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(event.getName().equalsIgnoreCase("setticket")){
            if(event.getMember().isOwner()){
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.GREEN);
                embed.setTitle("Ticket Openen");
                embed.setDescription("Klik op de knop om een ticket te openen");
                event.getChannel().sendMessageEmbeds(embed.build()).setActionRow(Button.success("openTicket", "Open Ticket").withEmoji(Emoji.fromMarkdown("\uD83C\uDFAB"))).queue();
                event.reply("Ticket embed gezet!").setEphemeral(true).queue();
            } else {
                event.reply("`❌` Je hebt geen permissie voor dit command!").setEphemeral(true).queue();
            }
        }
    }
}
