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

//bu class ile sqlite işlemleri yapılır
//veri tabanındaki tablolara ekleme çıkarma yapılır
public class DatabaseHelper extends SQLiteOpenHelper {
    //veri tabanının adı
    public static final String DATABASE_NAME = "YgsLysDatabase.db";

    //ygs doğru, yanlış ve netlerini kaydeden ilk tablo
    public static final String TABLE_NAME = "YgsMarks";
    public static final String COL_11 = "LESSON";
    public static final String COL_12 = "CORRECT";
    public static final String COL_13 = "INCORRECT";
    public static final String COL_14 = "MARK";

    //bütün net ve puanların kaydedildiği tablo
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

    //lys activity sine geçerken ygs net ve puanları kaybolmasın diye oluşturulan tablo
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
    public static final String COL_66 = "MF1";
    public static final String COL_67 = "MF2";
    public static final String COL_68 = "MF3";
    public static final String COL_69 = "MF4";
    public static final String COL_70 = "TM1";
    public static final String COL_71 = "TM2";
    public static final String COL_72 = "TM3";
    public static final String COL_73 = "TS1";
    public static final String COL_74 = "TS2";
    public static final String COL_75 = "LANG1";
    public static final String COL_76 = "LANG2";
    public static final String COL_77 = "LANG3";

    private ArrayList<String> Headers;
    private HashMap<String, List<String>> Datas;

    public DatabaseHelper(Context context) {
        //en sağdaki parametre veritabanınızın sürümüdür
        //uygulamanız ilk yayınlandığı zaman 1 dir
        //uygulamanızı her güncellemek istediğinizde 1 arttırmak zorundasınız
        //arttırmazsanız veri tabanı işlemlerinde hata verir
        super(context, DATABASE_NAME, null, 8);
    }

    //tabloları oluşturur
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
                "YGS1 INTEGER, YGS2 INTEGER, YGS3 INTEGER, YGS4 INTEGER, YGS5 INTEGER, YGS6 INTEGER," +
                "MF1 INTEGER, MF2 INTEGER, MF3 INTEGER, MF4 INTEGER, TM1 INTEGER, TM2 INTEGER" +
                "TM3 INTEGER, TS1 INTEGER, TS2 INTEGER, LANG1 INTEGER, LANG2 INTEGER, LANG3 INTEGER)");
    }

    //bir tabloda herhangi bir veriyi güncellerken eğer tablo mevcutsa tabloyu siler ve güncellenmiş halini tekrar oluşturur
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
            db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME2);
            db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME3);
            onCreate(db);
        } catch (Exception e) {
        }
    }

    //ygs doğru, yanlış ve net lerini veritabanına ekler
    public boolean ygsAddMark(String lesson, String correct, String incorrect, String mark) {
        //veritabanında işlem yapmak için veritabanı erişimini sağlar
        SQLiteDatabase db = this.getWritableDatabase();
        //girilecek içerikleri veri tabanına eklemek için kullanılır. bir nevi arabulucu
        ContentValues contentValues = new ContentValues();

        //1. satır 1. sütuna lesson değerini ekler
        contentValues.put(COL_11, lesson);
        //1. satır 2. sütuna correct değerini ekler
        contentValues.put(COL_12, correct);
        //1. satır 3. sütuna incorrect değerini ekler
        contentValues.put(COL_13, incorrect);
        //1. satır 4. sütuna mark değerini ekler
        contentValues.put(COL_14, mark);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    //ygs doğru, yanlış ve net lerini eğer tablo varsa üstüne günceller
    public boolean ygsUpdateMark(String lesson, String correct, String incorrect, String mark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_11, lesson);
        contentValues.put(COL_12, correct);
        contentValues.put(COL_13, incorrect);
        contentValues.put(COL_14, mark);

        //veriyi update etmek için lesson değişkenini kullanır
        //örneğin türkçe, 25, 4, 24 değerleri sırasıyla ders adı, correct, incorrect ve mark tır
        //lesson değişkenini yani türkçe stringini kullanarak tabloda türkçe kelimesini arar
        //bulduğu satırda diğer değerleri günceller
        db.update(TABLE_NAME, contentValues, "LESSON = ?", new String[]{lesson});

        return true;
    }

    //ygs netlerini tablodan çeker
    //bu metod sadece cursor u döndürür
    public Cursor ygsGetMark() {
        SQLiteDatabase db = this.getWritableDatabase();
        //1. tablodan curson döndürür
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);

        return res;
    }

    //deneme adı ve tarihiyle beraber denemeyi veri tabanına kaydeder
    public boolean addAllScore(AllScores allScores) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //allScores nesneyi yardımıyla getter metodlarını kullanarak verileri alır ve put ile tabloya ekler
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

    //expandable listview deki başlıklar için tablodan sınav adı ve tarihini çeker
    public ArrayList<String> getAllHeadersFromDb() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME2, null);

        Headers = new ArrayList<>();

        while (res.moveToNext()) {
            //0 veriyi ilk sütundan çeker, 1 ise 2. sütundan
            //0 sınav adıdır, 1 ise sınav tarihi
            //bu 2 stringi birleştirip headers arraylist ine atar
            Headers.add(res.getString(0) + " / " + res.getString(1));
        }

        return Headers;
    }

    //tablodan sadece sınav adını çeker
    public ArrayList<String> getAllHeadersFromDb2() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME2, null);

        Headers = new ArrayList<>();

        while (res.moveToNext()) {
            //ilk sütun için 0 ı kullanır yani sınav adını çeker
            Headers.add(res.getString(0));
        }

        return Headers;
    }

    //expandable listview deki child(net ve puanlar) ları çekip parentlara(sınav adı ve tarihleri) atar
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

            //hashmap ile veriler bir başlık altında toplanılır
            //hashmap datas(deneme1, deneme2)
            //datas(deneme1, deneme1 deki bütün net ve puanlar)
            //datas(deneme2, deneme2 deki bütün net ve puanlar)
            Datas.put(Headers.get(b), Child);
            b++;
        }

        return Datas;
    }

    //deneme siler
    public void deleteDatas(int position) {
        SQLiteDatabase db = this.getWritableDatabase();

        String examName;

        Cursor res = db.rawQuery("select * from " + TABLE_NAME2, null);

        //silmek istediğimiz denemenin expandable listviewdeki pozisyonuna göre cursor o pozisyona gider
        res.moveToPosition(position);

        //o pozisyondaki string değeri yani deneme adı alınır
        examName = res.getString(0);

        //silinmek istenen denemeyi adına göre aratıp siler
        //deneme adı benzersiz olmalıdır yoksa aynı ada sahip bütün denemeleri siler
        db.delete(TABLE_NAME2, "EXAM_NAME = ?", new String[]{examName});
    }

    //denemeyi yeniden adlandırır
    public void renameDatas(int position, String newExamName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String oldExamName;

        Cursor res = db.rawQuery("select * from " + TABLE_NAME2, null);

        //silmek istediğimiz denemenin expandable listviewdeki pozisyonuna göre cursor o pozisyona gider
        res.moveToPosition(position);

        //o pozisyondaki string değeri yani deneme adı alınır
        oldExamName = res.getString(0);

        //yeni deneme adı eski deneme adının yerine yazılır
        contentValues.put(COL_21, newExamName);

        db.update(TABLE_NAME2, contentValues, "EXAM_NAME = ?", new String[]{oldExamName});
    }

    //lys ye geçerken ygs netleri kaybolmasın diye veri tabanına ekler
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
        contentValues.put(COL_66, allScores.getMf1());
        contentValues.put(COL_67, allScores.getMf2());
        contentValues.put(COL_68, allScores.getMf3());
        contentValues.put(COL_69, allScores.getMf4());
        contentValues.put(COL_70, allScores.getTm1());
        contentValues.put(COL_71, allScores.getTm2());
        contentValues.put(COL_72, allScores.getTm3());
        contentValues.put(COL_73, allScores.getTs1());
        contentValues.put(COL_74, allScores.getTs2());
        contentValues.put(COL_75, allScores.getLang1());
        contentValues.put(COL_76, allScores.getLang2());
        contentValues.put(COL_77, allScores.getLang3());

        long result = db.insert(TABLE_NAME3, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    //eğer tablo varsa ygs netlerini varolan netlerin üstüne yazar
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
        contentValues.put(COL_66, allScores.getMf1());
        contentValues.put(COL_67, allScores.getMf2());
        contentValues.put(COL_68, allScores.getMf3());
        contentValues.put(COL_69, allScores.getMf4());
        contentValues.put(COL_70, allScores.getTm1());
        contentValues.put(COL_71, allScores.getTm2());
        contentValues.put(COL_72, allScores.getTm3());
        contentValues.put(COL_73, allScores.getTs1());
        contentValues.put(COL_74, allScores.getTs2());
        contentValues.put(COL_75, allScores.getLang1());
        contentValues.put(COL_76, allScores.getLang2());
        contentValues.put(COL_77, allScores.getLang3());

        db.update(TABLE_NAME3, contentValues, null, null);

        return true;
    }

    //sadece cursor u döndürür
    public Cursor getYgsDatasForLys() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME3, null);

        return res;
    }
}
