package test.support.appodeal.com.gittesttask.view.main.users;

import com.jakewharton.rxbinding2.InitialValueObservable;

import java.util.List;

import test.support.appodeal.com.gittesttask.base.BaseView;
import test.support.appodeal.com.gittesttask.base.BasePresenterMain;
import test.support.appodeal.com.gittesttask.base.BaseViewMain;
import test.support.appodeal.com.gittesttask.network.pojo.User;

public interface MvpContractUsers {
    interface View extends BaseViewMain, BaseView<Presenter> {
        void showUsers(List<User> users);

        void showSearchUsers(List<User> users);

        void showSearchUsersScroll(List<User> users);

        void showAllUsersAfterCloseSearch();

        void resetSettingsAdapterSearch();
    }

    interface Presenter extends BasePresenterMain {
        void requestGetUsers();

        void clickUser(String username);

        void setListenerSearchViewEditChanges(InitialValueObservable<CharSequence> text);

        void requestGetSearchUsers(String text);

        void requestGetNextPageSearchUser();
    }
}