package meng.com.moivelist.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import meng.com.moivelist.databinding.MovieItemViewBinding;

/**
 * Created by mengzhou on 7/15/19.
 */

class MovieListViewHolder extends RecyclerView.ViewHolder {

    public MovieItemViewBinding binding;

    public MovieListViewHolder(@NonNull MovieItemViewBinding movieItemViewBinding) {
        super(movieItemViewBinding.getRoot());
        this.binding = movieItemViewBinding;
    }

}
