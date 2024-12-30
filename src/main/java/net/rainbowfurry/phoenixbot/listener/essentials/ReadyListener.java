package net.rainbowfurry.phoenixbot.listener.essentials;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.rainbowfurry.phoenixbot.Main;

public class ReadyListener extends ListenerAdapter {

    public void onReady(ReadyEvent event){

        //Display on which Discord Servers the Bot is running
        StringBuilder onGuilds = new StringBuilder();
        for(Guild guild : event.getJDA().getGuilds())
            onGuilds.append("Name: ").append(guild.getName()).append(", ID: ").append(guild.getId()).append("\n");
        Main.instance.logger.info("This bot is on following servers:\n" + onGuilds);

        // event.getJDA().getGuilds().forEach(Main::manageMySQL);

    }

}
