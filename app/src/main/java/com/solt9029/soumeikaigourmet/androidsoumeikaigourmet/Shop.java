package com.solt9029.soumeikaigourmet.androidsoumeikaigourmet;

public class Shop {
    private int id;
    private String name;
    private String category;
    private String phone_number;
    private String address;
    private double latitude;
    private double longitude;
    private String link;
    private String comment;
    private String ownerName;
    private String ownerClub;
    private String ownerGraduatedAt;
    private String ownerGroup;

    public Shop(int id, String name, String category, String phone_number, String address, double latitude, double longitude, String link, String comment, String ownerName, String ownerClub, String ownerGraduatedAt, String ownerGroup) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.phone_number = phone_number;
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

    public String getPhone_number() {
        return phone_number;
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
