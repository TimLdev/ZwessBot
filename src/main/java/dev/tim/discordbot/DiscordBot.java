package dev.tim.discordbot;

import dev.tim.discordbot.commands.fun.JokeCommand;
import dev.tim.discordbot.commands.fun.MemeCommand;
import dev.tim.discordbot.commands.fun.RPSCommand;
import dev.tim.discordbot.commands.general.SuggestCommand;
import dev.tim.discordbot.commands.info.BotCommand;
import dev.tim.discordbot.commands.info.CodeCommand;
import dev.tim.discordbot.commands.info.HelpCommand;
import dev.tim.discordbot.commands.info.YouTubeCommand;
import dev.tim.discordbot.commands.moderation.BanCommand;
import dev.tim.discordbot.commands.moderation.ClearCommand;
import dev.tim.discordbot.commands.moderation.KickCommand;
import dev.tim.discordbot.commands.moderation.MuteCommand;
import dev.tim.discordbot.commands.music.*;
import dev.tim.discordbot.commands.owner.SetRulesCommand;
import dev.tim.discordbot.commands.owner.SetTicketCommand;
import dev.tim.discordbot.listeners.MemberJoinListener;
import dev.tim.discordbot.listeners.MemberLeaveListener;
import dev.tim.discordbot.listeners.TicketButtonListener;
import dev.tim.discordbot.managers.SlashCommandManager;
import dev.tim.discordbot.utils.Utils;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    private final Dotenv config;
    private final ShardManager shardManager;

    public DiscordBot() throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("/help | dsc.gg/zwess"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_VOICE_STATES);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.enableCache(CacheFlag.ONLINE_STATUS, CacheFlag.VOICE_STATE);
        shardManager = builder.build();

        shardManager.addEventListener(
                // Managers
                new SlashCommandManager(),
                // Commands
                new ClearCommand(),
                new CodeCommand(),
                new SuggestCommand(),
                new BotCommand(),
                new SetTicketCommand(),
                new KickCommand(),
                new YouTubeCommand(),
                new HelpCommand(),
                new BanCommand(),
                new SetRulesCommand(),
                new MuteCommand(),
                new MemeCommand(),
                new JokeCommand(),
                new JoinCommand(),
                new PlayCommand(),
                new StopCommand(),
                new SkipCommand(),
                new NowPlayingCommand(),
                new QueueCommand(),
                new RepeatCommand(),
                new LeaveCommand(),
                new RPSCommand(),
                // Listeners
                new MemberJoinListener(),
                new TicketButtonListener(),
                new MemberLeaveListener()
        );

    }

    public Dotenv getConfig(){
        return config;
    }

    public ShardManager getShardManager(){
        return shardManager;
    }

    public static void main(String[] args){

        new Utils();
        try {
            DiscordBot bot = new DiscordBot();
        } catch (LoginException e){
            System.out.println("ERROR: Bot token niet gevonden!");
        }
    }

}
