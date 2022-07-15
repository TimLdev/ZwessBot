package dev.tim.discordbot.commands.fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;

public class RPSCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equalsIgnoreCase("sps")) {
            event.deferReply().queue();

            OptionMapping option = event.getOption("keuze");

            if (option == null) {
                event.getHook().sendMessage("`❌` Je hebt geen steen, papier of schaar meegegeven!").setEphemeral(true).queue();
                return;
            }

            String[] rps = {"Steen", "Papier", "Schaar"};
            String computerMove = rps[new Random().nextInt(rps.length)];
            String playerMove = option.getAsString();

            if (playerMove.equalsIgnoreCase(computerMove)) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Steen, Papier, Schaar");
                embed.addField("Jouw keuze", playerMove, false);
                embed.addField("Bot keuze", computerMove, false);
                embed.addField("Resultaat", "Gelijkspel 👌", false);
                embed.setFooter("Zwess Community", event.getGuild().getIconUrl());
                embed.setColor(new Color(226, 255, 0));
                event.getHook().sendMessageEmbeds(embed.build()).queue();
            } else if (playerMove.equalsIgnoreCase("steen")) {
                if (computerMove.equalsIgnoreCase("papier")) {
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle("Steen, Papier, Schaar");
                    embed.addField("Jouw keuze", playerMove, false);
                    embed.addField("Bot keuze", computerMove, false);
                    embed.addField("Resultaat", "Verloren 😢", false);
                    embed.setFooter("Zwess Community", event.getGuild().getIconUrl());
                    embed.setColor(new Color(255, 0, 0));
                    event.getHook().sendMessageEmbeds(embed.build()).queue();
                } else if (computerMove.equalsIgnoreCase("schaar")) {
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle("Steen, Papier, Schaar");
                    embed.addField("Jouw keuze", playerMove, false);
                    embed.addField("Bot keuze", computerMove, false);
                    embed.addField("Resultaat", "Gewonnen 🎉", false);
                    embed.setFooter("Zwess Community", event.getGuild().getIconUrl());
                    embed.setColor(new Color(0, 255, 24));
                    event.getHook().sendMessageEmbeds(embed.build()).queue();
                }

            } else if (playerMove.equalsIgnoreCase("papier")) {
                if (computerMove.equalsIgnoreCase("steen")) {
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle("Steen, Papier, Schaar");
                    embed.addField("Jouw keuze", playerMove, false);
                    embed.addField("Bot keuze", computerMove, false);
                    embed.addField("Resultaat", "Gewonnen 🎉", false);
                    embed.setFooter("Zwess Community", event.getGuild().getIconUrl());
                    embed.setColor(new Color(0, 255, 24));
                    event.getHook().sendMessageEmbeds(embed.build()).queue();
                } else if (computerMove.equalsIgnoreCase("schaar")) {
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle("Steen, Papier, Schaar");
                    embed.addField("Jouw keuze", playerMove, false);
                    embed.addField("Bot keuze", computerMove, false);
                    embed.addField("Resultaat", "Verloren 😢", false);
                    embed.setFooter("Zwess Community", event.getGuild().getIconUrl());
                    embed.setColor(new Color(255, 0, 0));
                    event.getHook().sendMessageEmbeds(embed.build()).queue();
                }

            } else if (playerMove.equalsIgnoreCase("schaar")) {
                if (computerMove.equalsIgnoreCase("papier")) {
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle("Steen, Papier, Schaar");
                    embed.addField("Jouw keuze", playerMove, false);
                    embed.addField("Bot keuze", computerMove, false);
                    embed.addField("Resultaat", "Gewonnen 🎉", false);
                    embed.setFooter("Zwess Community", event.getGuild().getIconUrl());
                    embed.setColor(new Color(0, 255, 24));
                    event.getHook().sendMessageEmbeds(embed.build()).queue();
                } else if (computerMove.equalsIgnoreCase("steen")) {
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle("Steen, Papier, Schaar");
                    embed.addField("Jouw keuze", playerMove, false);
                    embed.addField("Bot keuze", computerMove, false);
                    embed.addField("Resultaat", "Verloren 😢", false);
                    embed.setFooter("Zwess Community", event.getGuild().getIconUrl());
                    embed.setColor(new Color(255, 0, 0));
                    event.getHook().sendMessageEmbeds(embed.build()).queue();
                }

            } else {
                event.getHook().sendMessage("`❌` Ongeldig gebruik! Kies uit: Steen, Papier of Schaar").setEphemeral(true).queue();
            }
        }
    }
}
