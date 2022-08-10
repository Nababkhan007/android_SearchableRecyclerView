package com.khan.searchablerecyclerview.model;

public class Footballer {
    private final int image;
    private final String name;
    private final String age;
    private final String height;
    private final String jerseyNumber;
    private final String position;
    private final String nationalTeam;
    private final String clubTeam;
    private final String country;

    public Footballer(int image, String name, String age, String height, String jerseyNumber,
                      String position, String nationalTeam, String clubTeam, String country) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.height = height;
        this.jerseyNumber = jerseyNumber;
        this.position = position;
        this.nationalTeam = nationalTeam;
        this.clubTeam = clubTeam;
        this.country = country;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public String getJerseyNumber() {
        return jerseyNumber;
    }

    public String getPosition() {
        return position;
    }

    public String getNationalTeam() {
        return nationalTeam;
    }

    public String getClubTeam() {
        return clubTeam;
    }

    public String getCountry() {
        return country;
    }
}
