package net.rainbowfurry.phoenixbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class Ping implements Command {

    private long inputTime;

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws Exception {

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "";
    }

    public void setInputTime(long inputTime) {
        this.inputTime = inputTime;
    }

    private Color getColorByPing(long ping) {
        if (ping < 100L)
            return Color.cyan;
        if (ping < 400L)
            return Color.green;
        if (ping < 700L)
            return Color.yellow;
        if (ping < 1000L)
            return Color.orange;
        return Color.red;
    }

}
