package net.rainbowfurry.phoenixbot.listener.essentials.interaction;

import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.rainbowfurry.phoenixbot.commands.info.UserInfo;

public class UserInteractionListener extends ListenerAdapter {

    @Override
    public void onUserContextInteraction(UserContextInteractionEvent event) {
        switch (event.getName()) {

            case "UserInfo":
                final UserInfo userInfo = new UserInfo();
                userInfo.displayUserInfo(event.getTargetMember(), event.getMessageChannel());
                break;

            case "Kick":
                //
                break;

            case "Warn":
                //
                break;

            case "Ban":
                //
                break;

            case "Mute":
                //
                break;

            case "Mod":
                //
                break;

        }
    }

}
