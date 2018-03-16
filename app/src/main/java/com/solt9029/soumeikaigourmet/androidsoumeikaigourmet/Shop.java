package com.solt9029.soumeikaigourmet.androidsoumeikaigourmet;

import com.google.gson.annotations.SerializedName;

public class Shop {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("category")
    private String category;

    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("address")
    private String address;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("link")
    private String link;

    @SerializedName("comment")
    private String comment;

    @SerializedName("owner_name")
    private String ownerName;

    @SerializedName("owner_club")
    private String ownerClub;

    @SerializedName("owner_graduated_at")
    private String ownerGraduatedAt;

    @SerializedName("owner_group")
    private String ownerGroup;

    public Shop(int id, String name, String category, String phoneNumber, String address, double latitude, double longitude, String link, String comment, String ownerName, String ownerClub, String ownerGraduatedAt, String ownerGroup) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.link = link;
        this.comment = comment;
        this.ownerName = ownerName;
        this.ownerClub = ownerClub;
        this.ownerGraduatedAt = ownerGraduatedAt;
        this.ownerGroup = ownerGroup;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLink() {
        return link;
    }

    public String getComment() {
        return comment;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerClub() {
        return ownerClub;
    }

    public String getOwnerGraduatedAt() {
        return ownerGraduatedAt;
    }

    public String getOwnerGroup() {
        return ownerGroup;
    }
}
