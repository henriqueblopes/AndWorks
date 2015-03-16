package com.example.henrique.limpadorapp;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Henrique on 14/03/2015.
 */
public class SDCardUtils {
    private static final String TAG = SDCardUtils.class.getName();

    public static File getSDCardFile(String dirName, String fileName) {
        File sdCard = android.os.Environment.getExternalStorageDirectory();
        sdCard = android.os.Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        File dir = new File(sdCard, dirName);
        if(!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(dir, fileName);
        return file;
    }

    public static File writeToSdCard(File f, byte[] bytes) {
        try {
            if(f != null) {
                FileOutputStream out = new FileOutputStream(f);
                out.write(bytes);
                out.close();
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return f;
    }
}
