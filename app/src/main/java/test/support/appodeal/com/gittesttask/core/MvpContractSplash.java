package test.support.appodeal.com.gittesttask.core;

import android.content.SharedPreferences;

import io.reactivex.Single;
import test.support.appodeal.com.gittesttask.model.User;

public interface MvpContractSplash {

    interface ViewSplash {
        void startLoginView();

        void startMainView();

        SharedPreferences getSharedPreferences();

    }

    interface Presenter {
        void startSplash(String authenticationHeader);
    }

}
