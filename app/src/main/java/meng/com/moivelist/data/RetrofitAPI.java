package meng.com.moivelist.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mengzhou on 7/14/19.
 */

public class RetrofitAPI {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "api_key";
    private static final String API_KEY_VALUE = "93084ce1f144ab7bf7f4e11fcb43afec";
    private static final String SORT_BY = "sort_by";
    private static final String SORT_BY_VALUE = "popularity.desc";
    private static final String PAGE = "page";

    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";
    public static final String IMAGE_SIZE_300 = "w300/";
    public static final String IMAGE_SIZE_1280 = "w780/";

    private static RetrofitAPI mRetrofitAPI = new RetrofitAPI();
    private RestAPI mAPI;

    private RetrofitAPI() {
        Gson gson = new GsonBuilder().create();
        mAPI = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(RestAPI.class);
    }

    public static RetrofitAPI getInstance() {
        return mRetrofitAPI;
    }

    public Observable<List<MovieServerModel>> getMostPopularMovies(int page) {
        Map<String, String> values = new HashMap<>();
        values.put(PAGE, String.valueOf(page));
        values.put(API_KEY, API_KEY_VALUE);
        values.put(SORT_BY, SORT_BY_VALUE);

        return mAPI.mostPopularMovies(values).subscribeOn(Schedulers.io())
                .map(response -> response.results);
    }

    public Observable<List<GenresResponseModel.Genre>> getGenreList() {
        Map<String, String> values = new HashMap<>();
        values.put(API_KEY, API_KEY_VALUE);

        return mAPI.genreList(values).subscribeOn(Schedulers.io()).map(genresResponseModel -> genresResponseModel.genres);
    }
}
