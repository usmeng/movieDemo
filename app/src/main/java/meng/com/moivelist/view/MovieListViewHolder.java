package meng.com.moivelist.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import meng.com.moivelist.databinding.MovieItemViewBinding;

/**
 * Created by mengzhou on 7/15/19.
 */

class MovieListViewHolder extends RecyclerView.ViewHolder {

    private MovieItemViewBinding movieItemViewBinding;

    public MovieListViewHolder(View itemView) {
        super(itemView);
    }

    public void setMovieItemViewBinding(MovieItemViewBinding movieItemViewBinding) {
        this.movieItemViewBinding = movieItemViewBinding;
    }

    public MovieItemViewBinding getMovieItemViewBinding() {
        return movieItemViewBinding;
    }
}
