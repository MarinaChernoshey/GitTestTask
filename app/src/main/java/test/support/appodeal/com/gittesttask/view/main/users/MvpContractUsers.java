package test.support.appodeal.com.gittesttask.users;

import java.util.List;

import test.support.appodeal.com.gittesttask.model.User;
import test.support.appodeal.com.gittesttask.view.BaseViewMain;

public interface MvpContractUsers {
    interface View extends BaseViewMain<Presenter> {
        void showUsers(List<User> users);
    }

    interface Presenter {
        void requestGetUsers();
    }
}