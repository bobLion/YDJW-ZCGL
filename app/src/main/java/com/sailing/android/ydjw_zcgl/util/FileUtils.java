package com.sailing.android.ydjw_zcgl.util;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by eagle on 2017-10-15 22:33
 */

public class FileUtils {

    /**
     * @param filePath 文件路径
     * @return 如果是文件则返回文件是否存在，如果是文件夹则返回false
     * @Description ：判断文件是否存在
     */
    public static boolean isFileExist(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return false;
        }

        File file = new File(filePath);
        return (file.exists() && file.isFile());
    }

    public static boolean copyFileFromAssets(Context context, String fileName,
                                      String path) {
        boolean copyIsFinish = false;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = context.getAssets().open(fileName);
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            copyIsFinish = true;
        }
        return copyIsFinish;
    }
}
