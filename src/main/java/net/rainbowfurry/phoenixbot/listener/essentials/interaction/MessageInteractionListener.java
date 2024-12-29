package net.rainbowfurry.phoenixbot.listener.essentials.interaction;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageInteractionListener extends ListenerAdapter {

    @Override
    public void onMessageContextInteraction(MessageContextInteractionEvent event) {
        if (event.getName().equals("Count words")) {
            event.reply("Words: " + event.getTarget().getContentRaw().split("\\s+").length).queue();
        }
    }

}
