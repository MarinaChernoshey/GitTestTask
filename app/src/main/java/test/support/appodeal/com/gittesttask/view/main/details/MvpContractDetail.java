package test.support.appodeal.com.gittesttask.view.main.details;

import android.support.v4.app.Fragment;

import test.support.appodeal.com.gittesttask.base.BaseView;

public interface MvpContractDetail {
    interface View extends BaseView<Presenter> {

        void closeView();

        void clearSharedPreference(String name, int mode);
    }

    interface Presenter {
        Fragment navigationUser(String username);

        Fragment navigationRepositories(String username);

        void clickLogout();
    }
}
