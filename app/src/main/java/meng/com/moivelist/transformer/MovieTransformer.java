package meng.com.moivelist.transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import meng.com.moivelist.data.GenresResponseModel;
import meng.com.moivelist.data.MovieServerModel;
import meng.com.moivelist.data.RetrofitAPI;
import meng.com.moivelist.view.MovieViewModel;

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
        movieViewModel.genre = buildGenre(model.genre_ids);
        movieViewModel.releaseYear = model.release_date;
        movieViewModel.score = String.valueOf(model.popularity);
        return movieViewModel;
    }

    private String buildGenre(List<Integer> genreIDList) {
        if (genreIDList == null || genreIDList.isEmpty()) return "";

        Map<Integer, String> genreMap = MovieDataProvider.getInstance().getGenreMap();
        StringBuilder genreString = new StringBuilder();
        for (int id : genreIDList) {
            genreString.append(genreMap.get(id)).append(", ");
        }
        genreString.delete(genreString.lastIndexOf(","), genreString.length());

        return genreString.toString();
    }

    public static Map<Integer, String> toGenreMap(List<GenresResponseModel.Genre> genreList) {
        Map<Integer, String> genresMap = new HashMap<>();
        if (genreList == null) return genresMap;

        for (GenresResponseModel.Genre genre : genreList) {
            genresMap.put(genre.id, genre.name);
        }

        return genresMap;
    }
}
