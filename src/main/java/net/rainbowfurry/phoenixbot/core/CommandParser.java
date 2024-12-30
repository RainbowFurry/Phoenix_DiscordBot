package net.rainbowfurry.phoenixbot.core;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.rainbowfurry.phoenixbot.builder.CustomEmbedBuilder;

import java.awt.*;
import java.nio.channels.Channel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class CommandParser {

    public static void sendMessage(MessageReceivedEvent event, Color color, String message){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(color);
        //embedBuilder.setFooter(event.getAuthor().getName() + " " + event.getMember().getRoles().get(0).getName(), event.getAuthor().getAvatarUrl());
        //embedBuilder.setFooter(event.getMessage().getTimeCreated().format(DateTimeFormatter.ofPattern("HH:mm\ndd. MMMM yyyy")));
        embedBuilder.setFooter(event.getAuthor().getName() + " " + event.getMember().getRoles().get(0).getName() + event.getMessage().getTimeCreated().format(DateTimeFormatter.ofPattern("HH:mm\ndd. MMMM yyyy")), event.getAuthor().getAvatarUrl());
        embedBuilder.setDescription(message);
        event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
    }

    public static void sendMessage(MessageChannel channel, EmbedBuilder embed){
        channel.sendMessageEmbeds(embed.build()).queue();
    }

    public static void sendMessage(MessageChannel channel, CustomEmbedBuilder embed){
        channel.sendMessageEmbeds(embed.build()).queue();
    }

    public static void sendMessage(MessageReceivedEvent event, EmbedBuilder embed){
        event.getChannel().sendMessageEmbeds(embed.build()).queue();
    }

    public static void sendMessage(MessageReceivedEvent event, MessageEmbed embed){
        event.getChannel().sendMessageEmbeds(embed).queue();
    }

    public static void sendMessage(MessageReceivedEvent event, CustomEmbedBuilder embed){
        event.getChannel().sendMessageEmbeds(embed.build()).queue();
    }

    public static void sendMessageWithDeleteTimer(MessageReceivedEvent event, EmbedBuilder embedBuilder, int time){
        final Message message = event.getChannel().sendMessageEmbeds(embedBuilder.build()).complete();
        message.delete().queueAfter(time, TimeUnit.SECONDS);
    }

    public static void sendMessageWithDeleteTimer(MessageReceivedEvent event, CustomEmbedBuilder embedBuilder, int time){
        final Message message = event.getChannel().sendMessageEmbeds(embedBuilder.build()).complete();
        message.delete().queueAfter(time, TimeUnit.SECONDS);
    }

    public CommandContainer parse(String raw, MessageReceivedEvent event) {
        event.getMessage().delete().queue();
        String beheaded;
        try{
            beheaded = raw.replaceFirst( "-", "");
        }catch (Exception e) {
            beheaded = raw.replaceFirst("\\.", "");
        }
        String[] splitBeheaded = beheaded.split(" ");
        String invoke = splitBeheaded[0];
        ArrayList<String> split = new ArrayList<>(Arrays.asList(splitBeheaded));
        String[] args = new String[split.size() - 1];
        split.subList(1, split.size()).toArray(args);
        return new CommandContainer(raw, beheaded, splitBeheaded, invoke, args, event);
    }

    static class CommandContainer {

        final String raw;
        final String beheaded;
        final String[] splitBeheaded;
        final String invoke;
        final String[] args;
        final MessageReceivedEvent event;

        CommandContainer(String rw, String beheaded, String[] splitBeheaded, String invoke, String[] args, MessageReceivedEvent event) {
            this.raw = rw;
            this.beheaded = beheaded;
            this.splitBeheaded = splitBeheaded;
            this.invoke = invoke;
            this.args = args;
            this.event = event;
        }

    }
}
