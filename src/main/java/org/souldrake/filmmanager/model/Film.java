package org.souldrake.filmmanager.model;

import java.util.Date;

/**
 * Film model class
 *
 * @author SoulDrake
 * @create 2021-10-26 18:41
 **/

public class Film {
    private Integer id;
    private String title;
    private int year;
    private String genre;
    private String country;
    private String date;
    private byte priority;
    private String actors;
    private String description;
    private String posterUrl;

    public Film(Integer id, String title, int year, String genre, String country,
                String date, byte priority, String actors, String description, String posterUrl) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.country = country;
        this.date = date;
        this.priority = priority;
        this.actors = actors;
        this.description = description;
        this.posterUrl = posterUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title=" + title +
                ", year=" + year +
                ", genre=" + genre +
                ", country=" + country +
                ", date=" + date +
                ", priority=" + priority +
                ", actors=" + actors +
                ", description=" + description +
                ", poster=" + posterUrl +
                '}';
    }
}
