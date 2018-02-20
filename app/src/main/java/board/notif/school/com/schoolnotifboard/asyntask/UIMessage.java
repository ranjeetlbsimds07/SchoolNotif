package board.notif.school.com.schoolnotifboard.asyntask;

/**
 * Created by Guest User on 1/30/2018.
 */

public class UIMessage {

    // private IScreenView view;
    private Object screenData;
    private int responseCode;
    private byte command;
    private byte mode;

    public String getScreenDataString() {
        if(screenData != null && screenData instanceof String){
            return (String) screenData;
        }
        return "";
    }

    public Object getScreenData() {
        return screenData;
    }

    public UIMessage setScreenData(Object screenData) {
        this.screenData = screenData;
        return this;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public UIMessage setResponseCode(int responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public byte getCommand() {
        return command;
    }

    public UIMessage setCommand(byte command) {
        this.command = command;
        return this;
    }

    public byte getMode() {
        return mode;
    }

    public UIMessage setMode(byte mode) {
        this.mode = mode;
        return this;
    }
}
