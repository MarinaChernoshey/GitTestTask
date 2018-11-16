package test.support.appodeal.com.gittesttask.view.main.details.user;

import test.support.appodeal.com.gittesttask.base.BaseView;
import test.support.appodeal.com.gittesttask.base.BasePresenterMain;
import test.support.appodeal.com.gittesttask.base.BaseViewMain;
import test.support.appodeal.com.gittesttask.network.pojo.User;

public interface MvpContractUser {
    interface View extends BaseViewMain, BaseView<Presenter> {
        void showUser(User user, boolean isCanEdit, boolean isShowEmail, boolean isShowCompany);
    }

    interface Presenter extends BasePresenterMain {
        void startView(String username);

        void clickEditUser(String username);
    }
}
