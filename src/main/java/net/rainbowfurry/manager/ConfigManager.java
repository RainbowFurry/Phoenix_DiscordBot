package net.rainbowfurry.manager;

import java.io.*;
import java.util.Properties;

public class ConfigManager {

    //ToDo Rework

    private final Properties properties = new Properties();

    public ConfigManager() throws IOException {
        File dir = new File("Configs");
        File file = new File(dir, "config.txt");
        if (!dir.exists())
            dir.mkdirs();
        if (!file.exists() &&
                file.createNewFile())
            try {
                OutputStream outputStream = new FileOutputStream(file);
                this.properties.setProperty("token", "YOUR_TOKEN");
                this.properties.setProperty("activityType", "ONLINE");
                this.properties.setProperty("activityText", "AktivityText");
                this.properties.setProperty("DB_Host", "localhost");
                this.properties.setProperty("DB_Database", "BotName");
                this.properties.setProperty("DB_Port", "3306");
                this.properties.setProperty("DB_UserName", "UserName");
                this.properties.setProperty("DB_Password", "Password");
                this.properties.store(outputStream, (String)null);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        InputStream inputStream = new FileInputStream(file);
        this.properties.load(inputStream);
    }

    public String getKey(String key) {
        return this.properties.getProperty(key);
    }

}
