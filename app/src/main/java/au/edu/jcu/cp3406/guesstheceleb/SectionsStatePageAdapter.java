package au.edu.jcu.cp3406.guesstheceleb;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsStatePageAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragmentsList = new ArrayList<>();
    private final List<String> fragmentsTitleList = new ArrayList<>();


    public SectionsStatePageAdapter(FragmentManager fm) {
        super(fm);
    }

   public void addFragment(Fragment fragment, String title){
        fragmentsList.add(fragment);
        fragmentsTitleList.add(title);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentsList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }
}
