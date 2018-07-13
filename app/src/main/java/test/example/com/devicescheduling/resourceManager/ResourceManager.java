package test.example.com.devicescheduling.resourceManager;

import android.media.AudioManager;

import test.example.com.devicescheduling.R;

/**
 * Created by Shoukhin on 7/9/2018.
 */

public class ResourceManager {
    public static final Integer[] imageResources = {R.drawable.pic1, R.drawable.pic2,
            R.drawable.pic3, R.drawable.pic4, R.drawable.pic5
    };
    public static final int[] soundResources = {R.raw.sound1, R.raw.sound2
    };
    public static final String[] soundNames = {"Crazy Smile", "Missile Alert"};

    public static int mapImageResource(int imageResourceID) {
        for (int i = 0; i < imageResources.length; i++) {
            if (imageResourceID == imageResources[i])
                return i;
        }
        return 0;
    }

    public static int getMappedImageResourceID(int imageResourceID) {
        if (imageResourceID >= imageResources.length)
            return imageResources[0];

        return imageResources[imageResourceID];
    }

    public static int getSoundFromID(int soundResourceID) {
        if (soundResourceID >= soundResources.length)
            return soundResources[0];

        return soundResources[soundResourceID];
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
