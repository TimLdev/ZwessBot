package dev.tim.discordbot.commands.moderation;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;;import java.util.List;

public class ClearCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equalsIgnoreCase("clear")) {

            OptionMapping option = event.getOption("aantal");

            if (event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {

                if (option == null) {
                    event.reply("`❌` Je hebt geen aantal meegegeven!").setEphemeral(true).queue();
                    return;
                }

                int aantal = option.getAsInt();

                List<Message> messageList = event.getChannel().getHistory().retrievePast(aantal).complete();
                event.getTextChannel().deleteMessages(messageList).queue();
                event.reply("`✅` " + event.getMember().getAsMention() + " **heeft " + aantal + " berichten verwijderd!**").setEphemeral(false).queue();

            } else {
                event.reply("`❌` Je hebt geen permissie voor dit command!").setEphemeral(true).queue();
            }
        }
    }

}
