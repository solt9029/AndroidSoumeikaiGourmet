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

        // 店の人の卒業年と会名
        TextView ownerGraduatedAtGroupText = (TextView)convertView.findViewById(R.id.owner_graduated_at_group_text);
        String ownerGraduatedAt = shopList.get(position).getOwnerGraduatedAt();
        String ownerGroup = shopList.get(position).getOwnerGroup();
        ownerGraduatedAtGroupText.setText(ownerGraduatedAt + " " + ownerGroup);

        // 店の人の名前と部活
        TextView ownerNameClubText = (TextView)convertView.findViewById(R.id.owner_name_club_text);
        String ownerName = shopList.get(position).getOwnerName();
        String ownerClub = shopList.get(position).getOwnerClub();
        ownerNameClubText.setText(ownerName + " " + "(" + ownerClub + "所属)");

        // 店の名前
        TextView nameText = (TextView)convertView.findViewById(R.id.name_text);
        nameText.setText(shopList.get(position).getName());

        // 店のコメント
        TextView commentText = (TextView)convertView.findViewById(R.id.comment_text);
        commentText.setText(shopList.get(position).getComment());

        // 店の電話番号
        TextView phoneNumberText = (TextView)convertView.findViewById(R.id.phone_number_text);
        phoneNumberText.setText("電話番号: " + shopList.get(position).getPhoneNumber());

        // 店の住所
        TextView addressText = (TextView)convertView.findViewById(R.id.address_text);
        addressText.setText("住所: " + shopList.get(position).getAddress());

        // 店の画像
        ImageView imageImage = (ImageView)convertView.findViewById(R.id.image_image);
        int drawableId = context.getResources().getIdentifier("shop" + shopList.get(position).getId(), "drawable", context.getPackageName());
        imageImage.setImageResource(drawableId);

        return convertView;
    }
}
