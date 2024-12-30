package net.rainbowfurry.phoenixbot.builder;

import net.dv8tion.jda.api.entities.emoji.RichCustomEmoji;

public class TextFormatting {

    public static String bold(String text){
        return String.format("**%s**", text);
    }

    public static String italic(String text){
        return String.format("*%s*", text);
    }

    public static String underline(String text){
        return String.format("__%s__", text);
    }

    public static String strikeThrough(String text){
        return String.format("~~%s~~", text);
    }

    public static String spoiler(String text){
        return String.format("||%s||", text);
    }


    public static String heading(String text, int heading){
        StringBuilder content = new StringBuilder();
        for(int i = 0; i < heading; i++)
            content.append("#");
        return String.format(content.append("%s").toString(), text);
    }

    public static String heading1(String text){
        return String.format("#%s", text);
    }

    public static String heading2(String text){
        return String.format("##%s", text);
    }

    public static String heading3(String text){
        return String.format("###%s", text);
    }

    public static String link(String displayName, String url){
        return String.format("[%s](%s)", displayName, url);
    }

    public static String subText(String text){
        return String.format("-#%s", text);
    }

    public static String codeLine(String text){
        return String.format("`%s`", text);
    }

    public static String codeBlock(String text){
        return String.format("```%s```", text);
    }

    public static String quoteLine(String text){
        return String.format(">%s", text);
    }

    public static String quoteBlock(String text){
        return String.format(">>>%s", text);
    }

    public static String customEmoji(RichCustomEmoji emoji){
        return String.format("<:%s:%s>", emoji.getName(), emoji.getId());
    }

    // :emote: | <:name:id>
    public static String customEmoji(String name, String id){
        return String.format("<:%s:%s>", name, id);
    }

    //ToDo Listing * - 1.
    //https://support.discord.com/hc/en-us/articles/210298617-Markdown-Text-101-Chat-Formatting-Bold-Italic-Underline

}
