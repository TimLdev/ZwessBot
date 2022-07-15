package dev.tim.discordbot.commands.general;

import dev.tim.discordbot.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;

public class SuggestCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(event.getName().equalsIgnoreCase("suggest")){

            OptionMapping option = event.getOption("suggestie");

            if(option == null){
                event.reply("`❌` Je hebt geen suggestie meegegeven!").setEphemeral(true).queue();
                return;
            }

            String suggestie = option.getAsString();

            Random random = new Random();
            float r = random.nextFloat();
            float g = random.nextFloat();
            float b = random.nextFloat();
            Color randomColor = new Color(r, g, b);

            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(randomColor);
            embed.setAuthor(event.getMember().getUser().getName(), null, event.getMember().getUser().getEffectiveAvatarUrl());
            embed.setDescription(suggestie);
            embed.setFooter("Plaats een suggestie met /suggest");
            event.getGuild().getTextChannelById(Utils.SUGGEST_CHANNEL_ID).sendMessageEmbeds(embed.build()).queue(message -> {
                message.addReaction(Utils.SUGGEST_YES_EMOJI).queue();
                message.addReaction(Utils.SUGGEST_MINUS_EMOJI).queue();
                message.addReaction(Utils.SUGGEST_NO_EMOJI).queue();
            });

            String suggestChannel = event.getGuild().getTextChannelById(Utils.SUGGEST_CHANNEL_ID).getAsMention();
            event.reply("`✅` Suggestie geplaatst in " + suggestChannel + "!").queue();
        }
    }
}
