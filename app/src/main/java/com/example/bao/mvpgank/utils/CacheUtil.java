package com.example.bao.mvpgank.utils;

import android.os.Environment;

import com.example.bao.mvpgank.app.App;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by BAO on 2017/7/11.
 */

public class CacheUtil {

    private static final long CACHE_TIME = 60 * 60 * 24 * 1000;

    public static <T> boolean saveObject(List<T> list, String file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = App.getContext().openFileOutput(file, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> List<T> readObject(String file) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = App.getContext().openFileInput(file);
            ois = new ObjectInputStream(fis);
            return (List<T>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void clearCache() {
        deleteDir(App.getContext().getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(App.getContext().getExternalCacheDir());
        }
    }

    public static void clearFiles() {
        deleteDir(App.getContext().getFilesDir());
    }


    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /*
    * 判断文件时间是否超过一天，是则更新数据
    * */
    public static boolean isCacheDataFailure(String cachefile) {
        boolean failure = false;
        File data = App.getContext().getFileStreamPath(cachefile);
        if (data.exists()
                && (System.currentTimeMillis() - data.lastModified()) > CACHE_TIME)
            failure = true;
        else if (!data.exists())
            failure = true;
        return failure;
    }


}
