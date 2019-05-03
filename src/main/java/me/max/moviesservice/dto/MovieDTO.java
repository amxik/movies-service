package me.max.moviesservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by amxik on 27.04.2019.
 */


@XmlRootElement(name = "movie")
public class MovieDTO {
    @JsonProperty("id")
    private long id;

    @JsonProperty("title")
    @Size(min = 3, max = 50)
    private String title;


    @JsonProperty("genre")
    @Size(min = 3, max = 30)
    private String genre;

    @JsonProperty("description")
    @Size(min = 10, max = 255)
    private String description;

    @JsonProperty("duration")
    @Min(1)
    private Integer duration;

    @JsonProperty("releaseDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDTO movieDTO = (MovieDTO) o;
        return id == movieDTO.id &&
                Objects.equals(title, movieDTO.title) &&
                Objects.equals(genre, movieDTO.genre) &&
                Objects.equals(description, movieDTO.description) &&
                Objects.equals(duration, movieDTO.duration) &&
                Objects.equals(releaseDate, movieDTO.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, description, duration, releaseDate);
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", releaseDate=" + releaseDate +
                '}';
    }


}
