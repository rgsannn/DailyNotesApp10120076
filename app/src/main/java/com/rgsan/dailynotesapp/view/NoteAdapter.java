package com.rgsan.dailynotesapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.rgsan.dailynotesapp.R;
import com.rgsan.dailynotesapp.model.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 10120076
 * Rifqi Galih Nur Ikhsan
 * IF-2
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> noteList;
    private FragmentActivity fragmentActivity;

    public NoteAdapter(List<Note> noteList, FragmentActivity fragmentActivity) {
        this.noteList = noteList;
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView idCatatanTextView, titleTextView, dateTextView;

        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            idCatatanTextView = itemView.findViewById(R.id.idCatatanTextView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            itemView.setOnClickListener(this);
        }

        void bind(Note note) {
            idCatatanTextView.setText(note.getIdCatatan());
            titleTextView.setText(note.getJudul());

            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

            try {
                Date tanggal = inputFormat.parse(note.getTanggal());
                String tanggalHanya = outputFormat.format(tanggal);
                dateTextView.setText(tanggalHanya);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Note note = noteList.get(position);
                String idCatatan = note.getIdCatatan();
                navigateToDetailsNotesFragment(idCatatan);
            }
        }
    }

    private void navigateToDetailsNotesFragment(String idCatatan) {
        DetailsNotesFragment fragmentDetailsNotes = DetailsNotesFragment.newInstance(idCatatan);

        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragmentDetailsNotes);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
