package com.rgsan.dailynotesapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.rgsan.dailynotesapp.R;

/**
 * 10120076
 * Rifqi Galih Nur Ikhsan
 * IF-2
 */

public class ProfileFragment extends Fragment {

    private ImageView ivProfileImage;
    private TextView tvNim, tvNama, tvKelas, tvEmail, tvUsername, tvBio;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        initializeViews(rootView);
        setProfileData();

        return rootView;
    }

    private void initializeViews(View rootView) {
        ivProfileImage = rootView.findViewById(R.id.profileImageView);
        tvNim = rootView.findViewById(R.id.nimTextView);
        tvNama = rootView.findViewById(R.id.namaTextView);
        tvKelas = rootView.findViewById(R.id.kelasTextView);
        tvEmail = rootView.findViewById(R.id.emailTextView);
        tvUsername = rootView.findViewById(R.id.usernameTextView);
        tvBio = rootView.findViewById(R.id.bioTextView);
    }

    private void setProfileData() {
        ivProfileImage.setImageResource(R.drawable.rgsannn);
        tvNim.setText(R.string.nim);
        tvNama.setText(R.string.name);
        tvKelas.setText(R.string.kelas);
        tvEmail.setText(R.string.email);
        tvUsername.setText(R.string.username);
        tvBio.setText(R.string.bio);
    }
}