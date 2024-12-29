package net.rainbowfurry.commands.admin;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.rainbowfurry.commands.Command;

import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Clear implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws Exception {

        int amount;

        //Get how many Messages need to be cleared
        if (args.length == 0)
            amount = 99;
        else
            amount = Integer.parseInt(args[0]);

        /*
        *                 event.getChannel().getHistory().retrievePast(numMessages + 1) // +1 to include the command message
                        .queue(messages -> {
                            event.getChannel().purgeMessages(messages);
                            event.getChannel().sendMessage("Cleared " + numMessages + " messages.").queue(msg -> {
                                msg.delete().queueAfter(5, java.util.concurrent.TimeUnit.SECONDS); // Auto-delete confirmation
                            });
                        });
        * */

        if (amount > 0 && amount < 100)
            try {
                //event.getMessage().delete().queue();
                MessageHistory history = new MessageHistory(event.getChannel());
                List<Message> messages = history.retrievePast(amount + 1).complete();
                event.getChannel().purgeMessages(messages);

                final Message message = event.getChannel().sendMessageEmbeds(new EmbedBuilder().setColor(Color.GREEN).addField("Garbage", ":wastebasket: All Messages has been deleted", false).build()).complete();
                message.delete().queueAfter(5, TimeUnit.SECONDS);
//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        message.delete().queue();
//                    }
//                }, 3000);
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "";
    }
}
