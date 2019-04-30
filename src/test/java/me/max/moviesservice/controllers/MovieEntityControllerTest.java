package me.max.moviesservice.controllers;

import me.max.moviesservice.dto.MovieDTO;
import me.max.moviesservice.exception.NotMovieIdInDatabaseException;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by amxik on 29.04.2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MovieEntityControllerTest {

    private MovieDTO testMovieDTO = new MovieDTO();

    @MockBean
    private MovieEntityService movieEntityService;

    @Autowired
    private MovieEntityController movieEntityController;


    @Autowired
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        testMovieDTO.setId(5);
        testMovieDTO.setTitle("Test");
        testMovieDTO.setGenre("TestGenre");
        testMovieDTO.setDescription("TestDescription");
        testMovieDTO.setDuration(77);
        testMovieDTO.setReleaseDate(new Date(1998, 04, 05));

    }


    @Test
    public void getMovieByIdTest() throws NotMovieIdInDatabaseException {
        Mockito.when(movieEntityService.getMovieById(5)).thenReturn(testMovieDTO);
        MovieDTO movie = movieEntityController.getMovie(5);
        Assert.isTrue(movie.getId() == 5, "Id must be the same");
        Mockito.verify(movieEntityService, Mockito.times(1))
                .getMovieById(5);
        Mockito.verifyNoMoreInteractions(movieEntityService);
        assertThat(movie).isEqualTo(testMovieDTO);
    }

    @Test
    public void getAllMoviesTest() {
        List<MovieDTO> list = new ArrayList<>();
        list.add(testMovieDTO);
        Mockito.when(movieEntityService.getAllMovies(0, 20)).thenReturn(list);
        List<MovieDTO> listTest = movieEntityController.getMovies(0, 20);
        assertThat(listTest).isEqualTo(list);
    }

    @Test
    public void testMovieGetByIdStringRandom() throws Exception {
        this.mockMvc.perform(get("/movies/string"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void ifTheMovieIdIsNotInTheDatabase() throws Exception {

            this.mockMvc.perform(get("/movies/92233720368547758"))

                    .andExpect(status().isBadRequest());


    }


    @Test
    public void testMovieGetByDoubleId() throws Exception {
        this.mockMvc.perform(get("/movies/5.444"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testMovieGetByIdMoreValueMaxLong() throws Exception {
        this.mockMvc.perform(get("/movies/9223372036854775810"))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testMovieGetByIdLessThanZero() throws Exception {

        this.mockMvc.perform(get("/movies/-1"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testReplaceMovie() throws Exception {

        Mockito.when(movieEntityService.replaceMovieById(5, testMovieDTO)).thenReturn(testMovieDTO);

        MovieDTO movie = movieEntityController.replaceMovie(5, testMovieDTO);

        assertThat(movie).isEqualTo(testMovieDTO);
    }

    @Test
    public void testUpdateMovie() throws Exception {

        Mockito.when(movieEntityService.updateMovie(5, testMovieDTO)).thenReturn(testMovieDTO);

        MovieDTO movie = movieEntityController.updateMovie(5, testMovieDTO);

        assertThat(movie).isEqualTo(testMovieDTO);

    }


    @Test
    public void testSearchMoviesByTitle() {
        List<MovieDTO> list = new ArrayList<>();
        list.add(testMovieDTO);
        Mockito.when(movieEntityService.getMovieByTitle("Test", 0, 20)).thenReturn(list);

        List<MovieDTO> testList = movieEntityController.searchMoviesByTitle("Test", 0, 20);

        assertThat(list).isEqualTo(testList);

        assertThat(list.get(0).getTitle()).isEqualTo(testList.get(0).getTitle());


    }

    @Test
    public void testSearchMoviesByReleaseDate() {

        List<MovieDTO> list = new ArrayList<>();
        list.add(testMovieDTO);
        Mockito.when(movieEntityService.getMoviesByReleaseDate(new Date(1998, 04, 05), 0, 20)).thenReturn(list);

        List<MovieDTO> testList = movieEntityController.searchMoviesByReleaseDate(new Date(1998, 04, 05), 0, 20);
        assertThat(list).isEqualTo(testList);
        assertThat(list.get(0).getReleaseDate()).isEqualTo(testList.get(0).getReleaseDate());

    }


}