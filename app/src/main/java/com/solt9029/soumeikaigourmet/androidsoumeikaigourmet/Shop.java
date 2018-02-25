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
    private String owner_name;
    private String owner_club;
    private String owner_graduated_at;
    private String owner_group;

    public Shop(int id, String name, String category, String phone_number, String address, double latitude, double longitude, String link, String comment, String owner_name, String owner_club, String owner_graduated_at, String owner_group) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.phone_number = phone_number;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.link = link;
        this.comment = comment;
        this.owner_name = owner_name;
        this.owner_club = owner_club;
        this.owner_graduated_at = owner_graduated_at;
        this.owner_group = owner_group;
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

    public String getOwner_name() {
        return owner_name;
    }

    public String getOwner_club() {
        return owner_club;
    }

    public String getOwner_graduated_at() {
        return owner_graduated_at;
    }

    public String getOwner_group() {
        return owner_group;
    }
}
