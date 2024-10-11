package pt.ubi.di.pdm.a44240_t0;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class xdbHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "mensagensDB4";

    public xdbHelper(Context context){
        super(context, TABLE_NAME, null, 17);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1_SAUDACAO + " TEXT," + COL2_QUEBRAGELO + "TEXT, " + COL3_CORPO + "TEXT, " +
                COL4_VOTOS + "TEXT, " + COL5_DESPEDIDA + "TEXT, " + COL6_ASSINATURA + "TEXT)";
         */
        String createTable = "create table mensagensDB4(ID INTEGER PRIMARY KEY AUTOINCREMENT, saudacao TEXT, quebragelo TEXT," +
                "corpo TEXT, votos TEXT, despedida TEXT, assinatura TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item, String quebraGelo, String corpo, String votos, String despedida, String assinatura){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("saudacao", item);
        contentValues.put("quebragelo", quebraGelo);
        contentValues.put("corpo", corpo);
        contentValues.put("votos", votos);
        contentValues.put("despedida", despedida);
        contentValues.put("assinatura", assinatura);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + "*" + " FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getID(String item, String COLUMN_NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT ID FROM " + TABLE_NAME +
                " WHERE " + COLUMN_NAME + " = '" + item + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteItem(int ID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE ID = " + ID;
        Log.d(TAG, "deleteName: query: " + query);
        db.execSQL(query);
    }

    public void updateItem(String oldValor, String Valor, int ID, String COLUMN_NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_NAME + " = '" + Valor + "' WHERE ID = '" + ID + "'" +
                " AND " + COLUMN_NAME + " = '" + oldValor + "'";
        db.execSQL(query);
    }
}
