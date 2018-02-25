package com.solt9029.soumeikaigourmet.androidsoumeikaigourmet;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Shop> shopList;
    private String distanceOptions[] = {"距離指定なし", "5km圏内", "10km圏内", "20km圏内", "50km圏内", "100km圏内"};
    private String clubOptions[] = {"部活指定なし", "風紀委員会", "ボーイスカウト部", "硬式野球部", "生徒会本部", "硬式テニス部", "美術演劇部", "美術部", "山岳部", "ESS", "バドミントン部", "水泳部", "卓球部", "フェンシング部", "物理部", "化学部", "バスケ部", "剣道部", "柔道部", "マンドリン部", "吹奏楽班", "相撲部", "応援指導班", "JRC インターアクト", "商業研究部", "スキー部"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 距離Spinner
        Spinner distanceSpinner = findViewById(R.id.distance_spinner);
        ArrayAdapter<String> distanceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, distanceOptions);
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distanceSpinner.setAdapter(distanceAdapter);
        distanceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                String item = (String)spinner.getSelectedItem();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // 何も選択されなかったとき
            }
        });

        // 部活動Spinner
        Spinner clubSpinner = findViewById(R.id.club_spinner);
        ArrayAdapter<String> clubAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, clubOptions);
        clubAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clubSpinner.setAdapter(clubAdapter);
        clubSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                String item = (String)spinner.getSelectedItem();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // 何も選択されなかったとき
            }
        });



        ListView shopListView = (ListView)findViewById(R.id.shop_list);

        shopList = new ArrayList<>();
        shopList.add(new Shop("test shop 1"));
        shopList.add(new Shop("test shop 2"));
        shopList.add(new Shop("test shop 3"));
        shopList.add(new Shop("test shop 1"));
        shopList.add(new Shop("test shop 2"));
        shopList.add(new Shop("test shop 3"));
        shopList.add(new Shop("test shop 1"));
        shopList.add(new Shop("test shop 2"));
        shopList.add(new Shop("test shop 3"));
        shopList.add(new Shop("test shop 1"));
        shopList.add(new Shop("test shop 2"));
        shopList.add(new Shop("test shop 3"));
        shopList.add(new Shop("test shop 1"));
        shopList.add(new Shop("test shop 2"));
        shopList.add(new Shop("test shop 3"));

        ShopAdapter shopAdapter = new ShopAdapter(this, shopList);
        shopListView.setAdapter(shopAdapter);

        shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                ListView listView = (ListView)parent;
                String item = "aiueo";
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
