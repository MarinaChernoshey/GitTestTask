package test.support.appodeal.com.gittesttask.view.main.issues;

import java.util.List;

import test.support.appodeal.com.gittesttask.base.BasePresenterMain;
import test.support.appodeal.com.gittesttask.base.BaseViewMain;
import test.support.appodeal.com.gittesttask.network.pojo.Issue;
import test.support.appodeal.com.gittesttask.base.BaseView;

public interface MvpContractIssues {

    interface View extends BaseViewMain, BaseView<Presenter> {
        void showIssues(List<Issue> issues);

        void showMessageUserNotHaveIssues();
    }

    interface Presenter extends BasePresenterMain {
        void startView();
    }
}