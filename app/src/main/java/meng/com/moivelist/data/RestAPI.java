package meng.com.moivelist.data;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by mengzhou on 7/14/19.
 */

public interface RestAPI {

    @GET("discover/movie")
    Observable<MovieResponseModel> mostPopularMovies(@QueryMap @NonNull Map<String, String> values);

    @GET("genre/movie/list")
    Observable<GenresResponseModel> genreList(@QueryMap @NonNull Map<String, String> values);
}
