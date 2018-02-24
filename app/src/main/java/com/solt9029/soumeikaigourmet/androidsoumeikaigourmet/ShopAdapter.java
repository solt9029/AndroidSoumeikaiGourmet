package com.solt9029.soumeikaigourmet.androidsoumeikaigourmet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        TextView nameView = (TextView)convertView.findViewById(R.id.name_text);
        nameView.setText(shopList.get(position).getName());

        return convertView;
    }
}
