package net.rainbowfurry.phoenixbot.listener.essentials.interaction;

import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class UserInteractionListener extends ListenerAdapter {

    @Override
    public void onUserContextInteraction(UserContextInteractionEvent event) {
        if (event.getName().equals("Get user avatar")) {
            event.reply("Avatar: " + event.getTarget().getEffectiveAvatarUrl()).queue();
        }
    }

}
