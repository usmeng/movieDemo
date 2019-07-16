package meng.com.moivelist.data;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

import static org.junit.Assert.*;

/**
 * Created by mengzhou on 7/14/19.
 */
public class RetrofitAPITest {
    @Test
    public void getMostPopularMovies() throws Exception {
        System.out.println("---- getMostPopularMovies ----");
        RetrofitAPI api = RetrofitAPI.getInstance();
        Observable<List<MovieServerModel>> mostPopularMovies = api.getMostPopularMovies(1);
        mostPopularMovies.subscribe(movieServerModels -> {
            System.out.println(movieServerModels.size());
            for (MovieServerModel model : movieServerModels) {
                System.out.println(model.title);
            }
        });
        Thread.sleep(5 * 1000);
    }

    @Test
    public void genreList() throws InterruptedException {
        RetrofitAPI api = RetrofitAPI.getInstance();
        api.getGenreList().subscribe(genreList -> System.out.println(genreList.size()));
        Thread.sleep(5 * 1000);
    }

}