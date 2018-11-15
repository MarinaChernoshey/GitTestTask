package test.support.appodeal.com.gittesttask.issues;

import test.support.appodeal.com.gittesttask.core.MvpContractMain;

public class PresenterIssues implements MvpContractIssues.Presenter {

    private MvpContractMain.Model model;
    private MvpContractIssues.View view;

    public PresenterIssues(MvpContractIssues.View view) {
        this.view = view;
    }

    @Override
    public void startView() {
    }
}
