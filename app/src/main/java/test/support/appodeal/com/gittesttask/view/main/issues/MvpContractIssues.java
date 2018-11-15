package test.support.appodeal.com.gittesttask.issues;

import java.util.List;

import test.support.appodeal.com.gittesttask.model.Issue;
import test.support.appodeal.com.gittesttask.view.BaseViewMain;

public interface MvpContractIssues {

    interface View extends BaseViewMain<Presenter> {
        void showIssues(List<Issue> issues);
    }

    interface Presenter {
        void startView();
    }
}