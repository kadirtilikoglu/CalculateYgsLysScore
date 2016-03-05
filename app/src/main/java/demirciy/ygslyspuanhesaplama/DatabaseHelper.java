package demirciy.ygslyspuanhesaplama;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "YgsLysPuanHesaplama.db";
    public static final String TABLE_NAME = "YgsNetler";
    public static final String COL_1 = "DERS";
    public static final String COL_2 = "NET";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NAME
                + "(DERS TEXT, NET INTEGER) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }

    public boolean ygsNetEkle(String ders, String net)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, ders);
        contentValues.put(COL_2, net);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean ygsNetGuncelle(String ders, String net)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, ders);
        contentValues.put(COL_2, net);

        db.update(TABLE_NAME, contentValues, "DERS = ?", new String[] {ders});
        return true;
    }

    public Cursor ygsNetleriAl()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
