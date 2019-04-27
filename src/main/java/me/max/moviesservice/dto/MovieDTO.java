package me.max.moviesservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

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
    @Size(min = 20, max = 255)
    private String description;

    @JsonProperty("duration")
    @Size(min = 2, max = 3)
    private Integer duration;

    @JsonProperty("releaseDate")
    @Size(min = 8)
    private Date releaseDate;

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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
