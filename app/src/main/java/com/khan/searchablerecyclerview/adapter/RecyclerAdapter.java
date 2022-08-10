package com.khan.searchablerecyclerview.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.khan.searchablerecyclerview.R;
import com.khan.searchablerecyclerview.databinding.RowItemBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {
    private final List<String> moviesList;
    private final List<String> moviesListAll;

    public RecyclerAdapter(List<String> moviesList) {
        this.moviesList = moviesList;
        this.moviesListAll = new ArrayList<>(moviesList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RowItemBinding rowItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.row_item, viewGroup, false);

        return new ViewHolder(rowItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rowItemBinding.rowCountTextView.setText(String.valueOf(position));
        holder.rowItemBinding.textView.setText(moviesList.get(position));
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<String> filteredList = new ArrayList<>();

            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(moviesListAll);

            } else {
                for (String movie : moviesListAll) {
                    if (movie.toLowerCase(Locale.ROOT)
                            .contains(charSequence.toString().toLowerCase(Locale.ROOT))) {
                        filteredList.add(movie);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            moviesList.clear();
            moviesList.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final RowItemBinding rowItemBinding;

        ViewHolder(@NonNull RowItemBinding rowItemBinding) {
            super(rowItemBinding.getRoot());
            this.rowItemBinding = rowItemBinding;

            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(view -> {
                moviesList.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
                return true;
            });
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),
                    moviesList.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    }
}















