package net.rainbowfurry.phoenixbot.core;

import net.rainbowfurry.phoenixbot.commands.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    public static final CommandParser parse = new CommandParser();
    public static Map<String, Command> commandMap = new HashMap<>();

    public void handleCommand(CommandParser.CommandContainer commandContainer) throws Exception {
        //if (commandContainer.event.getGuild().getRolesByName("Bot", true).get(0).hasPermission(Permission.ADMINISTRATOR)) {
        //     if (Main.getMySql().getObject(commandContainer.event.getGuild() , "Settings", "messageName", "ServerBlocked", "messageContent").equals(false)) {

        if (commandMap.containsKey(commandContainer.invoke)) {
            boolean safe = commandMap.get(commandContainer.invoke).called(commandContainer.args, commandContainer.event);
            if (!safe) {
                commandMap.get(commandContainer.invoke).action(commandContainer.args, commandContainer.event);

                commandMap.get(commandContainer.invoke).executed(false, commandContainer.event);

            } else {
                commandMap.get(commandContainer.invoke).executed(true, commandContainer.event);
            }
        } else {
            // commandContainer.event.getTextChannel().sendMessage(new EmbedBuilder().setColor(Color.YELLOW).setDescription(
            //           ":bulb: " + STATIC.noCommand + "\nhttp://www.darkwolfcraft.net/DiscordBot/Start.html"
            //   ).build()).queue();
        }

        //   }
        /*} else {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setColor(Color.red);
            embedBuilder.setDescription("**Bot Permission**\n\nDer BOT braucht den Rang Bot mit Admin Rechten um auf dem Server" +
                    " verwendet werden zu dürfen!");
            commandContainer.event.getTextChannel().sendMessage(embedBuilder.build()).queue();
        }*/
    }

}
