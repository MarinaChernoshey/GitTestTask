package test.support.appodeal.com.gittesttask.view.login;

import io.reactivex.Single;
import test.support.appodeal.com.gittesttask.base.BaseView;
import test.support.appodeal.com.gittesttask.base.BaseViewMain;
import test.support.appodeal.com.gittesttask.network.pojo.User;

public interface MvpContractAuthentication {

    interface View extends BaseViewMain, BaseView<Presenter> {

        void StartMainView();

        void attachPresenter(Presenter presenter);

        void saveAuthDataInSharedPreferences(String nameFile, String key,
                                             int mode, String authHeader);
    }

    interface ModelLogin {
        Single<User> getAuthenticationUser(String authenticationHeader);
    }

    interface Presenter {
        void authenticationUser(String login, String password);
    }
}
