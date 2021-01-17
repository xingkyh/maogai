package com.example.maogai.sqliteUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.maogai.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SqlUtil {
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public SqlUtil(Context context ){
        openDatabase();
        this.context = context;
    }

    public void openDatabase(){
        if (sqLiteDatabase != null){
            return;
        }
        try {//判断数据库文件是否存在，若不存在则导入
            if (!(new File(DbUtil.DB_PATH)).exists()){
                InputStream is = this.context.getResources().openRawResource(
                        R.raw.maogai); //欲导入的数据库
                FileOutputStream fos = new FileOutputStream(DbUtil.DB_PATH);
                byte[] buffer = new byte[DbUtil.BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(DbUtil.DB_PATH,null);
    }

    public void closeDatabase() {
        //判断如果打开了就关闭
        if (sqLiteDatabase != null) {
            //SQLiteDatabase的close方法
            sqLiteDatabase.close();
            sqLiteDatabase = null;
        }
    }

    //获取所有浏览历史
    public List<SqlPage> getALLHistory(){
        List<SqlPage> sqlPages = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(DbUtil.HISTORY_NAME, null, null,
                null, null, null, null);
        while (cursor != null && cursor.moveToNext()){
            sqlPages.add(new SqlPage(cursor.getString(1),
                    cursor.getString(2), cursor.getString(3)));
        }
        return sqlPages;
    }

    //获取所有收藏
    public List<SqlPage> getALLCollection(){
        List<SqlPage> sqlPages = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(DbUtil.COLLECTION_NAME, null, null,
                null, null, null, null);
        while (cursor != null && cursor.moveToNext()){
            sqlPages.add(new SqlPage(cursor.getString(1),
                    cursor.getString(2), cursor.getString(3)));
        }
        return sqlPages;
    }

    //插入历史
    public void addHistory(SqlPage sqlPage){
        //删除原有的历史
        sqLiteDatabase.delete(DbUtil.HISTORY_NAME, "chapter=?", new String[]{sqlPage.getChapter()});
        ContentValues values = new ContentValues();
        values.put("chapter", sqlPage.getChapter());
        values.put("name", sqlPage.getName());
        values.put("url", sqlPage.getUrl());
        sqLiteDatabase.insert(DbUtil.HISTORY_NAME, null, values);
    }

    //判断是否收藏
    public boolean isCollection(String chapter){
        Cursor cursor = sqLiteDatabase.query(DbUtil.COLLECTION_NAME, null, "chapter=?",
                new String[]{chapter},null, null, null);
        return cursor.getCount() > 0;
    }

    //添加收藏
    public void addCollection(SqlPage sqlPage){
        ContentValues values = new ContentValues();
        values.put("chapter", sqlPage.getChapter());
        values.put("name", sqlPage.getName());
        values.put("url", sqlPage.getUrl());
        sqLiteDatabase.insert(DbUtil.COLLECTION_NAME, null, values);
    }

    //删除收藏
    public void deleteCollection(String chapter){
        sqLiteDatabase.delete(DbUtil.COLLECTION_NAME, "chapter=?", new String[]{chapter});
    }
}
