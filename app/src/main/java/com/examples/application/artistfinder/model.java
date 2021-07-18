package com.examples.application.artistfinder;

public class model
{
    String art,email,name,purl,a1url, location;

    public model() {
    }

    public model(String course, String email, String name, String location, String a1url) {
        this.art = course;
        this.email = email;
        this.name = name;
        this.location = location;
        this.purl = purl;
        this.a1url = a1url;
    }

    public String getA1url() {
        return a1url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public void setA1url(String a1url) {
        this.a1url = a1url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
