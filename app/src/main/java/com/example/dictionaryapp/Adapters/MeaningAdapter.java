package com.example.dictionaryapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;
import com.example.dictionaryapp.model.Meanings;
import com.example.dictionaryapp.viewHolders.MeaningsViewHolder;

import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningsViewHolder> {
    Context context;
    List<Meanings> meanings;

    public MeaningAdapter(Context context, List<Meanings> meanings) {
        this.context = context;
        this.meanings = meanings;
    }

    @NonNull
    @Override
    public MeaningsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeaningsViewHolder(LayoutInflater.from(context).inflate(R.layout.meanings_lsit_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeaningsViewHolder holder, int position) {
        holder.textView_partOfSpeech.setText("Parts Of Speech " + meanings.get(position).getPartOfSpeech());
        holder.recyclerView_definitions.setHasFixedSize(true);
        holder.recyclerView_definitions.setLayoutManager(new GridLayoutManager(context,1));
        DefinitionAdapter definitionAdapter = new DefinitionAdapter(context,meanings.get(position).getDefinitions());
        holder.recyclerView_definitions.setAdapter(definitionAdapter);

    }

    @Override
    public int getItemCount() {
        return meanings.size();
    }
}
