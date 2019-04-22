package me.max.moviesservice.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * Created by amxik on 22.04.2019.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    private String description;

    private Integer duration;

    public MovieEntity(String title, String description, Integer duration) {
        this.title = title;
        this.description = description;
        this.duration = duration;
    }
}
