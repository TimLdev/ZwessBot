package dev.tim.discordbot.commands.info;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class YouTubeCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equalsIgnoreCase("youtube")) {

            JSONObject subsObj = null;
            JSONObject jsonObj = null;
            try {
                URL u = new URL("https://www.googleapis.com/youtube/v3/channels?part=statistics&id=API_ID");
                URLConnection conn = u.openConnection();
                conn.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                jsonObj = new JSONObject(sb.toString());
                subsObj = jsonObj.getJSONArray("items").getJSONObject(0).getJSONObject("statistics");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            event.deferReply().queue();

            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(new Color(255, 0, 0));
            embed.setTitle("**YouTube Zwess**", "https://www.youtube.com/channel/UCiL98vMbGydUPftgxLF_GvA");
            embed.setDescription("Informatie over het YouTube kanaal Zwess");
            embed.addField("Abonnees", subsObj.get("subscriberCount").toString(), false);
            embed.addField("Views", subsObj.get("viewCount").toString(), false);
            embed.addField("Videos", subsObj.get("videoCount").toString(), false);
            embed.setFooter("Zwess Community", event.getGuild().getIconUrl());
            embed.setThumbnail(event.getGuild().getIconUrl());

            event.getHook().sendMessageEmbeds(embed.build()).queue();

        }
    }

}
