package dev.tim.discordbot.managers;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandManager extends ListenerAdapter {

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList();

        commandData.add(Commands.slash("clear", "Verwijder een aantal berichten")
                .addOptions(new OptionData(OptionType.INTEGER, "aantal", "Het aantal berichten dat je verwijderd", true)
                        .setRequiredRange(1, 100)));
        commandData.add(Commands.slash("code", "Krijg informatie over hoe je je code correct kunt posten"));
        commandData.add(Commands.slash("suggest", "Plaats een suggestie in het suggestie kanaal")
                .addOption(OptionType.STRING, "suggestie", "Het suggestie bericht", true));
        commandData.add(Commands.slash("bot", "Krijg informatie over de bot"));
        commandData.add(Commands.slash("setticket", "Zet de ticket embed"));
        commandData.add(Commands.slash("kick", "Kick een gebruiker van de server")
                .addOption(OptionType.USER, "gebruiker", "De gebruiker die je wilt kicken", true)
                .addOption(OptionType.STRING, "reden", "De reden van de kick", true));
        commandData.add(Commands.slash("youtube", "Krijg informatie over het YouTube kanaal van Zwess"));
        commandData.add(Commands.slash("help", "Krijg alle commands te zien")
                .addOptions(new OptionData(OptionType.INTEGER, "pagina", "Kies uit pagina 1 t/m 2", true)
                        .setRequiredRange(1, 2)));
        commandData.add(Commands.slash("ban", "Ban een gebruiker van de server")
                .addOption(OptionType.USER, "gebruiker", "De gebruiker die je wilt bannen", true)
                .addOption(OptionType.STRING, "reden", "De reden van de ban", true));
        commandData.add(Commands.slash("setrules", "Zet de regels embed"));
        commandData.add(Commands.slash("mute", "Mute een gebruiker")
                .addOption(OptionType.USER, "gebruiker", "De gebruiker die je wilt muten", true)
                .addOption(OptionType.INTEGER, "minuten", "Het aantal minuten dat de mute duurt", true)
                .addOption(OptionType.STRING, "reden", "De reden van de mute", true));
        commandData.add(Commands.slash("meme", "Krijg een leuke meme te zien"));
        commandData.add(Commands.slash("joke", "Krijg een leuke grap te zien"));
        commandData.add(Commands.slash("join", "Laat de bot jouw voice channel betreden"));
        commandData.add(Commands.slash("play", "Speel een nummer af")
                .addOption(OptionType.STRING, "nummer", "De YouTube link of naam van het nummer", true));
        commandData.add(Commands.slash("stop", "Stop de muziek"));
        commandData.add(Commands.slash("skip", "Skip het huidige nummer"));
        commandData.add(Commands.slash("nowplaying", "Bekijk welk nummer nu afspeelt"));
        commandData.add(Commands.slash("queue", "Bekijk de nummers in de wachtrij"));
        commandData.add(Commands.slash("repeat", "Herhaal het huidige nummer"));
        commandData.add(Commands.slash("leave", "Laat de bot de voice channel verlaten"));
        commandData.add(Commands.slash("sps", "Speel steen, papier, schaar tegen de bot")
                .addOption(OptionType.STRING, "keuze", "Kies steen, papier of schaar", true));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

}
