package com.example.dictionaryapp;

import android.content.Context;
import android.widget.Toast;

import com.example.dictionaryapp.model.APIResponce;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RequestManager {
    Context context;

    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getWordMeaning(OnFetchDataListener listener , String word){
        CallDictionary callDictionary = retrofit.create(CallDictionary.class);
        Call<List<APIResponce>> call = callDictionary.callMeanings(word);

        call.enqueue(new Callback<List<APIResponce>>() {
            @Override
            public void onResponse(Call<List<APIResponce>> call, Response<List<APIResponce>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context,"error",Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onFetchData(response.body().get(0),response.message());
            }

            @Override
            public void onFailure(Call<List<APIResponce>> call, Throwable t) {
                listener.onError("request failed");
            }
        });
    }
    public interface CallDictionary{
        @GET("entries/en/{word}")
        Call<List<APIResponce>> callMeanings(
                @Path("word") String word
        );
    }
}
