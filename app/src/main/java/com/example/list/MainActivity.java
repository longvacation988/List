package com.example.list;

import androidx.appcompat.app.AppCompatActivity;

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