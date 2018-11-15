package test.support.appodeal.com.gittesttask.core;

import android.content.SharedPreferences;

import io.reactivex.Observable;
import io.reactivex.Single;
import test.support.appodeal.com.gittesttask.model.User;

public interface MvpContractAuthentication {

    interface ViewMainLogin {

    }

    interface ViewLogin {
        void showAuthenticationError();

        void showErrorAuthenticationLogin();

        void showErrorAuthenticationPassword();

        void showInternetError();

        SharedPreferences getSharedPreferences();

        void StartMainView();

        void attachPresenter(Presenter presenter);
    }

    interface ModelLogin {
        Single<User> getAuthenticationUser(String authenticationHeader);
    }

    interface Presenter {
        void authenticationUser(String login, String password);

        void attachLoginFragment(ViewLogin viewLogin);

        void detachLoginFragment();
    }

}
