package demirciy.ygslyspuanhesaplama.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demirciy.ygslyspuanhesaplama.model.AllScores;

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

    public static final String TABLE_NAME3 = "YGSDatasForLYS ";
    public static final String COL_56 = "TR_MARK";
    public static final String COL_57 = "SOCIAL_MARK";
    public static final String COL_58 = "MATH1_MARK";
    public static final String COL_59 = "SCIENCE_MARK";
    public static final String COL_60 = "YGS1";
    public static final String COL_61 = "YGS2";
    public static final String COL_62 = "YGS3";
    public static final String COL_63 = "YGS4";
    public static final String COL_64 = "YGS5";
    public static final String COL_65 = "YGS6";

    private ArrayList<String> Headers;
    private HashMap<String, List<String>> Datas;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
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
        db.execSQL("create table " + TABLE_NAME3
                + "(TR_MARK INTEGER, SOCIAL_MARK INTEGER, MATH1_MARK INTEGER, SCIENCE_MARK INTEGER," +
                "YGS1 INTEGER, YGS2 INTEGER, YGS3 INTEGER, YGS4 INTEGER, YGS5 INTEGER, YGS6 INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME3);
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

    public ArrayList<String> getAllHeadersFromDb() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME2, null);

        Headers = new ArrayList<>();

        while (res.moveToNext()) {

            Headers.add(res.getString(0) + " / " + res.getString(1));

        }

        return Headers;
    }

    public HashMap<String, List<String>> getAllDatasFromDb() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME2, null);

        Datas = new HashMap<>();

        int b = 0;
        while (res.moveToNext()) {
            ArrayList<String> Child = new ArrayList<>();
            Child.add(res.getString(2));
            Child.add(res.getString(3));
            Child.add(res.getString(4));
            Child.add(res.getString(5));
            Child.add(res.getString(6));
            Child.add(res.getString(7));
            Child.add(res.getString(8));
            Child.add(res.getString(9));
            Child.add(res.getString(10));
            Child.add(res.getString(11));
            Child.add(res.getString(12));
            Child.add(res.getString(13));
            Child.add(res.getString(14));
            Child.add(res.getString(15));
            Child.add(res.getString(16));
            Child.add(res.getString(17));
            Child.add(res.getString(18));
            Child.add(res.getString(19));
            Child.add(res.getString(20));
            Child.add(res.getString(21));
            Child.add(res.getString(22));
            Child.add(res.getString(23));
            Child.add(res.getString(24));
            Child.add(res.getString(25));
            Child.add(res.getString(26));
            Child.add(res.getString(27));
            Child.add(res.getString(28));
            Child.add(res.getString(29));
            Child.add(res.getString(30));
            Child.add(res.getString(31));
            Child.add(res.getString(32));
            Child.add(res.getString(33));
            Child.add(res.getString(34));

            Datas.put(Headers.get(b), Child);
            b++;

        }

        return Datas;
    }

    public void deleteDatas(int position) {
        SQLiteDatabase db = this.getWritableDatabase();

        String examName;

        Cursor res = db.rawQuery("select * from " + TABLE_NAME2, null);

        res.moveToPosition(position);

        examName = res.getString(0);

        db.delete(TABLE_NAME2, "EXAM_NAME = ?", new String[]{examName});
    }

    public void renameDatas(int position, String newExamName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String oldExamName;

        Cursor res = db.rawQuery("select * from " + TABLE_NAME2, null);

        res.moveToPosition(position);

        oldExamName = res.getString(0);

        contentValues.put(COL_21, newExamName);

        db.update(TABLE_NAME2, contentValues, "EXAM_NAME = ?", new String[]{oldExamName});
    }

    public boolean addYgsDatasForLys(AllScores allScores) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_56, allScores.getTrMark());
        contentValues.put(COL_57, allScores.getSocialMark());
        contentValues.put(COL_58, allScores.getMath1Mark());
        contentValues.put(COL_59, allScores.getScienceMark());
        contentValues.put(COL_60, allScores.getYgs1());
        contentValues.put(COL_61, allScores.getYgs2());
        contentValues.put(COL_62, allScores.getYgs3());
        contentValues.put(COL_63, allScores.getYgs4());
        contentValues.put(COL_64, allScores.getYgs5());
        contentValues.put(COL_65, allScores.getYgs6());

        long result = db.insert(TABLE_NAME3, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean updateYgsDatasForLys(AllScores allScores) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_56, allScores.getTrMark());
        contentValues.put(COL_57, allScores.getSocialMark());
        contentValues.put(COL_58, allScores.getMath1Mark());
        contentValues.put(COL_59, allScores.getScienceMark());
        contentValues.put(COL_60, allScores.getYgs1());
        contentValues.put(COL_61, allScores.getYgs2());
        contentValues.put(COL_62, allScores.getYgs3());
        contentValues.put(COL_63, allScores.getYgs4());
        contentValues.put(COL_64, allScores.getYgs5());
        contentValues.put(COL_65, allScores.getYgs6());

        db.update(TABLE_NAME3, contentValues, null, null);

        return true;
    }

    public Cursor getYgsDatasForLys() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME3, null);

        return res;
    }
}
