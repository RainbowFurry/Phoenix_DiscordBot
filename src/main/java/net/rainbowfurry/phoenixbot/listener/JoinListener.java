package net.rainbowfurry.phoenixbot.listener;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.rainbowfurry.phoenixbot.builder.CustomEmbedBuilder;
import net.rainbowfurry.phoenixbot.core.CommandParser;

import java.awt.*;

public class JoinListener extends ListenerAdapter {

    private final CustomEmbedBuilder customEmbedBuilder = new CustomEmbedBuilder();

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        Member member = event.getMember();

        // First Join - Yellow if enabled
        //ustomEmbedBuilder.setEmbedColor(Color.YELLOW).setFooter(member.getEffectiveName() + " has joined the Server for the first Time!", member.getEffectiveAvatarUrl());

        // Join - Green
        customEmbedBuilder.setEmbedColor(Color.GREEN).setFooter(member.getEffectiveName() + " has joined the Server! Welcome back!", member.getEffectiveAvatarUrl());
        //customEmbedBuilder.setEmbedColor(Color.GREEN).setImage(member.getEffectiveAvatarUrl()).setContent(member.getAsMention() + "has joined the Server!");
        //customEmbedBuilder.setEmbedColor(Color.GREEN).setThumbnail(member.getEffectiveAvatarUrl()).setContent(member.getAsMention() + "has joined the Server!");
        if(event.getGuild().getDefaultChannel() != null)
            event.getGuild().getDefaultChannel().asTextChannel().sendMessageEmbeds(customEmbedBuilder.build()).queue();

    }

    //private and server message

}
