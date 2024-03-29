package me.max.moviesservice.movie;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by amxik on 22.04.2019.
 */
@Entity
@Table(name = "movies")
@EntityListeners(AuditingEntityListener.class)
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    private Long createdAt;

    @LastModifiedDate
    private Long lastModified;

    private String title;

    private String genre;

    private String description;

    private Integer duration;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;




    public MovieEntity(String title, String genre, String description, Integer duration, LocalDate releaseDate) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.duration = duration;
        this.releaseDate = releaseDate;
    }

    public MovieEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getLastModified() {
        return lastModified;
    }

    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", lastModified=" + lastModified +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
