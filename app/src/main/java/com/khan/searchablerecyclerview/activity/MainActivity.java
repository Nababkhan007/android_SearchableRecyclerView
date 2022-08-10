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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private List<String> moviesList;
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
        recyclerAdapter = new RecyclerAdapter(moviesList);
        binding.recyclerView.setAdapter(recyclerAdapter);
        DividerItemDecoration dividerItemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        binding.recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void addMovies() {
        moviesList.add("Iron Man");
        moviesList.add("The Incredible Hulk");
        moviesList.add("Iron Man 2");
        moviesList.add("Thor");
        moviesList.add("Captain America: The First Avenger");
        moviesList.add("The Avengers");
        moviesList.add("Iron Man 3");
        moviesList.add("Thor: The Dark World");
        moviesList.add("Captain America: The Winter Soldier");
        moviesList.add("Guardians of the Galaxy");
        moviesList.add("Avengers: Age of Ultron");
        moviesList.add("Ant-Man");
        moviesList.add("Captain America: Civil War");
        moviesList.add("Doctor Strange");
        moviesList.add("Guardians of the Galaxy Vol. 2");
        moviesList.add("Spider-Man: Homecoming");
        moviesList.add("Thor: Ragnarok");
        moviesList.add("Black Panther");
        moviesList.add("Avengers: Infinity War");
        moviesList.add("Ant-Man and the Wasp");
        moviesList.add("Captain Marvel");
        moviesList.add("Avengers: Endgame");
        moviesList.add("Spider-Man: Far From Home");
    }

    private void init() {
        moviesList = new ArrayList<>();
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
