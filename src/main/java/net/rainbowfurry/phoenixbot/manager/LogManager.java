package net.rainbowfurry.phoenixbot.manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class LogManager {

    //ToDo Rework just the first basic Version for fast testing
    // In config file if multi or single log - eine file für ein tag, oder pro restart
    //In config Log Format.
    private final String _dir = "Log";
    private File _file;
    private final String LogString = "%Date% | %LogType% | %Priority% | %Content%";

    public LogManager() {
        this._file = new File(this._dir, ((new Date()) + ".log").replaceAll(" ", "_").replace(":", "-"));
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

    public void Log(String content){
        String _content = this.LogString.replace("%Date%", (new Date()).toString()).replace("%LogType%", LogType.SYSTEM.toString()).replace("%Priority%", String.valueOf(1)).replace("%Content%", content);
        save(_content);
    }

    public void Info(String content){
        String _content = this.LogString.replace("%Date%", (new Date()).toString()).replace("%LogType%", LogType.INFO.toString()).replace("%Priority%", String.valueOf(1)).replace("%Content%", content);
        save(_content);
    }

    public void Debug(String content){
        String _content = this.LogString.replace("%Date%", (new Date()).toString()).replace("%LogType%", LogType.DEBUG.toString()).replace("%Priority%", String.valueOf(1)).replace("%Content%", content);
        save(_content);
    }

    public void System(String content){
        String _content = this.LogString.replace("%Date%", (new Date()).toString()).replace("%LogType%", LogType.SYSTEM.toString()).replace("%Priority%", String.valueOf(1)).replace("%Content%", content);
        save(_content);
    }

    public void Warning(String content){
        String _content = this.LogString.replace("%Date%", (new Date()).toString()).replace("%LogType%", LogType.WARNING.toString()).replace("%Priority%", String.valueOf(1)).replace("%Content%", content);
        save(_content);
    }

    public void Fatal(String content){
        String _content = this.LogString.replace("%Date%", (new Date()).toString()).replace("%LogType%", LogType.FATAL.toString()).replace("%Priority%", String.valueOf(8)).replace("%Content%", content);
        save(_content);
    }

    public void Error(String content){
        String _content = this.LogString.replace("%Date%", (new Date()).toString()).replace("%LogType%", LogType.ERROR.toString()).replace("%Priority%", String.valueOf(10)).replace("%Content%", content);
        save(_content);
    }

    public void Error(Exception exception){
        String _content = this.LogString.replace("%Date%", (new Date()).toString()).replace("%LogType%", LogType.SYSTEM.toString()).replace("%Priority%", String.valueOf(1)).replace("%Content%", exception.getMessage());
        save(_content);
    }

    public void Log(Exception exception){
        String _content = this.LogString.replace("%Date%", (new Date()).toString()).replace("%LogType%", LogType.ERROR.toString()).replace("%Priority%", String.valueOf(4)).replace("%Content%", exception.getMessage());
        save(_content);
    }

    public void Log(LogType logType, int priority, String content) {
        String _content = this.LogString.replace("%Date%", (new Date()).toString()).replace("%LogType%", logType.toString()).replace("%Priority%", String.valueOf(priority)).replace("%Content%", content);
        save(_content);
    }

    private void save(String content){
        try {
            FileWriter fw = new FileWriter(this._file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
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
