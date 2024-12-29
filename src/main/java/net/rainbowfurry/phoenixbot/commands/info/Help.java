package net.rainbowfurry.phoenixbot.commands.info;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.rainbowfurry.phoenixbot.builder.CustomEmbedBuilder;
import net.rainbowfurry.phoenixbot.commands.Command;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Help implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws Exception {

        CustomEmbedBuilder customEmbedBuilder = new CustomEmbedBuilder();
        customEmbedBuilder.setContent("💡**You need help with me?**\n" +
                "You can find every Information you need under:\n" +
                "https://docs.rainbowfurry.com/phoenix-discord-bot").
                setThumbnail(event.getJDA().getSelfUser().getAvatarUrl()).
                setEmbedColor(Color.CYAN);

        final Message message = event.getChannel().sendMessageEmbeds(customEmbedBuilder.build()).complete();
        message.delete().queueAfter(30, TimeUnit.SECONDS);

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "";
    }

}
