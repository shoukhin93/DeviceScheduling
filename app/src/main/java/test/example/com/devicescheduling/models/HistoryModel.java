package test.example.com.devicescheduling.models;

/**
 * Created by Shoukhin on 7/9/2018.
 */

public class HistoryModel {
    private int imageResourceID;
    private String message;
    private String setterName;
    private String timestamp;

    public HistoryModel(){

    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    public void setImageResourceID(int imageResourceID) {
        this.imageResourceID = imageResourceID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSetterName() {
        return setterName;
    }

    public void setSetterName(String setterName) {
        this.setterName = setterName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
