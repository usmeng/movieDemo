package meng.com.moivelist.data;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by mengzhou on 7/14/19.
 */

public interface RestAPI {

    @GET("discover/movie")
    Single<MovieResponseModel> mostPopularMovies(@QueryMap @NonNull Map<String, String> values);

    @GET("genre/movie/list")
    Single<GenresResponseModel> genreList(@QueryMap @NonNull Map<String, String> values);

    interface Callback<T> {
        void onResult(T result);
        void onError(Throwable error);
    }
}
