package me.max.moviesservice.controllers;

import me.max.moviesservice.movie.MovieEntity;
import me.max.moviesservice.repositories.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by amxik on 29.04.2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MovieEntityControllerTest {

    private final Pageable pageable = new PageRequest(0, 20);
    private MovieEntity testMovieEntity = new MovieEntity();

    private final String JSON_ENTITY =
            "{\"id\":5,\"title\":\"Test\",\"genre\":\"TestGenre\",\"description\":\"TestDescription\",\"duration\":77,\"releaseDate\":\"1998-04-05\"}";

    @MockBean
    private MovieRepository movieRepository;

    @MockBean
    private Page page;

    @Autowired
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {

        testMovieEntity.setId(5L);
        testMovieEntity.setTitle("Test");
        testMovieEntity.setGenre("TestGenre");
        testMovieEntity.setDescription("TestDescription");
        testMovieEntity.setDuration(77);
        testMovieEntity.setReleaseDate(LocalDate.of(1998, Month.APRIL, 5));
    }


    @Test
    public void getMovieByIdTest() throws Exception {

        Mockito.when(movieRepository.findById(5L)).thenReturn(Optional.of(testMovieEntity));
        this.mockMvc.perform(get("/movies/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.title", is("Test")))
                .andExpect(jsonPath("$.genre", is("TestGenre")))
                .andExpect(jsonPath("$.description", is("TestDescription")))
                .andExpect(jsonPath("$.duration", is(77)))
                .andExpect(jsonPath("$.releaseDate", is("1998-04-05")));

        Mockito.verify(movieRepository, times(1))
                .findById(5L);
        Mockito.verifyNoMoreInteractions(movieRepository);

    }

    @Test
    public void getAllMoviesTest() throws Exception {
        List<MovieEntity> list = new ArrayList<>();
        list.add(testMovieEntity);

        Mockito.when(page.getContent()).thenReturn(list);

        Mockito.when(movieRepository.findAll(pageable))
                .thenReturn(page);

        this.mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(5)))
                .andExpect(jsonPath("$[0].title", is("Test")))
                .andExpect(jsonPath("$[0].genre", is("TestGenre")))
                .andExpect(jsonPath("$[0].description", is("TestDescription")))
                .andExpect(jsonPath("$[0].duration", is(77)))
                .andExpect(jsonPath("$[0].releaseDate", is("1998-04-05")));

        Mockito.verify(movieRepository, times(1)).findAll(pageable);


    }

    @Test
    public void testMovieGetByIdStringRandom() throws Exception {
        this.mockMvc.perform(get("/movies/string"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void ifTheMovieIdIsNotInTheDatabase() throws Exception {
        Mockito.when(movieRepository.findById(10L)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/movies/10"))
                .andExpect(status().isNotFound());
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
    public void testCreateMovie() throws Exception {
        Mockito.when(movieRepository.save(any(MovieEntity.class))).thenReturn(testMovieEntity);

        this.mockMvc.perform(post("/movies")
                .content(JSON_ENTITY)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                /*.andDo(print())*/
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.title", is("Test")))
                .andExpect(jsonPath("$.genre", is("TestGenre")))
                .andExpect(jsonPath("$.description", is("TestDescription")))
                .andExpect(jsonPath("$.duration", is(77)))
                .andExpect(jsonPath("$.releaseDate", is("1998-04-05")));

        Mockito.verify(movieRepository, times(1)).save(any(MovieEntity.class));


    }

    @Test
    public void testReplaceMovie() throws Exception {

        Mockito.when(movieRepository.save(any(MovieEntity.class))).thenReturn(testMovieEntity);

        this.mockMvc.perform(put("/movies/5")
                .content(JSON_ENTITY)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                /*.andDo(print())*/
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.title", is("Test")))
                .andExpect(jsonPath("$.genre", is("TestGenre")))
                .andExpect(jsonPath("$.description", is("TestDescription")))
                .andExpect(jsonPath("$.duration", is(77)))
                .andExpect(jsonPath("$.releaseDate", is("1998-04-05")));
    }

    @Test
    public void testUpdateMovie() throws Exception {


        Mockito.when(movieRepository.save(any(MovieEntity.class))).thenReturn(testMovieEntity);
        Mockito.when(movieRepository.findById(5L)).thenReturn(Optional.of(testMovieEntity));

        mockMvc.perform(patch("/movies/5")
                .content(JSON_ENTITY)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());

        Mockito.verify(movieRepository, times(1)).findById(5L);
        Mockito.verify(movieRepository, times(1)).save(any(MovieEntity.class));


    }


    @Test
    public void testSearchMoviesByTitle() throws Exception {
        List<MovieEntity> list = new ArrayList<>();
        list.add(testMovieEntity);

        Mockito.when(movieRepository.findByTitleContaining("Test", pageable))
                .thenReturn(list);

        this.mockMvc.perform(get("/movies/searchbytitle/Test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(5)))
                .andExpect(jsonPath("$[0].title", is("Test")))
                .andExpect(jsonPath("$[0].genre", is("TestGenre")))
                .andExpect(jsonPath("$[0].description", is("TestDescription")))
                .andExpect(jsonPath("$[0].duration", is(77)))
                .andExpect(jsonPath("$[0].releaseDate", is("1998-04-05")));

        Mockito.verify(movieRepository, times(1))
                .findByTitleContaining("Test", pageable);
    }

    @Test
    public void testDeleteMovie() throws Exception {

        Mockito.doNothing().when(movieRepository).deleteById(5L);

        mockMvc.perform(delete("/movies/5"))
                /*.andDo(print())*/
                .andExpect(status().isNoContent());

        Mockito.verify(movieRepository, times(1)).deleteById(5L);
    }

    @Test
    public void testSearchMoviesByReleaseDate() throws Exception {

        List<MovieEntity> list = new ArrayList<>();
        list.add(testMovieEntity);


        LocalDate date = LocalDate.of(1998, Month.APRIL, 5);
        Mockito.when(movieRepository.findAllByReleaseDate(date, pageable)).thenReturn(list);

        this.mockMvc.perform(get("/movies/searchbydate/1998-04-05"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", is(5)))
                .andExpect(jsonPath("$[0].title", is("Test")))
                .andExpect(jsonPath("$[0].genre", is("TestGenre")))
                .andExpect(jsonPath("$[0].description", is("TestDescription")))
                .andExpect(jsonPath("$[0].duration", is(77)))
                .andExpect(jsonPath("$[0].releaseDate", is("1998-04-05")));

        Mockito.verify(movieRepository, times(1))
                .findAllByReleaseDate(date, pageable);

    }


}