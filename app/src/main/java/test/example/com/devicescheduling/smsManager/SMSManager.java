package test.example.com.devicescheduling.smsManager;

import android.telephony.SmsManager;

import java.io.Serializable;

/**
 * Created by Shoukhin on 7/5/2018.
 */

public class SMSManager implements Serializable {
    private static final String APP_NAME = "deviceScheduling";
    private String fullMessage;
    private String image;
    private String sound;
    private String message;
    private String timestamp;
    private String recieverPhoneNumber;

    public SMSManager() {

    }

    public SMSManager(String fullMessage) {
        this.fullMessage = fullMessage;
    }

    public String getFormattedSMS() {
        String formattedSMS = APP_NAME + "," + timestamp + "," + image + "," + sound +
                "," + message;

        return formattedSMS;
    }

    public boolean isSMSForThisApp() {
        String splitMessages[];
        try {
            splitMessages = fullMessage.split(",");
            if (splitMessages.length == 5 && splitMessages[0].equals(APP_NAME)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void splitMessage(String fullMessage) {
        String[] splitMessages = fullMessage.split(",");
        timestamp = splitMessages[1];
        image = splitMessages[2];
        sound = splitMessages[3];
        message = splitMessages[4];
    }

    public void sendSMS() {
        try {
            String formattedSMS = getFormattedSMS();
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(recieverPhoneNumber, null,
                    formattedSMS, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRecieverPhoneNumber() {
        return recieverPhoneNumber;
    }

    public void setRecieverPhoneNumber(String recieverPhoneNumber) {
        this.recieverPhoneNumber = recieverPhoneNumber;
    }
}
