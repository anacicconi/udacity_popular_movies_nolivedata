package com.cicconi.popularmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cicconi.popularmovies.R;
import com.cicconi.popularmovies.model.Movie;
import com.cicconi.popularmovies.model.OldMovie;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private static final String IMAGE_URL = "https://image.tmdb.org/t/p/w185";

    private Context context;

    private List<OldMovie> mMovies = new ArrayList<>();

    final private MovieClickListener mClickListener;

    public MovieAdapter(MovieClickListener clickListener) {
        mClickListener = clickListener;
    }

    public void setMoviesData(List<OldMovie> movies, int page) {
        if(page == 1) {
            mMovies = movies;
        } else {
            mMovies.addAll(movies);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_item, parent, false);

        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, int position) {
        OldMovie movie = mMovies.get(position);

        Picasso.with(context)
            .load(IMAGE_URL + movie.getImage())
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(holder.mMovieImageView);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView mMovieImageView;

        MovieAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mMovieImageView = itemView.findViewById(R.id.iv_movie);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mClickListener.onMovieItemClick(mMovies.get(clickedPosition));
        }
    }

    public interface MovieClickListener {
        void onMovieItemClick(OldMovie movie);
    }
}
