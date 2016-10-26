package com.liveproject.persi.ycce.iimp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tiger on 05-10-2016.
 */
public class DatabaseService {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "IIMP_DB";

    private static final String TABLE_LOGIN = "LOGIN";
    private static final String TABLE_USERPROFILE = "USERPROFILE";
    private static final String TABLE_ADDR = "ADDRESS";
    private static final String TABLE_AREACODE = "AREACODE";
    private static final String TABLE_DESIGNATION = "DESIGNATION";
    private static final String TABLE_GROUP = "GROUPS";
    private static final String TABLE_GROUPMAP = "GROUPMAP";
    private static final String TABLE_CONDGROUPMAP = "CONDGROUPMAP";


    //All Columns of LOGIN TABLE:
    private static final String LOGIN_MOBILE_NO = "MOBILE_NO";
    private static final String LOGIN_STATUS = "STATUS";
    private static final String LOGIN_LAST_LOGIN = "LAST_LOGIN";

    //All Columns of USER_PROFILE TABLE:
    private static final String USERPROFILE_UPID = "UPID";
    private static final String USERPROFILE_ID = "ID";
    private static final String USERPROFILE_FIRST_NAME = "FIRST_NAME";
    private static final String USERPROFILE_LAST_NAME = "LAST_NAME";
    private static final String USERPROFILE_MOBILE_NO = "MOBILE_NO";
    private static final String USERPROFILE_EMAIL_ID = "EMAIL_ID";
    private static final String USERPROFILE_DIVISION = "DIVISION";
    private static final String USERPROFILE_SUBDIVISION = "SUBDIVISION";
    private static final String USERPROFILE_SUPERVISOR = "SUPERVISOR";
    private static final String USERPROFILE_DOB = "DOB";
    private static final String USERPROFILE_DOJ = "DOJ";
    private static final String USERPROFILE_GENDER = "GENDER";

    private static final String USERPROFILE_AID = "AID";
    private static final String USERPROFILE_DID = "DID";

    //All Columns of ADDRESS TABLE:
    private static final String ADDR_AID = "AID";
    private static final String ADDR_ADDRLINE = "ADDRLINE";
    private static final String ADDR_STREET = "STREET";
    private static final String ADDR_LOCALITY = "LOCALITY";
    private static final String ADDR_PINCODE = "PINCODE";

    //All Columns of AREACODE TABLE:
    private static final String AREACODE_PINCODE = "PINCODE";
    private static final String AREACODE_CITY = "CITY";
    private static final String AREACODE_STATE = "STATE";
    private static final String AREACODE_COUNTRY = "COUNTRY";

    //All Columns of DESIGNATION TABLE:
    private static final String DESIGNATION_DID = "DID";
    private static final String DESIGNATION_DESIGNATION = "DESIGNATION";


    //All Columns of GROUP TABLE:
    private static final String GROUP_ID = "GID";
    private static final String GROUP_NAME = "GNAME";
    private static final String GROUP_TYPE = "GTYPE";
    private static final String GROUP_STATUS = "STATUS";

    //All Columns of GROUPMAP TABLE
    private static final String GROUPMAP_GID = "GID";
    private static final String GROUPMAP_UPID = "UPID";

    //All columns of CONDGROUPMAP TABLE
    private static final String CONDGROUPMAP_GID = "GID";
    private static final String CONDGROUPMAP_CITY = "CITY";
    private static final String CONDGROUPMAP_DESIGNATION = "DESIGNATION";

    /*********
     * Used to open database in synchronized way
     *********/
    private static DataBaseHelper DBHelper = null;

    protected DatabaseService() {
    }

    /**********
     * Initialize database
     *********/
    public static void init(Context context) {
        if (DBHelper == null)
            DBHelper = new DataBaseHelper(context);
    }
   public static boolean check_validity(){
       if(DBHelper == null)
            return false;
       else
           return true;
   }

    private static class DataBaseHelper extends SQLiteOpenHelper {

        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_LOGIN +
                    " (" + LOGIN_MOBILE_NO + " text , " +
                    LOGIN_STATUS + " text , " +
                    LOGIN_LAST_LOGIN + " text ); "
            );

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USERPROFILE +
                    " (" + USERPROFILE_UPID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USERPROFILE_ID + " TEXT , " +
                    USERPROFILE_FIRST_NAME + " TEXT , " +
                    USERPROFILE_LAST_NAME + " TEXT , " +
                    USERPROFILE_MOBILE_NO + " TEXT , " +
                    USERPROFILE_EMAIL_ID + " TEXT , " +
                    USERPROFILE_DOB + " TEXT , " +
                    USERPROFILE_GENDER + " TEXT , " +
                    USERPROFILE_DIVISION + " TEXT , " +
                    USERPROFILE_SUBDIVISION + " TEXT , " +
                    USERPROFILE_SUPERVISOR + " TEXT , " +
                    USERPROFILE_DOJ + " TEXT , " +
                    USERPROFILE_AID + " INTEGER , " +
                    USERPROFILE_DID + "INTEGER ); "
            );

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_ADDR +
                    " (" + ADDR_AID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +       //Remember to truncate the table not just delete the rows.
                    ADDR_ADDRLINE + " TEXT, " +
                    ADDR_LOCALITY + " TEXT, " +
                    ADDR_STREET + " TEXT, " +
                    ADDR_PINCODE + " TEXT); "
            );

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_AREACODE +
                    " (" + AREACODE_PINCODE + " INTEGER PRIMARY KEY, " +
                    AREACODE_CITY + " TEXT, " +
                    AREACODE_STATE + " TEXT, " +
                    AREACODE_COUNTRY + " TEXT); "
            );

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_DESIGNATION +
                    " (" + DESIGNATION_DID + " INTEGER PRIMARY KEY, " +
                    DESIGNATION_DESIGNATION + " TEXT );"
            );
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_GROUP +
                    " (" + GROUP_ID + " INTEGER PRIMARY KEY, " +
                    GROUP_NAME + " TEXT, " +
                    GROUP_STATUS + " TEXT, " +
                    GROUP_TYPE + " TEXT );"
            );
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_GROUPMAP +
                    " (" + GROUPMAP_GID + " INTEGER PRIMARY KEY, " +
                    GROUPMAP_UPID + " INTEGER );"
            );
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CONDGROUPMAP +
                    " (" + CONDGROUPMAP_GID + " INTEGER , " +
                    CONDGROUPMAP_CITY + " TEXT, " +
                    CONDGROUPMAP_DESIGNATION + " TEXT );"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //Add the following code :
            /*
             for (String table : ALL_TABLES) {     // Here, the alltables is a string that is declared above and contains names of all tables in a string array.
                db.execSQL("DROP TABLE IF EXISTS " + table);
            }
            onCreate(db);
             */
        }
    }


    // HINT : OPEN DATABASE FOR INSERT,UPDATE,DELETE IN SYNCRONIZED MANNER
    private static synchronized SQLiteDatabase open() throws SQLException {
        return DBHelper.getWritableDatabase();
    }


    // HINT : INSERT OPERATION ON USER_PROFILE TABLE.
    public static boolean insertUserProfile(Member m) {
        final SQLiteDatabase db = open();

        ContentValues cval = new ContentValues();
        cval.put(USERPROFILE_UPID, "1");
        cval.put(USERPROFILE_FIRST_NAME, m.getFirstname());
        cval.put(USERPROFILE_LAST_NAME, m.getLastname());
        cval.put(USERPROFILE_MOBILE_NO, m.getMobileno());
        cval.put(USERPROFILE_EMAIL_ID, m.getEmailid());
        cval.put(USERPROFILE_DOB, m.getDob());
        cval.put(USERPROFILE_GENDER, m.getGender());
        cval.put(USERPROFILE_DOJ, m.getDoj());

        db.delete(TABLE_USERPROFILE, null, null);
        db.insert(TABLE_USERPROFILE, null, cval);

        db.close();
        return true;
    }

    // HINT : FETCHING OPERATION ON USER_PROFILE TABLE.
    public static Member getMember(int id) {
        final SQLiteDatabase db = open();

        Cursor cursor = db.query(TABLE_USERPROFILE,
                new String[]{USERPROFILE_ID, USERPROFILE_FIRST_NAME, USERPROFILE_EMAIL_ID, USERPROFILE_MOBILE_NO},
                USERPROFILE_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Member data = new Member(cursor.getString(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        return data;
    }
    public static Member getMember(String id) {
        final SQLiteDatabase db = open();

        Cursor cursor = db.query(TABLE_USERPROFILE,
                new String[]{USERPROFILE_ID, USERPROFILE_FIRST_NAME, USERPROFILE_EMAIL_ID, USERPROFILE_MOBILE_NO},
                USERPROFILE_ID + "=?",
                new String[]{id},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Member data = new Member(cursor.getString(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        return data;
    }


    // HINT : MOBILE INSERT OPERATION ON LOGIN TABLE:
    public static boolean insertLogin(String str) {
        final SQLiteDatabase db = open();

        ContentValues cval = new ContentValues();
        cval.put(LOGIN_MOBILE_NO, str);
        cval.put(LOGIN_STATUS,Constants.STATUS[1]);

        db.delete(TABLE_LOGIN, null, null);
        db.insert(TABLE_LOGIN, null, cval);

        db.close();
        return true;
    }

    // HINT : STATUS UPDATE AFTER OTP VERIFIED IN LOGIN TABLE.
    public static boolean statusUpdate(){
        final SQLiteDatabase db = open();

        ContentValues cval = new ContentValues();
        cval.put(LOGIN_STATUS,Constants.STATUS[2]);

        db.update(TABLE_LOGIN,cval,LOGIN_STATUS + "= ?",new String[]{Constants.STATUS[1]});
        return true;
    }


    // HINT : FETCH MOBILE NO FROM LOGIN TABLE.
    public static String fetchMobileNo() {

        // Open database for Read / Write
        final SQLiteDatabase db = open();

        Cursor cursor = db.query(TABLE_LOGIN,
                new String[]{LOGIN_MOBILE_NO},
                null,
                null,
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // HINT : RETURN MOBILE NO.
        return cursor.getString(0);
    }

    // HINT : FETCH STATUS FROM LOGIN TABLE.
    public static String fetchStatus() {

        // Open database for Read / Write
        final SQLiteDatabase db = open();

        Cursor cursor = db.query(TABLE_LOGIN,
                new String[]{LOGIN_STATUS},
                null,
                null,
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // HINT : RETURN STATUS.
        return cursor.getString(0);
    }

    //Fetch my Groups using only a mobile no passed to it.
    public static GroupClass[] getMyGroups(String Mob_no){
        String ids,names;
        GroupClass[] result;
        final SQLiteDatabase db =open();
        Cursor cursor=db.rawQuery("select gid,gname from groups;",new String[]{});
        //Cursor cursor =db.rawQuery("select " + GROUP_ID + " , " + GROUP_NAME + " from "+ TABLE_GROUP + " where " + GROUP_ID +
         //       " = ( select " + GROUPMAP_GID  + " from " + TABLE_GROUPMAP + " where " + GROUPMAP_UPID +
         //       " = ( select " + USERPROFILE_ID +  " from " + TABLE_USERPROFILE + " where " + USERPROFILE_MOBILE_NO + " = ?));",
         //       new String[]{Mob_no});
        int length = cursor.getCount();

        result = new GroupClass[length];
        if(cursor!=null)
        cursor.moveToFirst();

        for(int i=0;i<length;i++)
        {
            ids = cursor.getString(0);
            names = cursor.getString(1);
            result[i] = new GroupClass(ids,names);
            cursor.moveToNext();
        }
        return result;
    }

    public static String fetchgidbygname(String gname){
        final SQLiteDatabase db = open();

        Cursor cursor = db.query(TABLE_GROUP,
                new String[]{GROUPMAP_GID},
                GROUP_NAME + "=?",
                new String[]{gname},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor.getString(0);
    }

}