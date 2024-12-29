package net.rainbowfurry.commands.info;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.rainbowfurry.builder.CustomEmbedBuilder;
import net.rainbowfurry.commands.Command;
import net.rainbowfurry.core.CommandParser;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class ServerInfo implements Command {

    private CustomEmbedBuilder customEmbedBuilder = new CustomEmbedBuilder();

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws Exception {

        customEmbedBuilder.setEmbedColor(Color.GREEN).//setAuthor("Phenix-Network").
                setThumbnail(event.getJDA().getSelfUser().getAvatarUrl()).
                setTitle("**Server Info**").
                setContent("**Phoenix-Network**\n" + "(Clash of Clans, DarkSouls, Diablo, GTA)").
                addField("**Minecraft**", "", true).
                addField("**Java Minecraft**:", "play.phoenix.net", true).
                addField("**Bedrock Minecraft**:", "bedrock.phoenix.net", true).
                addField("**Web**", "", true).
                addField("**Website**:", "phoenix.net", true).
                addField("**Forum**:", "forum.phoenix.net", true).
                addField("**Shop**", "", true).
                addField("**Shop**:", "shop.phoenix.net", true).
                addBlankField( true).
                addField("**Talk**", "", true).
                addField("**Discord**:", "discord.phoenix.net", true).
                addField("**TS 3/5**:", "ts.phoenix.net", true).
                addField("**Other**", "", true).
                addField("**Docs**:", "docs.phoenix.net", true);

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
