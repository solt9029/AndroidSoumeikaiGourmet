package com.solt9029.soumeikaigourmet.androidsoumeikaigourmet;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Shop> shopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        ShopAdapter adapter = new ShopAdapter(this, shopList);
        shopListView.setAdapter(adapter);

        shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                ListView listView = (ListView)parent;
                String item = "aiueo";
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
