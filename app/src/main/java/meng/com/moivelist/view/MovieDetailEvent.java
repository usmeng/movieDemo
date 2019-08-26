package meng.com.moivelist.view;

/**
 * Created by mengzhou on 7/15/19.
 */
public class MovieDetailEvent {

    private MovieViewModel movieViewModel;

    public MovieDetailEvent(MovieViewModel movieViewModel) {
        this.movieViewModel = movieViewModel;
    }

    public MovieViewModel getMovieViewModel() {
        return movieViewModel;
    }

}
