package com.example.maogai.examination;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Debug;

import com.example.maogai.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//在线测试专用数据库操作类
public class ExamDao {
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public ExamDao(Context context) {
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

    //随机获得10道题目
    public List<Question> RandomGetTopic() {
        List<Question> questions = new ArrayList<>();
        if (sqLiteDatabase == null) {
            openDatabase();
        }
        if (sqLiteDatabase != null){
            //先查询数据表
            Cursor cursor1 = sqLiteDatabase.query(DbUtil.EXAM_NAME, null, null, null, null,
                    null, null,null);
            //得到数据表的行数
            int count = cursor1.getCount();
            //随机得到题目
            Random random = new Random();
            int index = random.nextInt(count - 10);

            Cursor cursor = sqLiteDatabase.query(DbUtil.EXAM_NAME, null, null, null, null,
                    null, null,index + "," + 10);

            while (cursor != null&&cursor.moveToNext()) {
                questions.add(new Question(cursor.getString(1), cursor.getString(2), cursor.getString( 3),
                        cursor.getString(4), cursor.getString(5), cursor.getInt(6)));
            }
            cursor.close();
        }
        closeDatabase();
        return questions;
    }
}
