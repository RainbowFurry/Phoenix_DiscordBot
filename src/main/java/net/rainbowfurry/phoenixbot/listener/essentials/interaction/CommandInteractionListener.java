package net.rainbowfurry.phoenixbot.listener.essentials.interaction;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class CommandInteractionListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if(event.getName().equals("test")){
            event.reply("TEST").queue();
        }

        if(event.getName().equals("badge")){
            System.out.println("Wait 24h and claim your Badge!");
            event.reply("Wait 24h and claim your Badge!").queue();
        }

        if (event.getName().equals("hello")) {
            event.reply("Click the button to say hello")
                    .addActionRow(
                            Button.primary("hello", "Click Me"), // Button with only a label
                            Button.success("emoji", Emoji.fromFormatted("<:minn:245267426227388416>"))) // Button with only an emoji
                    .queue();
        } else if (event.getName().equals("info")) {
            event.reply("Click the buttons for more info")
                    .addActionRow( // link buttons don't send events, they just open a link in the browser when clicked
                            Button.link("https://github.com/discord-jda/JDA", "GitHub")
                                    .withEmoji(Emoji.fromFormatted("<:github:849286315580719104>")), // Link Button with label and emoji
                            Button.link("https://docs.jda.wiki/", "Javadocs")) // Link Button with only a label
                    .queue();
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("hello")) {
            event.reply("Hello :)").queue(); // send a message in the channel
        } else if (event.getComponentId().equals("emoji")) {
            event.editMessage("That button didn't say click me").queue(); // update the message
        }
    }

}
