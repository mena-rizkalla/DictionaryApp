package com.example.dictionaryapp.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;

public class MeaningsViewHolder extends RecyclerView.ViewHolder {
    public TextView textView_partOfSpeech;
    public  RecyclerView recyclerView_definitions;
    public MeaningsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_partOfSpeech = itemView.findViewById(R.id.textView_partsOfSpeech);
        recyclerView_definitions = itemView.findViewById(R.id.recyclerview_definition);
    }
}
