package net.rainbowfurry.phoenixbot.listener;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class FilterListener extends ListenerAdapter {

    private static final List<String> BANNED_WORDS = Arrays.asList("badword1", "badword2");
    private static final Pattern URL_PATTERN = Pattern.compile("https?://[\\w-]+(\\.[\\w-]+)+(/[^\\s]*)?");
    private static final int MAX_MENTIONS = 5;
    private final Map<String, Long> userMessageTimestamps = new HashMap<>();
    private static final long SPAM_DELAY = 3000;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String userId = event.getAuthor().getId();
        long currentTime = System.currentTimeMillis();

        // Profanity filter
        for (String word : BANNED_WORDS) {
            if (message.toLowerCase().contains(word)) {
                event.getMessage().delete().queue();
                event.getChannel().sendMessage("Inappropriate language detected.").queue();
                return;
            }
        }

        // Spam detection
        if (userMessageTimestamps.containsKey(userId) && currentTime - userMessageTimestamps.get(userId) < SPAM_DELAY) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("Please slow down. You're sending messages too quickly.").queue();
            return;
        }
        userMessageTimestamps.put(userId, currentTime);

        // Excessive Mentions
        if (event.getMessage().getMentions().getRoles().size() > MAX_MENTIONS) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("Too many mentions in your message.").queue();
            return;
        }

        // Link Prevention
        if (URL_PATTERN.matcher(message).find()) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("Links are not allowed in this channel.").queue();
        }
    }

}
