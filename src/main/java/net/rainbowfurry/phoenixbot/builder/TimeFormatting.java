package net.rainbowfurry.phoenixbot.builder;

public class TimeFormatting {

    /**
     * T - November 28, 2018 9:01 AM
     * @param timeMilli
     * @return
     */
    public static String Default(long timeMilli){
        return String.format("<t:%s", timeMilli);
    }

    /**
     * T - 9:01 AM
     * @param timeMilli
     * @return
     */
    public static String ShortTime(long timeMilli){
        return String.format("<t:%s:t", timeMilli);
    }

    /**
     * T - 9:01:00 AM
     * @param timeMilli
     * @return
     */
    public static String LongTime(long timeMilli){
        return String.format("<t:%s:T", timeMilli);
    }

    /**
     * T - 11/28/2018
     * @param timeMilli
     * @return
     */
    public static String ShortDate(long timeMilli){
        return String.format("<t:%s:d", timeMilli);
    }

    /**
     * T - November 28, 2018
     * @param timeMilli
     * @return
     */
    public static String LongDate(long timeMilli){
        return String.format("<t:%s:D", timeMilli);
    }

    /**
     * T - November 28, 2018 9:01 AM
     * @param timeMilli
     * @return
     */
    public static String ShortDateTime(long timeMilli){
        return String.format("<t:%s:f", timeMilli);
    }

    /**
     * T - Wednesday, November 28, 2018 9:01 AM
     * @param timeMilli
     * @return
     */
    public static String LongDateTime(long timeMilli){
        return String.format("<t:%s:F", timeMilli);
    }

    /**
     * T - 3 years ago
     * @param timeMilli
     * @return
     */
    public static String RelativeTime(long timeMilli){
        return String.format("<t:%s:R", timeMilli);
    }

}
