package com.rgsan.dailynotesapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rgsan.dailynotesapp.R;
import com.rgsan.dailynotesapp.model.Note;
import com.rgsan.dailynotesapp.presenter.NotePresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 10120076
 * Rifqi Galih Nur Ikhsan
 * IF-2
 */

public class AddDailyNotesFragment extends Fragment implements NotePresenter.View {
    private EditText judulEditText;
    private EditText kategoriEditText;
    private EditText catatanEditText;
    private Button tambahCatatanButton;
    private NotePresenter notePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notePresenter = new NotePresenter(this, requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_daily_notes, container, false);
        ImageView backButton = view.findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> requireActivity().onBackPressed());

        judulEditText = view.findViewById(R.id.judulEditText);
        kategoriEditText = view.findViewById(R.id.kategoriEditText);
        catatanEditText = view.findViewById(R.id.catatanEditText);
        tambahCatatanButton = view.findViewById(R.id.tambahCatatanButton);
        tambahCatatanButton.setOnClickListener(v -> tambahCatatan());

        return view;
    }

    private void tambahCatatan() {
        String id_catatan = notePresenter.generateIdCatatan();
        String judul = judulEditText.getText().toString();
        String kategori = kategoriEditText.getText().toString();
        String catatan = catatanEditText.getText().toString();
        Date tanggal = new Date();

        if (judul.isEmpty() || kategori.isEmpty() || catatan.isEmpty()) {
            Toast.makeText(getContext(), "Harap lengkapi semua data", Toast.LENGTH_SHORT).show();
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String formattedDate = dateFormat.format(tanggal);

        Note note = new Note(id_catatan, judul, kategori, catatan, formattedDate);
        notePresenter.addNote(note);

        Toast.makeText(getContext(), "Catatan berhasil ditambahkan", Toast.LENGTH_SHORT).show();
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void showNotes(List<Note> notes) {
    }

    @Override
    public void showNoteDetail(Note note) {
    }

    @Override
    public void showNoteNotFound() {
    }
}
