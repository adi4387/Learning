package intg.com.reactivespring.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.reactivespring.domain.MovieInfo;
import com.reactivespring.repository.MovieInfoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

@DataMongoTest
@ActiveProfiles("test")
class MovieInfoRepositoryIntegrationTest {

    @Autowired
    MovieInfoRepository movieInfoRepository;

    @BeforeEach
    void setUp() {
        var movieInfos = List.of(new MovieInfo(null, "Batman Begins",
                2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15")),
                new MovieInfo(null, "The Dark Knight",
                        2008, List.of("Christian Bale", "HeathLedger"), LocalDate.parse("2008-07-18")),
                new MovieInfo(UUID.randomUUID().toString(), "Dark Night Rises",
                        2012, List.of("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-28"))
        );
        movieInfoRepository.saveAll(movieInfos)
                .blockLast();
    }

    @AfterEach
    void tearDown() {
        movieInfoRepository.deleteAll().block();
    }

    @Test
    void findAll() {
        //given

        //when
        var moviesInfo = movieInfoRepository.findAll().log();

        //then
        StepVerifier.create(moviesInfo)
                .expectNextCount(3)
                .verifyComplete();

    }

    @Test
    void findByYear() {
        //given

        //when
        var moviesInfo = movieInfoRepository.findByYear(2008).log();

        //then
        StepVerifier.create(moviesInfo)
                .expectNextCount(1)
                .verifyComplete();

    }

    @Test
    void findByName() {
        //given

        //when
        var moviesInfo = movieInfoRepository.findByName("Batman Begins").log();

        //then
        StepVerifier.create(moviesInfo)
                .expectNextCount(1)
                .verifyComplete();

    }
}