package com.rgsan.dailynotesapp.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.rgsan.dailynotesapp.R;
import com.rgsan.dailynotesapp.model.Note;
import com.rgsan.dailynotesapp.presenter.NotePresenter;

import java.util.List;

/**
 * 10120076
 * Rifqi Galih Nur Ikhsan
 * IF-2
 */

public class DetailsNotesFragment extends Fragment implements NotePresenter.View {
    private TextView idCatatanTextView, judulHeaderTextView, tanggalTextView, kategoriTextView, catatanTextView;
    private NotePresenter notePresenter;

    public static DetailsNotesFragment newInstance(String idCatatan) {
        DetailsNotesFragment fragment = new DetailsNotesFragment();
        Bundle args = new Bundle();
        args.putString("id_catatan", idCatatan);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notePresenter = new NotePresenter(this, requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_notes, container, false);
        idCatatanTextView = view.findViewById(R.id.idCatatanTextView);
        judulHeaderTextView = view.findViewById(R.id.judulHeaderTextView);
        kategoriTextView = view.findViewById(R.id.kategoriTextView);
        catatanTextView = view.findViewById(R.id.catatanTextView);
        tanggalTextView = view.findViewById(R.id.tanggalTextView);

        ImageView backButton = view.findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> requireActivity().onBackPressed());

        Bundle args = getArguments();
        if (args != null) {
            String idCatatan = args.getString("id_catatan");
            idCatatanTextView.setText(idCatatan);
            notePresenter.getNoteById(idCatatan);
        }

        ImageView actionBar = view.findViewById(R.id.action_bar);
        actionBar.setOnClickListener(v -> showPopupMenu(v, idCatatanTextView.getText().toString()));

        return view;
    }

    private void showPopupMenu(View view, String idCatatan) {
        PopupMenu popupMenu = new PopupMenu(requireContext(), view);
        popupMenu.inflate(R.menu.detail_notes_menu_toolbar);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.ubah_data:
                    navigateToUpdateDailyNotes(idCatatan);
                    return true;
                case R.id.hapus_data:
                    showDeleteConfirmationDialog(idCatatan);
                    return true;
                default:
                    return false;
            }
        });

        popupMenu.show();
    }

    private void navigateToUpdateDailyNotes(String idCatatan) {
        Bundle args = new Bundle();
        args.putString("id_catatan", idCatatan);
        UpdateDailyNotesFragment fragmentUpdateDailyNotes = new UpdateDailyNotesFragment();
        fragmentUpdateDailyNotes.setArguments(args);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragmentUpdateDailyNotes)
                .addToBackStack(null)
                .commit();
    }

    private void showDeleteConfirmationDialog(String idCatatan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Konfirmasi");
        builder.setMessage("Apakah Anda yakin ingin menghapus data ini?");
        builder.setPositiveButton("Ya", (dialog, which) -> {
            notePresenter.deleteNoteById(idCatatan);
            Toast.makeText(getContext(), "Catatan berhasil dihapus", Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStack();
        });
        builder.setNegativeButton("Tidak", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showNotes(List<Note> notes) {
    }

    @Override
    public void showNoteDetail(Note note) {
        getActivity().runOnUiThread(() -> {
            judulHeaderTextView.setText(note.getJudul());
            kategoriTextView.setText(note.getKategori());
            tanggalTextView.setText(note.getTanggal());
            catatanTextView.setText(note.getCatatan());
        });
    }

    @Override
    public void showNoteNotFound() {
        getActivity().runOnUiThread(() -> {
            Toast.makeText(requireContext(), "Catatan tidak ditemukan", Toast.LENGTH_SHORT).show();
        });
    }
}