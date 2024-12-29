package net.rainbowfurry.phoenixbot.builder;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class CustomEmbedBuilder {

    private EmbedBuilder embedBuilder;

    /**
     * Init CustomEmbedBuilder
     */
    public CustomEmbedBuilder(){
        embedBuilder = new EmbedBuilder();
    }

    /**
     * Set the Color for the Embed on the left Side
     * @param color Color
     * @return base Class
     */
    public CustomEmbedBuilder setEmbedColor(Color color){
        embedBuilder.setColor(color);
        return this;
    }

    /**
     * Set the Color for the Ebed by Hex Code #fff
     * @param color Color as Hey String
     * @return base Class
     */
    public CustomEmbedBuilder setEmbedColor(String color){
        if(color.startsWith("#"))
            embedBuilder.setColor(Color.decode(color));
        else
            embedBuilder.setColor(Color.decode("#07e843"));
        return this;
    }

    /**
     * Set the Title for the Embed
     * @param title String Title
     * @return base Class
     */
    public CustomEmbedBuilder setTitle(String title){
        embedBuilder.setTitle(title);
        return this;
    }

    /**
     * Set the Content for the Embed to display
     * @param content String Content
     * @return base Class
     */
    public CustomEmbedBuilder setContent(String content){
        embedBuilder.setDescription(content);
        return this;
    }

    /**
     * Set the Image of the Embed
     * @param url String ImageURL
     * @return base Class
     */
    public CustomEmbedBuilder setImage(String url){
        embedBuilder.setImage(url);
        return this;
    }

    /**
     * Set the Thumbnail of the Embed
     * @param url String ThumbnailURL
     * @return base Class
     */
    public CustomEmbedBuilder setThumbnail(String url){
        embedBuilder.setThumbnail(url);
        return this;
    }

    /**
     * Set the Author of the Embed
     * @param author String Author
     * @return base Class
     */
    public CustomEmbedBuilder setAuthor(String author){
        embedBuilder.setAuthor(author);
        return this;
    }

    /**
     * Set the Footer for the Embed
     * @param footer String Footer
     * @return base Class
     */
    public CustomEmbedBuilder setFooter(String footer){
        embedBuilder.setFooter(footer);
        return this;
    }

    public CustomEmbedBuilder addField(String title, String content, boolean inline){
        embedBuilder.addField(title, content, inline);
        return this;
    }

    public CustomEmbedBuilder addBlankField(boolean inline){
        embedBuilder.addBlankField(inline);
        return this;
    }

    /**
     * Build the Embed and Return
     * @return MessageEmbed
     */
    public MessageEmbed build(){
        return embedBuilder.build();
    }

}
