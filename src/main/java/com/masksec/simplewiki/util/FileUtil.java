package com.masksec.simplewiki.util;

import java.io.File;
import java.util.function.Consumer;

/**
 * FileUtil扩展
 *
 * @author RichardTang
 */
public class FileUtil extends cn.hutool.core.io.FileUtil {

    /**
     * 递归遍历指定目录下的文件和文件夹
     *
     * @param file     需要遍历的文件夹
     * @param consumer 每个file对象需要处理的动作
     */
    public static void loopFileAndDir(File file, Consumer<File> consumer) {
        if (file == null) {
            return;
        }
        if (file.isDirectory()) {
            consumer.accept(file);
            File[] files = file.listFiles();
            for (File f : files) {
                loopFileAndDir(f, consumer);
            }
        } else {
            consumer.accept(file);
        }
    }
}