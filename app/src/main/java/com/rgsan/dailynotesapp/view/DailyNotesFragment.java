package com.rgsan.dailynotesapp.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rgsan.dailynotesapp.R;
import com.rgsan.dailynotesapp.model.Note;
import com.rgsan.dailynotesapp.presenter.NotePresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * 10120076
 * Rifqi Galih Nur Ikhsan
 * IF-2
 */

public class DailyNotesFragment extends Fragment implements NotePresenter.View, View.OnClickListener {
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private NotePresenter notePresenter;
    private List<Note> noteList;
    private TextView dateTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notePresenter = new NotePresenter(this, requireContext());
        noteList = new ArrayList<>();
        noteAdapter = new NoteAdapter(noteList, requireActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_notes, container, false);
        initializeViews(view);
        setListeners(view);
        return view;
    }

    private void initializeViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(noteAdapter);

        FloatingActionButton fab = view.findViewById(R.id.addNav_button);
        dateTextView = view.findViewById(R.id.dateTextView);
    }

    private void setListeners(View view) {
        FloatingActionButton fab = view.findViewById(R.id.addNav_button);
        fab.setOnClickListener(this);

        LinearLayout dateLayout = view.findViewById(R.id.dateLayout);
        dateLayout.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        notePresenter.loadNotes();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addNav_button:
                navigateToFragmentAddDailyNotes();
                break;
            case R.id.dateLayout:
                showDatePicker();
                break;
        }
    }

    @Override
    public void showNotes(List<Note> notes) {
        noteList.clear();
        noteList.addAll(notes);
        noteAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNoteDetail(Note note) {

    }

    @Override
    public void showNoteNotFound() {

    }

    private void navigateToFragmentAddDailyNotes() {
        AddDailyNotesFragment fragmentAddDailyNotes = new AddDailyNotesFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragmentAddDailyNotes);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                    calendar.set(selectedYear, selectedMonth, selectedDayOfMonth);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    String selectedDate = dateFormat.format(calendar.getTime());
                    dateTextView.setText(selectedDate);
                    notePresenter.loadNotesByDate(selectedDate);
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }
}