package net.rainbowfurry.phoenixbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.rainbowfurry.phoenixbot.commands.Ping;
import net.rainbowfurry.phoenixbot.commands.admin.*;
import net.rainbowfurry.phoenixbot.commands.Test;
import net.rainbowfurry.phoenixbot.commands.info.Help;
import net.rainbowfurry.phoenixbot.commands.info.ServerInfo;
import net.rainbowfurry.phoenixbot.commands.info.UserInfo;
import net.rainbowfurry.phoenixbot.commands.core.CommandHandler;
import net.rainbowfurry.phoenixbot.listener.*;
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

    public static Main instance;
    private JDA jda;
    private JDABuilder builder;
    public Logger logger;
    public LogManager _logger;
    private CommandListUpdateAction commands;

    public static void main( String[] args ) throws IOException {
      try {
          instance = new Main();
          instance.run(args);
      }catch (Exception exception){
          instance._logger.Error(exception);
      }
    }

    public void run(String[] args) throws IOException {
        // Register a shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            onShutDown();
        }));

        // Init Logger
        logger = Logger.getGlobal();
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");

        _logger = new LogManager();
        _logger.Info("\nBotName: Phoenix\nCreator: Jasmin Hoffmann\nNickName:RainbowFurry\n");
        _logger.Info("All Infos About the Bot: https://docs.rainbowfurry.com/phoenix-discord-bot\n\n");
        _logger.Info( "The Bot is starting Up!");
        _logger.Info( "The Logger has been initialized and loaded!");

        // Init Configs
        _logger.Info( "Start loading Configs.");
        ConfigManager configManager = new ConfigManager();
        _logger.Info("The Config has been created and loaded!");

        // Init SQL
        //...

        // Init Bot
        _logger.Info( "Start loading Bot.");
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

        // Bot Interaction Commands
        commands = jda.updateCommands();
        _logger.Info("Start Loading Bot Interactions");
        registerInteractionCommands();
        _logger.Info("Finished Loading Bot Interactions");
        commands.queue();

        // TEST
        Guild guild = jda.getGuildById("1309574664141668393");
        if (guild != null) {
            guild.updateCommands().addCommands(
                    Commands.user("Moderation"),
                    Commands.slash("Badge", "Get the Dev Badge :D")
            ).queue();
        }

        _logger.Info("The Bot has been initialized successfully!");

        // Command and Listener Registration
        commandRegistration();
        _logger.Info( "The Commands have been loaded!");
        listenerRegistration();
        _logger.Info( "The Listener have been loaded!");

        _logger.Info("The BOT has been started successfully!\n\n");

    }

    private void onShutDown(){
        System.out.println("Application is shutting down...");
        jda.shutdownNow();
        System.out.println("The Bot is now Offline!");
    }

    private void commandRegistration() {
        // Admin
         CommandHandler.commandMap.put("ban", new Ban());
         CommandHandler.commandMap.put("kick", new Kick());
         CommandHandler.commandMap.put("warn", new Warn());
         CommandHandler.commandMap.put("mute", new Mute());
        // User
         CommandHandler.commandMap.put("ping", new Ping());
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

    private void listenerRegistration() {

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
        jda.addEventListener(new SupportListener());
        jda.addEventListener(new TicketListener());
        jda.addEventListener(new SelfroleListener());
        jda.addEventListener(new GiveAwayListener());
        jda.addEventListener(new JoinRolesListener());
        jda.addEventListener(new VerificationListener());

    }

    public void updateBotStatus(Activity activity){
        jda = builder.setActivity(activity).build();
    }

    public void updateBotStatus(Activity.ActivityType activityType, String content){
        jda = builder.setActivity(Activity.of(activityType, content)).build();
    }

    /**
     * BOT INTERACTION COMMANDS
     */

    private void registerInteractionCommands(){
        _logger.Info( "Start Loading Bot Command Interaction Commands");
        registerBotInteractionCommands();
        _logger.Info( "Start Loading Bot Menu Interaction Commands");
        registerMenuInteractionCommands();
        _logger.Info( "Start Loading Bot User Interaction Commands");
        registerUserInteractionCommands();
    }

    private void registerUserInteractionCommands(){
        commands.addCommands(
                // Auf server der Command Menu Button
                //ToDo nur für Guilde?
                /*Commands.user("User Info"),
                //Commands.user("Moderation"),
                Commands.user("Kick"),
                Commands.user("Mute"),
                Commands.user("Warn"),
                Commands.user("Ban")*/
        );
        _logger.Info( "Finished Loading Bot User Interaction Commands");
    }

    private void registerMenuInteractionCommands(){
        commands.addCommands(
                // drei punkte bei nachrichten und apps (KP)
                Commands.message("Message Info"),
                Commands.message("Moderation"),
                Commands.message("Translate"),
                Commands.message("Archive")
        );
        _logger.Info( "Finished Loading Bot Menu Interaction Commands");
    }

    private void registerBotInteractionCommands(){
        commands.addCommands(
                // Auf server der Command Menu Button
                Commands.slash("botinfo", "Provides Info about the Bot").addOption(OptionType.USER, "User", "Mention User").addSubcommands(),
                Commands.slash("serverinfo", "Provides Info about the Server"),
                Commands.slash("ping", "Shows your Connection to the Server."),
                Commands.slash("test", "For Testing!!!")
        );
        _logger.Info( "Finished Loading Bot Command Interaction Commands");
    }

}
