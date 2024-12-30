package net.rainbowfurry.phoenixbot.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.rainbowfurry.phoenixbot.builder.CustomEmbedBuilder;
import net.rainbowfurry.phoenixbot.core.CommandParser;

import java.awt.*;

public class Test implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        CustomEmbedBuilder customEmbedBuilder = new CustomEmbedBuilder();
        Member member = event.getMember();


        customEmbedBuilder.setEmbedColor(Color.GREEN).setThumbnail(member.getEffectiveAvatarUrl()).setContent(member.getAsMention() + "has joined the Server!");
        CommandParser.sendMessage(event, customEmbedBuilder);
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "";
    }
}
