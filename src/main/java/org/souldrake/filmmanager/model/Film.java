package org.souldrake.filmmanager.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Film model class
 *
 * @author SoulDrake
 * @create 2021-10-26 18:41
 **/

@Entity
@Table(name = "films", uniqueConstraints = {@UniqueConstraint(columnNames = {"title", "year"}, name = "films_title_year_unique_index")})
public class Film {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "film_id_seq")
    private Integer id;
    private String title;
    private int year;
    private String genre;
    private String country;
    private LocalDate date;
    private byte priority;
    private String actors;
    private String description;
    @Column(name = "poster_url")
    private String posterUrl;

    public Film() {}

    public Film(Integer id, String title, int year, String genre, String country,
                LocalDate date, byte priority, String actors, String description, String posterUrl) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
                ", posterUrl=" + posterUrl +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        Film film = (Film) o;
        return id != null && id.equals(film.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
