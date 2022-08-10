package com.khan.searchablerecyclerview.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.khan.searchablerecyclerview.R;
import com.khan.searchablerecyclerview.databinding.ModelListItemBinding;
import com.khan.searchablerecyclerview.model.Footballer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
        implements Filterable {
    private final Context context;
    private final List<Footballer> footballerList;
    private final List<Footballer> footballerListAll;

    public RecyclerAdapter(Context context, List<Footballer> footballerList) {
        this.context = context;
        this.footballerList = footballerList;
        this.footballerListAll = new ArrayList<>(footballerList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ModelListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.model_list_item, viewGroup, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Footballer footballer = footballerList.get(position);
        holder.binding.imageView.setImageResource(footballer.getImage());
        holder.binding.nameTv.setText(footballer.getName());
        holder.binding.ageTv.setText(context.getString(R.string.age_concatenate,
                footballer.getAge()));
        holder.binding.heightTv.setText(context.getString(R.string.height_concatenate,
                footballer.getHeight()));
        holder.binding.jerseyNumberTv.setText(context.getString(R.string.jersey_number_concatenate,
                footballer.getJerseyNumber()));
        holder.binding.positionTv.setText(context.getString(R.string.position_concatenate,
                footballer.getPosition()));
        holder.binding.nationalTeamTv.setText(context.getString(R.string.national_team_concatenate,
                footballer.getNationalTeam()));
        holder.binding.clubTeamTv.setText(context.getString(R.string.club_team_concatenate,
                footballer.getClubTeam()));
        holder.binding.countryTv.setText(context.getString(R.string.country_concatenate,
                footballer.getCountry()));

        holder.binding.modelRootLayout.setOnClickListener(v ->
                Toast.makeText(context, "" + footballer.getName(),
                        Toast.LENGTH_SHORT).show());

        holder.binding.modelRootLayout.setOnLongClickListener(view -> {
            footballerList.remove(position);
            notifyItemRemoved(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return footballerList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Footballer> filteredList = new ArrayList<>();

            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(footballerListAll);

            } else {
                for (Footballer footballer : footballerListAll) {
                    if (footballer.getName().toLowerCase(Locale.ROOT)
                            .contains(charSequence.toString().toLowerCase(Locale.ROOT))
                            || footballer.getAge().toLowerCase(Locale.ROOT)
                            .contains(charSequence.toString().toLowerCase(Locale.ROOT))
                            || footballer.getHeight().toLowerCase(Locale.ROOT)
                            .contains(charSequence.toString().toLowerCase(Locale.ROOT))
                            || footballer.getJerseyNumber().toLowerCase(Locale.ROOT)
                            .contains(charSequence.toString().toLowerCase(Locale.ROOT))
                            || footballer.getPosition().toLowerCase(Locale.ROOT)
                            .contains(charSequence.toString().toLowerCase(Locale.ROOT))
                            || footballer.getNationalTeam().toLowerCase(Locale.ROOT)
                            .contains(charSequence.toString().toLowerCase(Locale.ROOT))
                            || footballer.getClubTeam().toLowerCase(Locale.ROOT)
                            .contains(charSequence.toString().toLowerCase(Locale.ROOT))
                            || footballer.getCountry().toLowerCase(Locale.ROOT)
                            .contains(charSequence.toString().toLowerCase(Locale.ROOT))) {
                        filteredList.add(footballer);
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
            footballerList.clear();
            footballerList.addAll((Collection<? extends Footballer>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ModelListItemBinding binding;

        ViewHolder(@NonNull ModelListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}















