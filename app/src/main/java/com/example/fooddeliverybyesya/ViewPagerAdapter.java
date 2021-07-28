package com.example.fooddeliverybyesya;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fooddeliverybyesya.Fragments.CategoriesCardFragment;
import com.example.fooddeliverybyesya.Fragments.HomeFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final int countOfCategories;
    public ViewPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, int countOfCategories) {
        super(fragmentManager, lifecycle);
        this.countOfCategories = countOfCategories;
    }

    @Override
    public Fragment createFragment(int position) {
        return CategoriesCardFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return countOfCategories;
    }
}
