package dev.tim.discordbot.commands.info;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class CodeCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equalsIgnoreCase("code")) {
            event.deferReply().queue();
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.CYAN);
            embed.setTitle("Hoe post je je code?");
            embed.setDescription("Zet je code in codeblokken!");
            embed.addField("Voorbeeld", "` ``java\n" +
                    "public static void main(String[] args) {\n" +
                    "System.out.println(\"Abonneer op Zwess!\");\n" +
                    "}\n" +
                    "```", false);
            embed.addField("Uitkomst", "```java\n" +
                    "public static void main(String[] args) {\n" +
                    "    System.out.println(\"Abonneer op Zwess!\");\n" +
                    "}\n" +
                    "```", false);
            embed.addField(" " ,"Maak een screenshot als je code te groot is voor een bericht. ", false);
            embed.setFooter("Zwess Community", event.getGuild().getIconUrl());

            event.getHook().sendMessageEmbeds(embed.build()).queue();
        }
    }

}
