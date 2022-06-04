package com.example.dictionaryapp.viewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;

public class PhoneticViewHolder extends RecyclerView.ViewHolder {
    public TextView textView_Phonetics;
    public ImageButton imageButton_audio;


    public PhoneticViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_Phonetics = itemView.findViewById(R.id.textView_Phonetic);
        imageButton_audio = itemView.findViewById(R.id.imageButton_audio);


    }
}
