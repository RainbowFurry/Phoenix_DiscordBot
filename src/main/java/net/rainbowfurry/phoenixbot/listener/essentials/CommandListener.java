package net.rainbowfurry.phoenixbot.listener.essentials;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.rainbowfurry.phoenixbot.core.CommandHandler;

public class CommandListener extends ListenerAdapter {

    private CommandHandler commandHandler;
    public static Guild guild;

    public CommandListener(CommandHandler commandHandler){
        this.commandHandler = commandHandler;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        guild = event.getGuild();
        String prefix = "-";
        if(event.getMessage().getContentRaw().startsWith(prefix) && !event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            try {
                commandHandler.handleCommand(CommandHandler.parse.parse(event.getMessage().getContentRaw(), event));
                System.out.println("Phoenix: The Member: " + event.getMember().getEffectiveName() + " has run the Command: " + event.getMessage().getContentRaw());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
