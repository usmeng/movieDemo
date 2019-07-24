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
        notifyItemRangeChanged(this.data.size() - data.size(), data.size());
    }

    @Override
    public MovieListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieListViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.movie_item_view, null, false));
    }

    @Override
    public void onBindViewHolder(final MovieListViewHolder holder, int position) {
        MovieViewModel movieViewModel = data.get(position);
        Glide.with(context).load(movieViewModel.thumbNailImagePath).into(holder.binding.movieImage);
        holder.binding.setViewmodel(movieViewModel);
        holder.binding.movieContainer.setOnClickListener(v -> {
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
