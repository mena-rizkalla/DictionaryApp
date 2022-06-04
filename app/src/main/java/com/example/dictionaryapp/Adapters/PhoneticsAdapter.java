package com.example.dictionaryapp.Adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;
import com.example.dictionaryapp.model.Phonetics;
import com.example.dictionaryapp.viewHolders.PhoneticViewHolder;

import java.io.IOException;
import java.util.List;

public class PhoneticsAdapter extends RecyclerView.Adapter<PhoneticViewHolder> {
    private Context context;
    private List<Phonetics> phoneticsList;

    public PhoneticsAdapter(Context context, List<Phonetics> phoneticsList) {
        this.context = context;
        this.phoneticsList = phoneticsList;
    }

    @NonNull
    @Override
    public PhoneticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new PhoneticViewHolder(LayoutInflater.from(context).inflate(R.layout.phonetics_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneticViewHolder holder, int position) {
       holder.textView_Phonetics.setText(phoneticsList.get(holder.getAdapterPosition()).getText());
       holder.imageButton_audio.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               MediaPlayer mediaPlayer = new MediaPlayer();
               try {
                   mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                   mediaPlayer.setDataSource(phoneticsList.get(holder.getAdapterPosition()).getAudio());
                   mediaPlayer.prepare();
                   mediaPlayer.start();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       });
    }

    @Override
    public int getItemCount() {
        return phoneticsList.size();
    }
}
