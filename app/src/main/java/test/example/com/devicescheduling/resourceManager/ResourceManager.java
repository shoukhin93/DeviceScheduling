package test.example.com.devicescheduling.resourceManager;

import android.media.AudioManager;

import test.example.com.devicescheduling.R;

/**
 * Created by Shoukhin on 7/9/2018.
 */

public class ResourceManager {
    public static final Integer[] imageResources = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
            R.drawable.pic4, R.drawable.pic5
    };
    public static final int[] soundResources = {R.raw.crazy_smile
    };

    public static int mapImageResource(int imageResourceID) {
        for (int i = 0; i < imageResources.length; i++) {
            if (imageResourceID == imageResources[i])
                return i;
        }
        return 0;
    }

    public static int getMappedImageResourceID(int imageResourceID) {
        for (int i = 0; i < imageResources.length; i++) {
            if (imageResourceID == imageResources[i])
                return imageResources[i];
        }
        return imageResources[0];
    }

    public static int mapSoundResource(int soundResourceID) {
        for (int i = 0; i < soundResources.length; i++) {
            if (soundResourceID == soundResources[i])
                return i;
        }
        return 0;
    }

    public static int getMappedSoundResourceID(int soundResourceID) {
        for (int i = 0; i < soundResources.length; i++) {
            if (soundResourceID == soundResources[i])
                return soundResources[i];
        }
        return soundResources[0];
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
