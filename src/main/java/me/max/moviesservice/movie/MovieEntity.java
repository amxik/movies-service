package me.max.moviesservice.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.max.moviesservice.data.BaseEntity;

import javax.persistence.*;

/**
 * Created by amxik on 22.04.2019.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class MovieEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private Integer duration;

    public MovieEntity(String title, String description, Integer duration) {
        this.title = title;
        this.description = description;
        this.duration = duration;
    }
}
