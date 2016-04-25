package demirciy.ygslyspuanhesaplama.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import demirciy.ygslyspuanhesaplama.AllScores;
import demirciy.ygslyspuanhesaplama.model.YgsScore;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "YgsLysDatabase.db";
    public static final String TABLE_NAME = "YgsMarks";
    public static final String COL_11 = "LESSON";
    public static final String COL_12 = "CORRECT";
    public static final String COL_13 = "INCORRECT";
    public static final String COL_14 = "MARK";

    public static final String TABLE_NAME2 = "AllScores";
    public static final String COL_21 = "EXAM_NAME";
    public static final String COL_22 = "EXAM_DATE";
    public static final String COL_23 = "TR_MARK";
    public static final String COL_24 = "SOCIAL_MARK";
    public static final String COL_25 = "MATH1_MARK";
    public static final String COL_26 = "SCIENCE_MARK";
    public static final String COL_27 = "YGS1";
    public static final String COL_28 = "YGS2";
    public static final String COL_29 = "YGS3";
    public static final String COL_30 = "YGS4";
    public static final String COL_31 = "YGS5";
    public static final String COL_32 = "YGS6";
    public static final String COL_33 = "MATH2_MARK";
    public static final String COL_34 = "GEO_MARK";
    public static final String COL_35 = "PHY_MARK";
    public static final String COL_36 = "CHE_MARK";
    public static final String COL_37 = "BIO_MARK";
    public static final String COL_38 = "LITE_MARK";
    public static final String COL_39 = "GEOG1_MARK";
    public static final String COL_40 = "HISTORY_MARK";
    public static final String COL_41 = "GEOG2_MARK";
    public static final String COL_42 = "PHI_MARK";
    public static final String COL_43 = "FOREIGN_MARK";
    public static final String COL_44 = "MF1";
    public static final String COL_45 = "MF2";
    public static final String COL_46 = "MF3";
    public static final String COL_47 = "MF4";
    public static final String COL_48 = "TM1";
    public static final String COL_49 = "TM2";
    public static final String COL_50 = "TM3";
    public static final String COL_51 = "TS1";
    public static final String COL_52 = "TS2";
    public static final String COL_53 = "LANG1";
    public static final String COL_54 = "LANG2";
    public static final String COL_55 = "LANG3";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME
                + "(LESSON TEXT, CORRECT INTEGER, INCORRECT INTEGER, MARK INTEGER)");
        db.execSQL("create table " + TABLE_NAME2
                + "(EXAM_NAME TEXT, EXAM_DATE TEXT, TR_MARK INTEGER, SOCIAL_MARK INTEGER, " +
                "MATH1_MARK INTEGER, SCIENCE_MARK INTEGER, YGS1 INTEGER, YGS2 INTEGER, YGS3 INTEGER, " +
                "YGS4 INTEGER, YGS5 INTEGER, YGS6 INTEGER, MATH2_MARK INTEGER, GEO_MARK INTEGER, " +
                "PHY_MARK INTEGER, CHE_MARK INTEGER, BIO_MARK INTEGER, LITE_MARK INTEGER, " +
                "GEOG1_MARK INTEGER, HISTORY_MARK INTEGER, GEOG2_MARK INTEGER, PHI_MARK INTEGER, " +
                "FOREIGN_MARK INTEGER, MF1 INTEGER, MF2 INTEGER, MF3 INTEGER, MF4 INTEGER, TM1 INTEGER, " +
                "TM2 INTEGER, TM3 INTEGER, TS1 INTEGER, TS2 INTEGER, LANG1 INTEGER, LANG2 INTEGER, LANG3 INTEGER)");
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

    public boolean addAllScore(AllScores allScores) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_21, allScores.getExamName());
        contentValues.put(COL_22, allScores.getExamDate());
        contentValues.put(COL_23, allScores.getTrMark());
        contentValues.put(COL_24, allScores.getSocialMark());
        contentValues.put(COL_25, allScores.getMath1Mark());
        contentValues.put(COL_26, allScores.getScienceMark());
        contentValues.put(COL_27, allScores.getYgs1());
        contentValues.put(COL_28, allScores.getYgs2());
        contentValues.put(COL_29, allScores.getYgs3());
        contentValues.put(COL_30, allScores.getYgs4());
        contentValues.put(COL_31, allScores.getYgs5());
        contentValues.put(COL_32, allScores.getYgs6());
        contentValues.put(COL_33, allScores.getMath2Mark());
        contentValues.put(COL_34, allScores.getGeoMark());
        contentValues.put(COL_35, allScores.getPhyMark());
        contentValues.put(COL_36, allScores.getCheMark());
        contentValues.put(COL_37, allScores.getBioMark());
        contentValues.put(COL_38, allScores.getLiteMark());
        contentValues.put(COL_39, allScores.getGeog1Mark());
        contentValues.put(COL_40, allScores.getHistoryMark());
        contentValues.put(COL_41, allScores.getGeog2Mark());
        contentValues.put(COL_42, allScores.getPhiMark());
        contentValues.put(COL_43, allScores.getForeignMark());
        contentValues.put(COL_44, allScores.getMf1());
        contentValues.put(COL_45, allScores.getMf2());
        contentValues.put(COL_46, allScores.getMf3());
        contentValues.put(COL_47, allScores.getMf4());
        contentValues.put(COL_48, allScores.getTm1());
        contentValues.put(COL_49, allScores.getTm2());
        contentValues.put(COL_50, allScores.getTm3());
        contentValues.put(COL_51, allScores.getTs1());
        contentValues.put(COL_52, allScores.getTs2());
        contentValues.put(COL_53, allScores.getLang1());
        contentValues.put(COL_54, allScores.getLang2());
        contentValues.put(COL_55, allScores.getLang3());

        long result = db.insert(TABLE_NAME2, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllScores() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME2, null);

        return res;
/*
        expScoreData = new String[35];

        int i = 0, c = 0;

        try {

            while (res.moveToNext()) {

                expScoreData[i] = res.getString(c);
                i++;
                c++;
            }

        } catch (Exception e) {

        }

        return expScoreData;*/

    }

    /*
    public boolean updateAllScore(String date, String examName, String marks, String scoreType, String score) {
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
    */



  /*
    public List<YgsScore> getAllScore() {

        List<YgsScore> list = new ArrayList<YgsScore>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME2, null);
        if (cursor.moveToFirst()) {
            do {
                YgsScore ygsScore = new YgsScore();
                ygsScore.setID(cursor.getDouble(0));
                ygsScore.setDate(cursor.getString(1));
                ygsScore.setExamName(cursor.getString(2));
                ygsScore.setMarks(cursor.getDouble(3));
                ygsScore.setScoreType(cursor.getString(4));
                ygsScore.setScore(cursor.getDouble(5));
                list.add(ygsScore);
            } while (cursor.moveToNext());
        }
        return list;
    }
    */  //expandable listview için verileri böyle bir liste atıp exp listview e göndericeksin

    public int getRowCountScore() {
        String countQuery = "SELECT * FROM" + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
        return rowCount;
    }


}
