package net.rainbowfurry.phoenixbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.rainbowfurry.phoenixbot.core.CommandParser;

import java.awt.*;

public class Test implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        CommandParser.sendMessage(event, Color.BLUE, "TEST");
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "";
    }
}
