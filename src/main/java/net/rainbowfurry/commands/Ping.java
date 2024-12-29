package net.rainbowfurry.commands;

import java.awt.*;

public class Ping {

    private static long inputTime;

    /*
    *     setInputTime(System.currentTimeMillis());
    long processing = (new Date()).getTime() - inputTime;
    long ping = event.getJDA().getGatewayPing();
    event.getHook().editOriginalEmbeds(new MessageEmbed[] { (new CustomEmbedBuilder()).setEmbedColor(Color.MAGENTA).setTitle("Ping").setContent(String.format(":ping_pong:   Pong!\n\nThe Bot takes **%s milliseconds** to answer.", new Object[] { Long.valueOf(ping) })).build() }).queue();
    return true;
    * */

    public static void setInputTime(long inputTimeLong) {
        inputTime = inputTimeLong;
    }

    private Color getColorByPing(long ping) {
        if (ping < 100L)
            return Color.cyan;
        if (ping < 400L)
            return Color.green;
        if (ping < 700L)
            return Color.yellow;
        if (ping < 1000L)
            return Color.orange;
        return Color.red;
    }

}
