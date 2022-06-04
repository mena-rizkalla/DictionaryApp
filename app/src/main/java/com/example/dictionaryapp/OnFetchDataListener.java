package com.example.dictionaryapp;

import com.example.dictionaryapp.model.APIResponce;

public interface OnFetchDataListener {
    void onFetchData(APIResponce apiResponce , String message);
    void onError(String message);
}
