package com.example.maogai.sqliteUtil;

import android.os.Environment;

//存放数据库操作的静态变量
public class DbUtil {
    public static final int BUFFER_SIZE = 8192;
    public static final String DB_NAME = "maogai.db";
    public static final String PACKAGE_NAME = "com.example.maogai";
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME + "/" + DB_NAME;
    public static final String EXAM_NAME = "subject";//题目表名
    public static final String HISTORY_NAME = "history";//浏览历史表名
    public static final String COLLECTION_NAME = "collection";//收藏表名
}
