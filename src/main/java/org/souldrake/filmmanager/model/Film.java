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
    private Date date;
    private byte priority;
    private String actors;
    private String description;
    private String poster;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
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
                ", poster=" + poster +
                '}';
    }
}
