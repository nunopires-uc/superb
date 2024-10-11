package pt.ubi.di.pdm.a44240_t0;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "mensagensDB";
    private static final String COL0_ID = "ID";
    private static final String COL1_SAUDACAO = "saudacao";
    private static final String COL2_QUEBRAGELO = "quebragelo";
    private static final String COL3_CORPO = "corpo";
    private static final String COL4_VOTOS = "votos";
    private static final String COL5_DESPEDIDA = "despedida";
    private static final String COL6_ASSINATURA = "assinatura";

    public DatabaseHelper(Context context){
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1_SAUDACAO + " TEXT," + COL2_QUEBRAGELO + "TEXT, " + COL3_CORPO + "TEXT, " +
                COL4_VOTOS + "TEXT, " + COL5_DESPEDIDA + "TEXT, " + COL6_ASSINATURA + "TEXT)";

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item, String COLUMN_NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, item);
        Log.d(TAG, "addData: Adding " + item + " to " + COLUMN_NAME + ":" + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public SQLiteDatabase getDb(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + "*" + " FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


}
