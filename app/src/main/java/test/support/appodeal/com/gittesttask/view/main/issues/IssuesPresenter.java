package test.support.appodeal.com.gittesttask.view.main.issues;

import test.support.appodeal.com.gittesttask.util.ThrowableUtil;
import test.support.appodeal.com.gittesttask.base.Presenter;
import test.support.appodeal.com.gittesttask.core.App;
import test.support.appodeal.com.gittesttask.view.main.MvpContractMain;

public class IssuesPresenter extends Presenter implements MvpContractIssues.Presenter {

    private MvpContractIssues.View view;
    private MvpContractMain.Model model;

    public IssuesPresenter(MvpContractIssues.View view, MvpContractMain.Model model) {
        this.view = view;
        this.model = model;
        view.attachPresenter(this);
    }

    @Override
    public void startView() {
        disposable = model.requestGetIssues(App.getInstance().getAuthKey())
                .subscribe(
                        issues -> {
                            if (issues.size() == 0) {
                                view.showMessageUserNotHaveIssues();
                                view.showErrorToast("Nothing");
                            } else {
                                view.showIssues(issues);
                            }
                        },
                        ThrowableUtil.getConsumerForShowThrowableScreen(view)

                );
    }

    @Override
    public void destroyView() {
        super.destroyView();

        view = null;
    }
}
