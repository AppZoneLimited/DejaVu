package dejavu.appzonegroup.com.dejavuandroid.Fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import dejavu.appzonegroup.com.dejavuandroid.R;

/**
 * Created by CrowdStar on 2/19/2015.
 */
public class FragmentChanger {

    public FragmentChanger(FragmentManager fragmentManager, Fragment fragment) {
        fragmentManager.beginTransaction().replace(R.id.splash_screen_frame, fragment).commitAllowingStateLoss();
    }

    public FragmentChanger(FragmentManager fragmentManager, boolean soft, boolean hard, boolean debit) {
        fragmentManager.beginTransaction().replace(R.id.splash_screen_frame, new BankFlow().newInstance(soft, hard, debit)).commitAllowingStateLoss();
    }

    public FragmentChanger(FragmentTransaction fragmentTransaction, Fragment fragment) {
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.splash_screen_frame, fragment).commitAllowingStateLoss();
    }
}
