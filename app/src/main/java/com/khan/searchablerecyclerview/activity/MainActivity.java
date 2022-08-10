package com.khan.searchablerecyclerview.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.khan.searchablerecyclerview.R;
import com.khan.searchablerecyclerview.adapter.RecyclerAdapter;
import com.khan.searchablerecyclerview.databinding.ActivityMainBinding;
import com.khan.searchablerecyclerview.model.Footballer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private List<Footballer> footballerList;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindingInit();

        init();

        addMovies();

        setMoviesData();
    }

    private void setMoviesData() {
        recyclerAdapter = new RecyclerAdapter(this, footballerList);
        binding.recyclerView.setAdapter(recyclerAdapter);
        DividerItemDecoration dividerItemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        binding.recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void addMovies() {
        footballerList.add(new Footballer(R.drawable.cristiano_ronaldo,
                "Cristiano Ronaldo", "37 years",
                "6 ft 2 in", "7",
                "Forward", "Portugal",
                "Manchester United", "Portugal"));
        footballerList.add(new Footballer(R.drawable.lionel_messi,
                "Lionel Messi", "35 years",
                "5 ft 7 in", "30",
                "Forward", "Argentina",
                "Paris Saint-Germain", "Argentina"));
        footballerList.add(new Footballer(R.drawable.neymar,
                "Neymar", "30 years",
                "5 ft 9 in", "10",
                "Forward", "Brazil",
                "Paris Saint-Germain", "Brazil"));
        footballerList.add(new Footballer(R.drawable.mohamed_salah,
                "Mohamed Salah", "30 years",
                "5 ft 9 in", "11",
                "Forward, Winger", "Egypt",
                "Liverpool", "Egypt"));
        footballerList.add(new Footballer(R.drawable.kylian_mbappe,
                "Kylian Mbappé", "23 years",
                "5 ft 10 in", "7",
                "Forward", "France",
                "Paris Saint-Germain", "France"));
        footballerList.add(new Footballer(R.drawable.thiago_silva,
                "Thiago Silva", "37 years",
                "5 ft 11 in", "3",
                "Centre-back", "Brazil",
                "Chelsea", "Brazil"));
        footballerList.add(new Footballer(R.drawable.kevin_de_bruyne,
                "Kevin De Bruyne", "31 years",
                "5 ft 11 in", "17",
                "Midfielder", "Belgium",
                "Manchester City", "Belgium"));
        footballerList.add(new Footballer(R.drawable.marcelo_vieira,
                "Marcelo Vieira", "34 years",
                "5 ft 9 in", "12",
                "Left-back", "Brazil",
                "Real Madrid", "Brazil"));
        footballerList.add(new Footballer(R.drawable.alisson_becker,
                "Alisson Becker", "29 years",
                "6 ft 3 in", "1",
                "Goalkeeper", "Brazil",
                "Liverpool", "Brazil"));
    }

    private void init() {
        footballerList = new ArrayList<>();
    }

    private void bindingInit() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
