package test.example.com.devicescheduling.smsManager;

import android.util.Log;

import test.example.com.devicescheduling.Constants;

/**
 * Created by Shoukhin on 7/5/2018.
 */

public class SMSManager {
    private static final String APP_NAME = "deviceScheduling";
    private String fullMessage;
    private String image;
    private String sound;
    private String message;

    public SMSManager(String message) {
        this.message = message;
    }

    public String getFormattedSMS(String image, String sound, String message) {
        String formattedSMS = APP_NAME + "," + image + "," + sound +
                "," + message;

        return formattedSMS;
    }

    public boolean isSMSForThisApp() {
        String splitMessages[];
        try {
            splitMessages = fullMessage.split(",");
            if (splitMessages.length == 4 && splitMessages[0].equalsIgnoreCase(APP_NAME)) {
                splitMessages[1] = image;
                splitMessages[2] = sound;
                splitMessages[3] = message;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
