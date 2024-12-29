package net.rainbowfurry.manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class LogManager {

    //ToDo Rework just the first basic Version for fast testing
    private String _dir = "Log";

    private File _file;

    private String LogString = "%Date% | %LogType% | %Priority% | %Content%";

    public LogManager() {
        this._file = new File(this._dir, (String.valueOf(new Date()) + ".log").replaceAll(" ", "_").replace(":", "-"));
        File dir = new File(this._dir);
        if (!dir.exists())
            dir.mkdirs();
        if (!this._file.exists())
            try {
                this._file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void Log(LogType logType, int priority, String content) {
        String _content = this.LogString.replace("%Date%", (new Date()).toString()).replace("%LogType%", logType.toString()).replace("%Priority%", String.valueOf(priority)).replace("%Content%", content);
        try {
            FileWriter fw = new FileWriter(this._file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(_content);
            bw.newLine();
            bw.close();
            System.out.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public enum LogType {
        INFO, SYSTEM, DEBUG, WARNING, FATAL, ERROR;
    }

}
