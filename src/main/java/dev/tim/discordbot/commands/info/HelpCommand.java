package dev.tim.discordbot.commands.info;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class HelpCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equalsIgnoreCase("help")) {
            event.deferReply().queue();

            OptionMapping option = event.getOption("pagina");

            if (option == null) {
                event.reply("`❌` Je hebt geen pagina meegegeven!").setEphemeral(true).queue();
                return;
            }

            int pagina = option.getAsInt();
            Guild guild = event.getGuild();

            if(pagina == 1){
                event.getHook().sendMessageEmbeds(page1(guild).build()).queue();
            } else {
                event.getHook().sendMessageEmbeds(page2(guild).build()).queue();
            }
        }
    }

    public EmbedBuilder page1(Guild guild){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(new Color(255, 234, 0, 255));
        embed.setTitle("**Lijst van commands (Pagina 1/2)**");
        embed.setFooter("Zwess Community", guild.getIconUrl());

        embed.addField("", "\uD83D\uDCCB **| INFORMATIE**", false);
        embed.addField("`/help`", "Krijg alle commands te zien", true);
        embed.addField("`/bot`", "Krijg informatie over de bot", true);
        embed.addField("`/code`", "Krijg informatie over hoe je code post", true);
        embed.addField("`/youtube`", "Krijg informatie over de YouTube van Zwess", true);
        embed.addField("", "\uD83C\uDF75 **| ALGEMEEN**", false);
        embed.addField("`/suggest`", "Plaats een suggestie voor Zwess of de server", true);
        embed.addField("", "\uD83C\uDFB2 **| PLEZIER**", false);
        embed.addField("`/meme`", "Krijg een leuke meme te zien", true);
        embed.addField("`/joke`", "Krijg een leuke grap te zien", true);
        embed.addField("`/sps`", "Speel steen, papier, schaar tegen de bot", true);
        embed.addField("", "\uD83C\uDFB5 **| MUZIEK**", false);
        embed.addField("`/join`", "Laat de bot jouw voice channel betreden", true);
        embed.addField("`/play`", "Speel een nummer af", true);
        embed.addField("`/stop`", "Stop de muziek", true);
        embed.addField("`/skip`", "Skip het huidige nummer", true);
        embed.addField("`/nowplaying`", "Bekijk welk nummer nu afspeelt", true);
        embed.addField("`/queue`", "Bekijk de nummers in de wachtrij", true);
        embed.addField("`/repeat`", "Herhaal het huidige nummer", true);
        embed.addField("`/leave`", "Laat de bot de voice channel verlaten", true);
        embed.addField("", "", false);
        return embed;
    }

    public EmbedBuilder page2(Guild guild){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(new Color(255, 234, 0, 255));
        embed.setTitle("**Lijst van commands (Pagina 2/2)**");
        embed.setFooter("Zwess Community", guild.getIconUrl());


        embed.addField("", "\uD83D\uDEE0 **| MODERATIE**", false);
        embed.addField("`/clear`", "Verwijder een aantal berichten", true);
        embed.addField("`/kick`", "Kick een gebruiker van de server", true);
        embed.addField("`/ban`", "Ban een gebruiker van de server", true);
        embed.addField("`/mute`", "Mute een gebruiker", true);
        embed.addField("", "\uD83D\uDC51 **| EIGENAAR**", false);
        embed.addField("`/setticket`", "Zet de ticket embed", true);
        embed.addField("`/setrules`", "Zet de regels embed", true);
        embed.addField("", "", false);
        return embed;
    }

}
