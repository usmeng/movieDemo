package meng.com.moivelist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.android.schedulers.AndroidSchedulers;
import meng.com.moivelist.data.RetrofitAPI;
import meng.com.moivelist.transformer.MovieDataProvider;
import meng.com.moivelist.transformer.MovieTransformer;
import meng.com.moivelist.view.MovieDetailEvent;
import meng.com.moivelist.view.MovieDetailFragment;
import meng.com.moivelist.view.MovieListFragment;

/**
 * Created by mengzhou on 7/14/19.
 */

public class MainActivity extends AppCompatActivity {

    private MovieListFragment movieListFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);

        initGenreMap();

        movieListFragment = MovieListFragment.newInstance(new Bundle());
        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.movie_fragment, movieListFragment, MovieListFragment.TAG)
                                   .commit();
    }

    private void initGenreMap() {
        RetrofitAPI.getInstance().getGenreList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(genres -> MovieDataProvider.getInstance().initGenreMap(MovieTransformer.toGenreMap(genres)));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMovieDetailEvent(MovieDetailEvent movieDetailEvent) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MovieDetailFragment.MOVIE_DETAIL_KEY, movieDetailEvent.getMovieViewModel());
        MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance(bundle);
        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.movie_fragment, movieDetailFragment, MovieDetailFragment.TAG)
                                   .addToBackStack("")
                                   .hide(movieListFragment)
                                   .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
