package net.rainbowfurry.commands.info;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.rainbowfurry.builder.CustomEmbedBuilder;
import net.rainbowfurry.commands.Command;

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

        Member member = event.getGuild().getMemberById(event.getMessage().getMentions().getUsers().get(0).getId());

        String roles = "";
        int index = 0;
        int size = member.getRoles().size();
        for(Role role : member.getRoles()) {
            roles += role.getName();
            if(index < size -1)
                roles += " | ";
            index++;
        }

        customEmbedBuilder.setEmbedColor(Color.GREEN).//setAuthor("Phenix-Network").
                setThumbnail(member.getAvatarUrl()).
                setTitle("**User Profile**").
                setContent("**Profile**: " + member.getAsMention()).
                addField("**User Name**:", member.getEffectiveName(), true).
                addField("**User ID**:", member.getId(), true).
                addField("**Highest Rank**:", member.getRoles().get(0).getName(), true).
                addField("**Created**:", "`" + member.getTimeJoined().format(DateTimeFormatter.ofPattern("HH:mm\ndd. MMMM yyyy")) + "`", true).
                addField("**Joined**:", "`" + member.getTimeCreated().format(DateTimeFormatter.ofPattern("HH:mm\ndd. MMMM yyyy")) + "`", true).
                addField("**Level**:", "100", false).
                addField("**EXP**:", "min/max", false).
                addField("**Ranks**:", roles, false).
                addField("**Warnings**:", "-", false);

        event.getChannel().sendMessageEmbeds(customEmbedBuilder.build()).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "";
    }
}
