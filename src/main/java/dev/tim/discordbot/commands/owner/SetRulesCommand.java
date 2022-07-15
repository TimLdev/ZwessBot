package dev.tim.discordbot.commands.owner;

import dev.tim.discordbot.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class SetRulesCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(event.getName().equalsIgnoreCase("setrules")){
            if(event.getMember().isOwner()){

                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(new Color(200, 255, 89));
                embed.setTitle("\uD83D\uDCD7 REGELS \uD83D\uDCD7");
                embed.addField("Gedrag", "Gedraag je volwassen en houd rekening met andere mensen.", false);
                embed.addField("Content", "Het plaatsen van NSFW of virussen is verboden.", false);
                embed.addField("Kanalen", "Gebruik de kanalen waar het voor bedoeld is.", false);
                embed.addField("Gegevens", "Het delen van privé gegevens is verboden.", false);
                embed.addField("Spam", "Spammen is niet toegestaan.", false);
                embed.addField("Luister", "Luister altijd naar de Staff-leden.", false);
                embed.addField("Pesterij", "Pesten, beledigen of uitdagen is niet toegestaan.", false);
                embed.addField("Schelden", "We schelden hier niet met ziektes.", false);
                embed.addField("Rol", "Maak geen misbruik van je rank/rol.", false);
                embed.addField("Adverteren", "Niet adverteren en geen zelfpromotie zonder toestemming.", false);
                embed.addField("Pingen", "Niet onnodig iemand pingen.", false);
                embed.addField("Gebruiker", "Gebruik een normale gebruikersnaam en profielfoto.", false);
                embed.addField("Microfoon", "Gebruik een goede en normale microfoon in spraakkanalen.", false);
                embed.addField("Voice changer", "Gebruik geen voice changers in spraakkanalen.", false);
                embed.addField("Earrape", "Geen earrape in spraakkanalen.", false);
                event.getChannel().sendMessageEmbeds(embed.build()).queue();

                event.reply("Regels embed gezet!").setEphemeral(true).queue();
            } else {
                event.reply("`❌` Je hebt geen permissie voor dit command!").setEphemeral(true).queue();
            }
        }
    }

}
