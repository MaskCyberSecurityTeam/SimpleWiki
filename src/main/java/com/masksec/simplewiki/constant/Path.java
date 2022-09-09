package com.masksec.simplewiki.constant;

import cn.hutool.system.SystemUtil;

import java.io.File;

/**
 * 路径常量
 *
 * @author RichardTang
 */
public class Path {

    // 工作目录
    public static final String WORK_DIR = System.getProperty(SystemUtil.USER_DIR);

    // 存放markdown文章的目录
    public static final File LIBRARY_DIR = new File(WORK_DIR + File.separator + "library" + File.separator);

    // 存放资源的目录(图片)
    public static final File RESOURCE_DIR = new File(WORK_DIR + File.separator + "resource" + File.separator);
}