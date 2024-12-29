package net.rainbowfurry.phoenixbot.listener;

import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.rainbowfurry.phoenixbot.builder.CustomEmbedBuilder;

public class LeaveListener extends ListenerAdapter {

    private final CustomEmbedBuilder customEmbedBuilder = new CustomEmbedBuilder();

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {

    }

    //private and server message

}
