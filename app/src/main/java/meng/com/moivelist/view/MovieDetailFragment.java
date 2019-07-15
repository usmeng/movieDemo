package meng.com.moivelist.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import meng.com.moivelist.R;
import meng.com.moivelist.databinding.MovieItemDetailViewBinding;
import meng.com.moivelist.transformer.MovieViewModel;

/**
 * Created by mengzhou on 7/15/19.
 */

public class MovieDetailFragment extends Fragment {

    public static final String TAG = MovieDetailFragment.class.getSimpleName();
    public static final String MOVIE_DETAIL_KEY = "movie_detail";
    private MovieItemDetailViewBinding movieItemDetailViewBinding;
    private MovieViewModel movieDetailViewModel;

    public static MovieDetailFragment newInstance(Bundle bundle) {
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.movieDetailViewModel = (MovieViewModel) bundle.getSerializable(MOVIE_DETAIL_KEY);
        return movieDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        movieItemDetailViewBinding = DataBindingUtil.inflate(inflater, R.layout.movie_item_detail_view, container, false);
        return movieItemDetailViewBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieItemDetailViewBinding.setViewmodel(movieDetailViewModel);
        Glide.with(getContext()).load(movieDetailViewModel.originImagePath).into(movieItemDetailViewBinding.movieImage);
    }
}
