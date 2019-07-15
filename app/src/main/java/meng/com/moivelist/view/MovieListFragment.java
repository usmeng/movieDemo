package meng.com.moivelist.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import meng.com.moivelist.R;
import meng.com.moivelist.data.RetrofitAPI;
import meng.com.moivelist.databinding.MovieListFragmentBinding;
import meng.com.moivelist.transformer.MovieTransformer;

/**
 * Created by mengzhou on 7/14/19.
 */

public class MovieListFragment extends Fragment {

    public static final String TAG = MovieListFragment.class.getSimpleName();
    private static final int SMALLEST_PAGE = 1;
    private MovieListFragmentBinding movieListFragmentBinding;
    private MovieListAdapter movieListAdapter;
    private MovieTransformer movieListTransformer;
    private int page = SMALLEST_PAGE;

    public static MovieListFragment newInstance(Bundle argus) {
        return new MovieListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieListTransformer = new MovieTransformer();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchData(page);
    }

    private void fetchData(int page) {
        RetrofitAPI.getInstance().getMostPopularMovies(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieServerModels -> movieListAdapter.addData(movieListTransformer.toMovieList(movieServerModels)));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        movieListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment, container, false);
        movieListAdapter = new MovieListAdapter(getContext());
        movieListFragmentBinding.movieRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        movieListFragmentBinding.movieRecycleview.setAdapter(movieListAdapter);
        movieListFragmentBinding.movieRecycleview.addOnScrollListener(getOnScrollListener());
        movieListAdapter.setOnItemClickListener(movieViewModel -> EventBus.getDefault().post(new MovieDetailEvent(movieViewModel)));
        return movieListFragmentBinding.getRoot();
    }

    @NonNull
    private RecyclerView.OnScrollListener getOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (!movieListFragmentBinding.movieRecycleview.canScrollVertically(1)) {
                        fetchData(++page);
                    }
                }
            };
    }

    public interface MovieOnItemClickListener {

        void onItemClick(MovieViewModel movieViewModel);
    }

}
