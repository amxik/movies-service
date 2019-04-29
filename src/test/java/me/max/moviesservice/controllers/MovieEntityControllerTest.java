package me.max.moviesservice.controllers;

import me.max.moviesservice.dto.MovieDTO;
import me.max.moviesservice.service.MovieEntityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * Created by amxik on 29.04.2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MovieEntityControllerTest {

    @MockBean
    private MovieEntityService movieEntityService;

    @Autowired
    private MovieEntityController movieEntityController;

    @Before
    public void setUp() throws Exception {
        MovieDTO testMovieDTO = new MovieDTO();

        testMovieDTO.setId(5);
        testMovieDTO.setTitle("Test");
        testMovieDTO.setGenre("TestGenre");
        testMovieDTO.setDescription("TestDescription");
        testMovieDTO.setDuration(77);
        testMovieDTO.setReleaseDate(new Date(2000,12,12));

    }



    @Test
    public void getMovieByIdTest() {
        MovieDTO testMovieDTO = new MovieDTO();

        testMovieDTO.setId(5);
        testMovieDTO.setTitle("Test");
        testMovieDTO.setGenre("TestGenre");
        testMovieDTO.setDescription("TestDescription");
        testMovieDTO.setDuration(77);
        testMovieDTO.setReleaseDate(new Date(2000,12,12));

        Mockito.when(movieEntityService.getMovieById(5)).thenReturn(testMovieDTO);

        MovieDTO movie = movieEntityController.getMovie(5);

        Assert.isTrue(movie.getId()==5, "Id must be the same");



    }

}