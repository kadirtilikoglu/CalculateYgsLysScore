package demirciy.ygslyspuanhesaplama;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "YgsLysScoresyu.db";
    public static final String TABLE_NAME = "YgsMarks";
    public static final String COL_11 = "LESSON";
    public static final String COL_12 = "CORRECT";
    public static final String COL_13 = "INCORRECT";
    public static final String COL_14 = "MARK";

    public static final String TABLE_NAME2 = "YgsScores";
    public static final String COL_21 = "DATE";
    public static final String COL_22 = "EXAM_NAME";
    public static final String COL_23 = "MARKS";
    public static final String COL_24 = "SCORE_TYPE";
    public static final String COL_25 = "SCORE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME
                + "(LESSON TEXT, CORRECT INTEGER, INCORRECT INTEGER, MARK INTEGER) ");
        db.execSQL("create table " + TABLE_NAME2
                + "(DATE TEXT, EXAM_NAME TEXT,MARKS INTEGER, SCORE_TYPE TEXT, SCORE INTEGER) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME2);
        onCreate(db);
    }

    public boolean ygsAddMark(String lesson, String correct, String incorrect, String mark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_11, lesson);
        contentValues.put(COL_12, correct);
        contentValues.put(COL_13, incorrect);
        contentValues.put(COL_14, mark);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean ygsUpdateMark(String lesson, String correct, String incorrect, String mark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_11, lesson);
        contentValues.put(COL_12, correct);
        contentValues.put(COL_13, incorrect);
        contentValues.put(COL_14, mark);

        db.update(TABLE_NAME, contentValues, "LESSON = ?", new String[]{lesson});
        return true;
    }


    public Cursor ygsGetMark() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean ygsAddScore(String date, String examName, String marks, String scoreType, String score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_21, date);
        contentValues.put(COL_22, examName);
        contentValues.put(COL_23, marks);
        contentValues.put(COL_24, scoreType);
        contentValues.put(COL_25, score);

        long result = db.insert(TABLE_NAME2, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean ygsUpdateScore(String date, String examName, String marks, String scoreType, String score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_21, date);
        contentValues.put(COL_22, examName);
        contentValues.put(COL_23, marks);
        contentValues.put(COL_24, scoreType);
        contentValues.put(COL_25, score);

        db.update(TABLE_NAME2, contentValues, "EXAM_NAME = ?", new String[]{examName});
        return true;
    }

    public Cursor ygsGetScore() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME2, null);
        return res;
    }
}
