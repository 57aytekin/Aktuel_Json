package com.aytekincomez.aktueluygulamasi.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.aytekincomez.aktueluygulamasi.Fragment.ViewTabFragmnet1;
import com.aytekincomez.aktueluygulamasi.Fragment.ViewTabFragmnet2;

public class PageAdapter extends FragmentStatePagerAdapter {

    int tabSayisi;

    public PageAdapter(FragmentManager fm, int tabSayisi) {
        super(fm);
        this.tabSayisi = tabSayisi;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ViewTabFragmnet1 tab1 = new ViewTabFragmnet1();
                return tab1;
            case 1:
                ViewTabFragmnet2 tab2 = new ViewTabFragmnet2();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabSayisi;
    }
}
