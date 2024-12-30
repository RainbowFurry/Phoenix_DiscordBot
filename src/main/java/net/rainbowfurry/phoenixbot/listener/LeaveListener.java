package net.rainbowfurry.phoenixbot.listener;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.rainbowfurry.phoenixbot.builder.CustomEmbedBuilder;

import java.awt.*;

public class LeaveListener extends ListenerAdapter {

    private final CustomEmbedBuilder customEmbedBuilder = new CustomEmbedBuilder();

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {

        Member member = event.getMember();

        customEmbedBuilder.setEmbedColor(Color.RED).setFooter(member.getEffectiveName() + " has left the Server!", member.getEffectiveAvatarUrl());

        if(event.getGuild().getDefaultChannel() != null)
            event.getGuild().getDefaultChannel().asTextChannel().sendMessageEmbeds(customEmbedBuilder.build()).queue();

    }

    //private and server message

}
