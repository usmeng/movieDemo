package meng.com.moivelist.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import meng.com.moivelist.R;
import meng.com.moivelist.databinding.MovieItemViewBinding;

/**
 * Created by mengzhou on 7/14/19.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListViewHolder> {

    private List<MovieViewModel> data = new ArrayList<>();
    private Context context;
    private MovieListFragment.MovieOnItemClickListener onItemClickListener;

    public MovieListAdapter(@NonNull Context context) {
        this.context = context;
    }

    public List<MovieViewModel> getData() {
        return data;
    }

    public void addData(List<MovieViewModel> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public MovieListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieItemViewBinding movieItemViewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.movie_item_view, null, false);
        MovieListViewHolder movieListViewHolder = new MovieListViewHolder(movieItemViewBinding.getRoot());
        movieListViewHolder.setMovieItemViewBinding(movieItemViewBinding);
        return movieListViewHolder;
    }

    @Override
    public void onBindViewHolder(final MovieListViewHolder holder, int position) {
        MovieViewModel movieViewModel = data.get(position);
        MovieItemViewBinding movieItemViewBinding = holder.getMovieItemViewBinding();
        Glide.with(context).load(movieViewModel.thumbNailImagePath).into(movieItemViewBinding.movieImage);
        movieItemViewBinding.setViewmodel(movieViewModel);
        holder.getMovieItemViewBinding().movieContainer.setOnClickListener(v -> {
            if (onItemClickListener == null) return;
            onItemClickListener.onItemClick(movieViewModel);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(MovieListFragment.MovieOnItemClickListener clickListener) {
        this.onItemClickListener = clickListener;
    }
}
