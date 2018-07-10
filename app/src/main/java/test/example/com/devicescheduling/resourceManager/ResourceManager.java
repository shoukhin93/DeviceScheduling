package test.example.com.devicescheduling.resourceManager;

import android.media.AudioManager;

import test.example.com.devicescheduling.R;

/**
 * Created by Shoukhin on 7/9/2018.
 */

public class ResourceManager {

    public static int mapImageResource(int imageResourceID) {
        switch (imageResourceID) {
            case R.drawable.pic1:
                return 1;
            case R.drawable.pic2:
                return 2;
            default:
                return 1;
        }
    }

    public static int getMappedImageResourceID(int imageResourceID) {
        switch (imageResourceID) {
            case 1:
                return R.drawable.pic1;
            case 2:
                return R.drawable.pic2;
            default:
                return R.drawable.pic1;
        }
    }

    public static int mapSoundResource(int soundResourceID) {
        switch (soundResourceID) {
            case R.raw.crazy_smile:
                return 1;
            default:
                return 1;
        }
    }

    public static int getMappedSoundResourceID(int soundResourceID) {
        switch (soundResourceID) {
            case 1:
                return R.raw.crazy_smile;
            default:
                return R.raw.crazy_smile;
        }
    }

    public static int mapPhoneStatusFromName(String status) {
        switch (status) {
            case "Silent":
                return AudioManager.RINGER_MODE_SILENT;
            case "Vibrate":
                return AudioManager.RINGER_MODE_VIBRATE;
            case "Ringing":
                return AudioManager.RINGER_MODE_NORMAL;
            default:
                return -1;
        }
    }
}
