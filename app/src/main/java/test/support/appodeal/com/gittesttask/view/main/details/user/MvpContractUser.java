package test.support.appodeal.com.gittesttask.details.user;

import test.support.appodeal.com.gittesttask.model.User;
import test.support.appodeal.com.gittesttask.view.BaseViewMain;

public class MvpContractUser {
    public interface View extends BaseViewMain<Presenter> {
        void showUser(User user);
    }

    public interface Presenter {
        void startView(String username);
    }
}
