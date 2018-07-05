package test.example.com.devicescheduling.smsManager;

/**
 * Created by Shoukhin on 7/5/2018.
 */

public class SMSManager {
    private static final String APP_NAME = "deviceScheduling";
    private String fullMessage;
    private String image;
    private String sound;
    private String message;

    public SMSManager(String fullMessage) {
        this.fullMessage = fullMessage;
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
                image = splitMessages[1];
                sound = splitMessages[2];
                message = splitMessages[3];
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
