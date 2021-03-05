package com.example.list;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    //データベースの名前フィールド
    private static final  String DBNAME ="shopping.db";
    //データベースのバージョン情報
    private static final int DB_VER=1;
    //親クラスのコンストラクタの呼び出し
    public DatabaseHelper(Context context){
        super(context,DBNAME,null,DB_VER);
    }
    //テーブル作成用のSQL文字列の作成
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE shopping(");
        stringBuilder.append("_id INTEGER PRIMARY KEY,");
        stringBuilder.append(");");
        String sql =stringBuilder.toString();
        //SQL実行
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
