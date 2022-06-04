package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionaryapp.Adapters.MeaningAdapter;
import com.example.dictionaryapp.Adapters.PhoneticsAdapter;
import com.example.dictionaryapp.model.APIResponce;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    TextView textView_word;
    RecyclerView recyclerView_phonetics, recyclerView_meanings;
    ProgressDialog progressDialog;
    PhoneticsAdapter phoneticsAdapter;
    MeaningAdapter meaningAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_view);
        textView_word = findViewById(R.id.textview_word);
        recyclerView_meanings = findViewById(R.id.recycler_meanings);
        recyclerView_phonetics = findViewById(R.id.recycler_phonetics);
        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Loading" );
        progressDialog.show();
        RequestManager requestManager =new RequestManager(MainActivity.this);
        requestManager.getWordMeaning(listener,"hello");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setMessage("Searching for" + query);
                progressDialog.show();
                RequestManager requestManager =new RequestManager(MainActivity.this);
                requestManager.getWordMeaning(listener,query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponce apiResponce, String message) {
            progressDialog.dismiss();
            if (apiResponce == null){
                Toast.makeText(MainActivity.this,"no data found",Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponce);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(APIResponce apiResponce) {
        textView_word.setText("Word : " + apiResponce.getWord());
        recyclerView_phonetics.setHasFixedSize(true);
        recyclerView_phonetics.setLayoutManager(new GridLayoutManager(this,1));
        phoneticsAdapter = new PhoneticsAdapter(MainActivity.this,apiResponce.getPhonetics());
        recyclerView_phonetics.setAdapter(phoneticsAdapter);

        recyclerView_meanings.setHasFixedSize(true);
        recyclerView_meanings.setLayoutManager(new GridLayoutManager(this,1));
        meaningAdapter = new MeaningAdapter(MainActivity.this,apiResponce.getMeanings());
        recyclerView_meanings.setAdapter(meaningAdapter);

    }
}