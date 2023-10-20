package com.example.bpart2assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String PIXABAY_API_KEY = "40130339-5a311ed3cd56be44c4eab5b7e";
    private static final int PER_PAGE = 15;
    private static final int START_PAGE = 1;
    private static final int DEFAULT_COLUMNS = 1;

    private EditText queryEditText;
    private Button searchButton;
    private Button toggleViewButton;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private PixabayAPI api;
    private SearchResultAdapter adapter;
    private List<ImageFinalRes> imageFinalRes;

    private int columns = DEFAULT_COLUMNS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queryEditText = findViewById(R.id.query_edit_text);
        searchButton = findViewById(R.id.search_button);
        toggleViewButton = findViewById(R.id.toggle_view_button);
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);

        api = new Retrofit.Builder()
                .baseUrl("https://pixabay.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PixabayAPI.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imageFinalRes = new ArrayList<>();
        adapter = new SearchResultAdapter(imageFinalRes);
        recyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = queryEditText.getText().toString();
                if (!query.isEmpty()) {
                    search(query);
                }
            }
        });

        toggleViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleView();
            }
        });
    }

    private void search(String query) {
        progressBar.setVisibility(View.VISIBLE);
        imageFinalRes.clear();
        adapter.notifyDataSetChanged();

        api.search(PIXABAY_API_KEY, query, PER_PAGE, START_PAGE)
                .enqueue(new Callback<ImageSearchAns>() {
                    @Override
                    public void onResponse(Call<ImageSearchAns> call, Response<ImageSearchAns> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<ImageFinalRes> results = response.body().getImageHits()
                                    .stream()
                                    .map(result -> new ImageFinalRes(result.getId(), result.getLargeImageURL()))
                                    .collect(Collectors.toList());

                            // Limit the number of images to 15 if there are more than 15
                            if (results.size() > PER_PAGE) {
                                results = results.subList(0, PER_PAGE);
                            }

                            imageFinalRes.addAll(results);
                            adapter.notifyDataSetChanged();
                        }
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<ImageSearchAns> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Failed to search.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private static class SearchResultViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public SearchResultViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }

    private static class SearchResultAdapter extends RecyclerView.Adapter<SearchResultViewHolder> {
        private List<ImageFinalRes> imageFinalRes;

        public SearchResultAdapter(List<ImageFinalRes> imageFinalRes) {
            this.imageFinalRes = imageFinalRes;
        }
        @Override
        public int getItemCount() {
            return imageFinalRes.size();
        }
        @Override
        public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.search_result_item, parent, false);
            return new SearchResultViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SearchResultViewHolder holder, int position) {
            ImageFinalRes imageFinalRes = this.imageFinalRes.get(position);
            Glide.with(holder.imageView)
                    .load(imageFinalRes.getPreviewURL())
                    .into(holder.imageView);
        }

    }
    private void toggleView() {
        if (columns == 1) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            columns = 2;
            toggleViewButton.setText(R.string.single_column_view);
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            columns = 1;
            toggleViewButton.setText(R.string.double_column_view);
        }
    }


}