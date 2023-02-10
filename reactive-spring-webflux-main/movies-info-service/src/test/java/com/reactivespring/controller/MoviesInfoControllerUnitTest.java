package unit.com.reactivespring.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.reactivespring.controller.MoviesInfoController;
import com.reactivespring.domain.MovieInfo;
import com.reactivespring.service.MovieInfoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = MoviesInfoController.class)
@AutoConfigureWebTestClient
class MoviesInfoControllerUnitTest {

    public static final String MOVIE_INFO_URL = "/v1/movieinfos";

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private MovieInfoService movieInfoService;

    @Test
    void addMovieInfo() {
        //given
        var movieInfo = new MovieInfo(null, "The Dark Knight",
                        2008, List.of("Christian Bale", "HeathLedger"), LocalDate.parse("2008-07-18"));

        var expectedMovieInfo = new MovieInfo("abc", "The Dark Knight",
                2008, List.of("Christian Bale", "HeathLedger"), LocalDate.parse("2008-07-18"));

        when(movieInfoService.addMovieInfo(movieInfo)).thenReturn(Mono.just(expectedMovieInfo));

        //when //then
        webTestClient.post()
                .uri(MOVIE_INFO_URL)
                .bodyValue(movieInfo)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(MovieInfo.class)
                .consumeWith(movieInfoEntityExchangeResult -> {
                    var savedMovieInfo = movieInfoEntityExchangeResult.getResponseBody();
                    assertNotNull(Objects.requireNonNull(savedMovieInfo).getMovieInfoId());
                });
    }

    @Test
    void addMovieInfo_validation() {
        //given
        var movieInfo = new MovieInfo(null, null,
                -2008, List.of(""), LocalDate.parse("2008-07-18"));

        //when //then
        webTestClient.post()
                .uri(MOVIE_INFO_URL)
                .bodyValue(movieInfo)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody(String.class)
                .consumeWith(stringEntityExchangeResult -> {
                    var responseBody = stringEntityExchangeResult.getResponseBody();
                    assertEquals("movieInfo.cast must not be empty, movieInfo.name must be present, movieInfo.year must be a Positive value", responseBody);
                });
    }

    @Test
    void getAllMovieInfos() {
        //given
        var movieInfos = List.of(
                new MovieInfo(null, "The Dark Knight",
                        2008, List.of("Christian Bale", "HeathLedger"), LocalDate.parse("2008-07-18")),
                new MovieInfo("ABC", "Dark Night Rises",
                        2012, List.of("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-28"))
        );

        when(movieInfoService.getAllMovieInfos()).thenReturn(Flux.fromIterable(movieInfos));

        //when //then
        webTestClient.get()
                .uri(MOVIE_INFO_URL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(MovieInfo.class)
                .hasSize(2);
    }

    @Test
    void getMovieInfo() {
        //given
        var movieInfo = new MovieInfo("ABC", "The Dark Knight",
                2008, List.of("Christian Bale", "HeathLedger"), LocalDate.parse("2008-07-18"));

        when(movieInfoService.getMovieInfo("ABC")).thenReturn(Mono.just(movieInfo));

        //when //then
        webTestClient.get()
                .uri(MOVIE_INFO_URL + "/ABC")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("The Dark Knight");
    }

    @Test
    void updateMovieInfo() {
        //given
        var movieInfo = new MovieInfo(null, "Dark Night Rises 1",
                2012, List.of("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-28"));

        var expectedMovieInfo = new MovieInfo("ABC", "Dark Night Rises 1",
                2008, List.of("Christian Bale", "HeathLedger"), LocalDate.parse("2008-07-18"));

        when(movieInfoService.updateMovieInfo(movieInfo, "ABC")).thenReturn(Mono.just(expectedMovieInfo));

        //when //then
        webTestClient.put()
                .uri(MOVIE_INFO_URL + "/ABC")
                .bodyValue(movieInfo)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(MovieInfo.class)
                .consumeWith(movieInfoEntityExchangeResult -> {
                    var updatedMovieInfo = movieInfoEntityExchangeResult.getResponseBody();
                    assertNotNull(updatedMovieInfo);
                    assertNotNull(Objects.requireNonNull(updatedMovieInfo).getMovieInfoId());
                    assertEquals("Dark Night Rises 1", updatedMovieInfo.getName());

                });
    }

    @Test
    void deleteMovieInfo() {
        //given
        when(movieInfoService.deleteMovieInfo("ABC")).thenReturn(Mono.empty());

        //when then
        webTestClient.delete()
                .uri(MOVIE_INFO_URL + "/ABC")
                .exchange()
                .expectStatus()
                .isNoContent();
    }
}