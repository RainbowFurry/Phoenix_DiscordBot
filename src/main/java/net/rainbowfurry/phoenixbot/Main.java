package net.rainbowfurry.phoenixbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.rainbowfurry.phoenixbot.commands.admin.Clear;
import net.rainbowfurry.phoenixbot.commands.Test;
import net.rainbowfurry.phoenixbot.commands.info.Help;
import net.rainbowfurry.phoenixbot.commands.info.ServerInfo;
import net.rainbowfurry.phoenixbot.commands.info.UserInfo;
import net.rainbowfurry.phoenixbot.core.CommandHandler;
import net.rainbowfurry.phoenixbot.listener.JoinListener;
import net.rainbowfurry.phoenixbot.listener.LeaveListener;
import net.rainbowfurry.phoenixbot.listener.essentials.BotGuildJoinListener;
import net.rainbowfurry.phoenixbot.listener.essentials.CommandListener;
import net.rainbowfurry.phoenixbot.listener.essentials.ReadyListener;
import net.rainbowfurry.phoenixbot.listener.essentials.interaction.CommandInteractionListener;
import net.rainbowfurry.phoenixbot.listener.essentials.interaction.MessageInteractionListener;
import net.rainbowfurry.phoenixbot.listener.essentials.interaction.UserInteractionListener;
import net.rainbowfurry.phoenixbot.manager.ConfigManager;
import net.rainbowfurry.phoenixbot.manager.LogManager;

import java.io.IOException;
import java.util.logging.Logger;
//import java.util.logging.LogManager;

public class Main
{

    private static Main instance;
    public static JDA jda;
    private static JDABuilder builder;
    public static Logger logger;
    private static LogManager _logger;

    public static void main( String[] args ) throws IOException {

        // Init Logger
        logger = Logger.getGlobal();
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        _logger = new LogManager();
        _logger.Log(LogManager.LogType.SYSTEM, 1, "\nBotName: Phoenix\nCreator: Jasmin Hoffmann\nNickName:RainbowFurry\n\n");
        _logger.Log(LogManager.LogType.SYSTEM, 1, "The Bot is starting Up!");
        _logger.Log(LogManager.LogType.SYSTEM, 1, "The Logger has been initialized and loaded!");

        // Init Configs
        _logger.Log(LogManager.LogType.SYSTEM, 1, "Start loading Configs.");
        ConfigManager configManager = new ConfigManager();
        _logger.Log(LogManager.LogType.SYSTEM, 1, "The Config has been created and loaded!");

        // Init SQL
        //...

        // Init Bot
        _logger.Log(LogManager.LogType.SYSTEM, 1, "Start loading Bot.");
        builder = JDABuilder.createDefault(configManager.getKey("token"));
        builder.setAutoReconnect(true);
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, new CacheFlag[] { CacheFlag.VOICE_STATE });
        builder.setBulkDeleteSplittingEnabled(false);
        // Grant Permission
        for(GatewayIntent gatewayIntent : GatewayIntent.values())
            builder.enableIntents(gatewayIntent);

        builder.setStatus(OnlineStatus.fromKey(configManager.getKey("activityState")));
        builder.setActivity(Activity.of(Activity.ActivityType.valueOf(configManager.getKey("activityType")), configManager.getKey("activityText")));

        jda = builder.build();

        //ToDo Example Commands In Bot Command List View
        CommandListUpdateAction commands = jda.updateCommands();
        commands.addCommands(
                // Auf server der Command Menu Button
                Commands.slash("botinfo", "Provides Info about the Bot"),
                Commands.slash("serverinfo", "Provides Info about the Server"),
                Commands.slash("ping", "Shows your Connection to the Server."),
                // auf user und dann apps (info, kick, mute, warn, ban)
                //ToDo nur für Guilde?
                /*Commands.user("User Info"),
                Commands.user("Kick"),
                Commands.user("Mute"),
                Commands.user("Warn"),
                Commands.user("Ban"),*/
                //Commands.user("Moderation"),
                // drei punkte bei nachrichten und apps (KP)
                Commands.message("Message Info"),
                Commands.message("Moderation"),
                Commands.message("Translate"),
                Commands.message("Archive")
        ).queue();

        Guild guild = jda.getGuildById("1309574664141668393");

        if (guild != null) {
            guild.updateCommands().addCommands(
                    Commands.user("Moderation")
            ).queue();
        }

        _logger.Log(LogManager.LogType.SYSTEM, 1, "The Bot has been initialized without errors!");

        // Command and Listener Registration
        commandRegistration();
        _logger.Log(LogManager.LogType.SYSTEM, 1, "The Commands have been loaded!");
        listenerRegistration();
        _logger.Log(LogManager.LogType.SYSTEM, 1, "The Listener have been loaded!");

        _logger.Log(LogManager.LogType.SYSTEM, 1, "The BOT has been started successfully without Errors!\n\n");

    }

    private static void commandRegistration() {
        // Admin
        // CommandHandler.commandMap.put("ban", new Test());
        // CommandHandler.commandMap.put("kick", new Test());
        // CommandHandler.commandMap.put("warn", new Test());
        // CommandHandler.commandMap.put("mute", new Test());
        // User
        // CommandHandler.commandMap.put("ping", new Test());
        // CommandHandler.commandMap.put("invitebot", new Test());
        // CommandHandler.commandMap.put("invite", new Test());
        // Fun
        // CommandHandler.commandMap.put("coin", new Test());
        // CommandHandler.commandMap.put("dice", new Test());
        // Info
        CommandHandler.commandMap.put("help", new Help());
        CommandHandler.commandMap.put("userinfo", new UserInfo());
        CommandHandler.commandMap.put("serverinfo", new ServerInfo());
        //CommandHandler.commandMap.put("botinfo", new Test());
        // Other
        CommandHandler.commandMap.put("test", new Test());
        CommandHandler.commandMap.put("clear", new Clear());
    }

    private static void listenerRegistration() {

        // Essentials Bot Listener
        jda.addEventListener(new CommandListener(new CommandHandler()));//Handle Commands
        jda.addEventListener(new ReadyListener());//Bot started output Message
        jda.addEventListener(new BotGuildJoinListener());//Create Database on Bot join Guild
        // Interaction
        jda.addEventListener(new UserInteractionListener());
        jda.addEventListener(new MessageInteractionListener());
        jda.addEventListener(new CommandInteractionListener());

        // Listener
        jda.addEventListener(new JoinListener());
        jda.addEventListener(new LeaveListener());
        // Support Listener
        // Ticket Listener
        // Self Role Listener

    }

}
