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
        switch (position) {
            case 0:
                return new IntroFragment("Select Each Category");
            case 1:
                return new IntroFragment("Select the option that suits you best");
            case 2:
                return new IntroFragment("Watch as your carbon emissions calculate");
            default:
                return new EmptyFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
