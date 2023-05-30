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

import java.util.List;

/**
 * 10120076
 * Rifqi Galih Nur Ikhsan
 * IF-2
 */

public class UpdateDailyNotesFragment extends Fragment implements NotePresenter.View {
    private EditText judulEditText;
    private EditText kategoriEditText;
    private EditText catatanEditText;
    private Button updateCatatanButton;
    private NotePresenter notePresenter;
    private String idCatatan;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notePresenter = new NotePresenter(this, requireContext());
        idCatatan = getArguments().getString("id_catatan");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_daily_notes, container, false);
        ImageView backButton = view.findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> requireActivity().onBackPressed());

        judulEditText = view.findViewById(R.id.judulEditText);
        kategoriEditText = view.findViewById(R.id.kategoriEditText);
        catatanEditText = view.findViewById(R.id.catatanEditText);
        updateCatatanButton = view.findViewById(R.id.updateCatatanButton);
        updateCatatanButton.setOnClickListener(v -> updateCatatan());

        notePresenter.getNoteById(idCatatan);

        return view;
    }

    @Override
    public void showNotes(List<Note> notes) {
    }

    @Override
    public void showNoteDetail(Note note) {
        requireActivity().runOnUiThread(() -> {
            judulEditText.setText(note.getJudul());
            kategoriEditText.setText(note.getKategori());
            catatanEditText.setText(note.getCatatan());
        });
    }

    @Override
    public void showNoteNotFound() {
        requireActivity().runOnUiThread(() -> Toast.makeText(requireContext(), "Catatan tidak ditemukan", Toast.LENGTH_SHORT).show());
    }

    private void updateCatatan() {
        String judul = judulEditText.getText().toString();
        String kategori = kategoriEditText.getText().toString();
        String catatan = catatanEditText.getText().toString();

        if (judul.isEmpty() || kategori.isEmpty() || catatan.isEmpty()) {
            Toast.makeText(getContext(), "Harap lengkapi semua data", Toast.LENGTH_SHORT).show();
            return;
        }

        notePresenter.updateNoteById(idCatatan, judul, kategori, catatan);

        Toast.makeText(getContext(), "Catatan berhasil diperbarui", Toast.LENGTH_SHORT).show();
        requireActivity().getSupportFragmentManager().popBackStack();
    }
}
