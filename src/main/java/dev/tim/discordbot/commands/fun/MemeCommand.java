package dev.tim.discordbot.commands.fun;

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
import java.util.Random;

public class MemeCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(event.getName().equalsIgnoreCase("meme")) {

            JSONObject data = null;
            String title = null;
            String image = null;
            String url = null;
            try {
                URL u = new URL("https://apis.duncte123.me/meme");
                URLConnection conn = u.openConnection();
                conn.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                JSONObject jsonObj = new JSONObject(sb.toString());
                data = jsonObj.getJSONObject("data");

                title = data.get("title").toString();
                url = data.get("url").toString();
                image = data.get("image").toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            event.deferReply().queue();

            Random random = new Random();
            float r = random.nextFloat();
            float g = random.nextFloat();
            float b = random.nextFloat();
            Color randomColor = new Color(r, g, b);

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle(title, url);
            embed.setColor(randomColor);
            embed.setImage(image);

            event.getHook().sendMessageEmbeds(embed.build()).queue();

        }
    }

}
