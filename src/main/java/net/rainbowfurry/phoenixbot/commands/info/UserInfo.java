package net.rainbowfurry.phoenixbot.commands.info;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.rainbowfurry.phoenixbot.builder.CustomEmbedBuilder;
import net.rainbowfurry.phoenixbot.commands.Command;
import net.rainbowfurry.phoenixbot.commands.core.CommandParser;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class UserInfo implements Command {

    private final CustomEmbedBuilder customEmbedBuilder = new CustomEmbedBuilder();

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws Exception {
        if(event.getMember() != null)
            displayUserInfo(event.getMember(), event.getChannel());
    }

    /*public void displayUserInfo(Event event){}*/

    public void displayUserInfo(Member member, MessageChannel channel){
        customEmbedBuilder.setEmbedColor(Color.GREEN).
                setThumbnail(member.getEffectiveAvatarUrl()).
                setTitle("**User Profile**").
                setContent("**Profile**: " + member.getAsMention()).
                addField("**User Name**:", member.getEffectiveName(), true).
                addField("**User ID**:", member.getId(), true).
                addField("**Highest Rank**:", member.getRoles().get(0).getName(), true).
                addField("**Created**:", "`" + member.getTimeJoined().format(DateTimeFormatter.ofPattern("HH:mm\ndd. MMMM yyyy")) + "`", true).
                addField("**Joined**:", "`" + member.getTimeCreated().format(DateTimeFormatter.ofPattern("HH:mm\ndd. MMMM yyyy")) + "`", true).
                addField("**Level**:", "100", false).
                addField("**EXP**:", "min/max", false).
                addField("**Ranks**:", getUserRoles(member), false).
                addField("**Warnings**:", "-", false);

        CommandParser.sendMessage(channel, customEmbedBuilder);
        //channel.sendMessageEmbeds(customEmbedBuilder.build()).queue();
    }

    private String getUserRoles(Member member){
        StringBuilder roles = new StringBuilder();
        int index = 0;
        int size = member.getRoles().size();
        for(Role role : member.getRoles()) {
            roles.append(role.getName());
            if(index < size -1)
                roles.append(" | ");
            index++;
        }
        return roles.toString();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "";
    }
}
