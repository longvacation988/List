package com.example.list;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //選択された商品のID
    int shopID= -1;
    //選択された商品名を表す場所
    String shopName="";
    //商品名を表示する場所
    TextView name;
    //ボタン
    Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //テキストビューセット
        name =findViewById(R.id.name);
        //ボタンビューセット
        saveButton=findViewById(R.id.saveButton);
        //リストビューセット
        ListView listView =findViewById(R.id.listView);
        //リストが押された時の処理
        listView.setOnItemClickListener(new ListItemClickListener());
    }
    //ボタンがタッチされた時の処理
    public void setSaveButton(View view){
        //ビューのセット
        EditText editText=findViewById(R.id.editText);
        //入力欄の情報を取得する
        String note =editText.getText().toString();
        //データベースヘルパーオブジェクトを作成する
        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
        //データベースヘルパーオブジェクトからデータベース接続オブジェクトを取得する
        SQLiteDatabase db = helper.getWritableDatabase();

        try{
            //選択情報ののデータを削除して、その後インサート
            String sqlDelete="DELETE FROM shopping WHERE _id= ?";
            //SQL文字列をもとにステートメント取得
            SQLiteStatement statement = db.compileStatement(sqlDelete);
            //変数のバインド
            statement.bindLong(1,shopID);
            //削除SQLの実行
            statement.executeUpdateDelete();

            //挿入SQL文字列の用意
            String sqlInsert ="INSERT INTO shopping(_id,name,note)VALUES(?,?,?)";
            //文字列からステートメントを取得する
            statement=db.compileStatement(sqlInsert);
            //変数のバインド
            statement.bindLong(1,shopID);
            statement.bindString(2,shopName);
            statement.bindString(3,note);
            //挿入の実行
            statement.executeInsert();
        }
        finally {
            //データベースの接続を終わる
            db.close();
        }
        //未選択に変更
        name.setText(getString(R.string.name));
        //入力欄を消去
        editText.setText("");
        //ボタンをタップできないように変更
        saveButton.setEnabled(false);
    }
    //アイテムがタッチされた時の処理
    private class  ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //タップされた行番号をIDにセット
            shopID = position;
            //行のデータを取得してセット
            shopName=(String)parent.getItemAtPosition(position);
            //テキストビューにセット
            name.setText(shopName);
            //ボタンを押せるように変更
            saveButton.setEnabled(true);

        }
    }
}