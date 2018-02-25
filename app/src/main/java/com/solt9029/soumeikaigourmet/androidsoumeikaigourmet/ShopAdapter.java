package com.solt9029.soumeikaigourmet.androidsoumeikaigourmet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShopAdapter extends BaseAdapter{
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Shop> shopList;

    public ShopAdapter(Context context, ArrayList<Shop>shopList){
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.shopList = shopList;
    }

    @Override
    public int getCount(){
        return shopList.size();
    }

    @Override
    public Object getItem(int position){
        return shopList.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        convertView = layoutInflater.inflate(R.layout.shop_listview_cell, parent, false);

        // 店の名前
        TextView nameText = (TextView)convertView.findViewById(R.id.name_text);
        nameText.setText(shopList.get(position).getName());

        // 店の画像
        ImageView imageImage = (ImageView)convertView.findViewById(R.id.image_image);
        int drawableId = context.getResources().getIdentifier("shop" + shopList.get(position).getId(), "drawable", context.getPackageName());
        imageImage.setImageResource(drawableId);

        return convertView;
    }
}
