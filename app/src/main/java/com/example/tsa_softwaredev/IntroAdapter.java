package com.example.tsa_softwaredev;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tsa_softwaredev.IntroFragment;

public class IntroAdapter extends FragmentStateAdapter {

    public IntroAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a different fragment depending on the position
        switch (position) {
            case 0:
                return new IntroFragment("Screen 1: App Introduction");
            case 1:
                return new IntroFragment("Screen 2: How to Use");
            case 2:
                return new IntroFragment("Screen 3: Final Tips");
            default:
                return new EmptyFragment();
        }
    }

    @Override
    public int getItemCount() {
        // Total number of intro screens
        return 4;
    }
}
