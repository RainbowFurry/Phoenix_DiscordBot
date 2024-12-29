package net.rainbowfurry.listener.essentials;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotGuildJoinListener extends ListenerAdapter {

    private Guild guild;

    @Override
    public void onGuildJoin(GuildJoinEvent event) {

        guild = event.getGuild();

        //Create SQL Entry for Guild
        //Main.manageMySQL(guild);

        //event.getGuild().getDefaultChannel().sendMessage("").queue();

    }

}
