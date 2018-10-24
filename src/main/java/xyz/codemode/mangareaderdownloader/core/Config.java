package xyz.codemode.mangareaderdownloader.core;

import xyz.codemode.util.FileUtility;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Config {
    public static void init() {}

    private static File configFile;

    static {
        configFile = new File("./cfg/config.json");
        if(!configFile.exists())
            configFile.getParentFile().mkdirs();

        try {
            String jsonContent = FileUtility.loadAsString(configFile);
            JSONObject object = new JSONObject(jsonContent);
            windowX = object.getInt("WINDOW_X");
            windowY = object.getInt("WINDOW_Y");
            directory = object.getString("DIRECTORY");
        } catch (IOException e) {
            //file not found; set default values; create new json file
            setDefault();
            saveConfig();
        } catch (JSONException e) {
            //file does not contain specific key
            setDefault();
            saveConfig();
        }
    }

    private static void setDefault() {
        windowX = 500;
        windowY = 300;
        directory = "";
    }

    public static void saveConfig() {
        JSONStringer stringer = new JSONStringer();
        stringer.object();
        stringer.key("WINDOW_X");
        stringer.value(windowX);
        stringer.key("WINDOW_Y");
        stringer.value(windowY);
        stringer.key("DIRECTORY");
        stringer.value(directory);
        stringer.endObject();
        try {
            FileUtility.saveTextFile(configFile,stringer.toString());
        } catch (FileNotFoundException exc) {
            exc.printStackTrace();
        }
    }

    public static int getWindowX() { return windowX; }
    public static int getWindowY() { return windowY; }
    public static String getDirectory() { return directory; }

    public static void setWindowX(int arg) { windowX = arg; }
    public static void setWindowY(int arg) { windowY = arg; }
    public static void setDirectory(String arg) { directory = arg; }

    private static int windowX;
    private static int windowY;
    private static String directory;
}
