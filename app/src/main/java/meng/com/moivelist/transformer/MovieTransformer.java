package meng.com.moivelist.transformer;

import java.util.ArrayList;
import java.util.List;

import meng.com.moivelist.data.MovieServerModel;
import meng.com.moivelist.data.RetrofitAPI;

/**
 * Created by mengzhou on 7/14/19.
 */

public class MovieTransformer {

    public List<MovieViewModel> toMovieList(List<MovieServerModel> movieServerModelList) {
        List<MovieViewModel> list = new ArrayList<>();
        for (MovieServerModel model : movieServerModelList) {
            list.add(toMovieViewModel(model));
        }
        return list;
    }

    public MovieViewModel toMovieViewModel(MovieServerModel model) {
        MovieViewModel movieViewModel = new MovieViewModel();
        movieViewModel.thumbNailImagePath = RetrofitAPI.IMAGE_BASE_URL + RetrofitAPI.IMAGE_SIZE_300 + model.poster_path;
        movieViewModel.originImagePath = RetrofitAPI.IMAGE_BASE_URL + RetrofitAPI.IMAGE_SIZE_1280 + model.poster_path;
        movieViewModel.title = model.title;
        movieViewModel.overview = model.overview;
        movieViewModel.genre = model.genre_ids.toString();
        movieViewModel.releaseYear = model.release_date;
        movieViewModel.score = String.valueOf(model.popularity);
        return movieViewModel;
    }
}
