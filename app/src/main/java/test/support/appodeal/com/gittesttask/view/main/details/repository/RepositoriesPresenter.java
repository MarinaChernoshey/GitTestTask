package test.support.appodeal.com.gittesttask.view.main.details.repository;

import test.support.appodeal.com.gittesttask.base.Presenter;
import test.support.appodeal.com.gittesttask.util.CallbackClickItemRepositories;
import test.support.appodeal.com.gittesttask.util.ThrowableUtil;
import test.support.appodeal.com.gittesttask.view.main.MvpContractMain;

public class RepositoriesPresenter extends Presenter
        implements MvpContractRepositories.Presenter {

    private MvpContractRepositories.View view;
    private MvpContractMain.Model model;
    private CallbackClickItemRepositories callbackClickItemRepositories;

    public RepositoriesPresenter(MvpContractRepositories.View view,
                                 MvpContractMain.Model model,
                                 CallbackClickItemRepositories callbackClickItemRepositories) {
        this.model = model;
        this.view = view;
        this.callbackClickItemRepositories = callbackClickItemRepositories;
        view.attachPresenter(this);
    }

    @Override
    public void startView(String username) {
        disposable = model.requestGetRepositories(username)
                .subscribe(
                        repositories -> {
                            if (repositories.size() > 0)
                                view.showRepositories(repositories);
                            else
                                view.showMessageNoRepo();
                        },
                        ThrowableUtil.getConsumerForShowThrowableScreen(view)
                );
    }

    @Override
    public void chooseRepositoryFromList(String url) {
        callbackClickItemRepositories.onClick(url);
    }

    @Override
    public void destroyView() {
        super.destroyView();
        view = null;
    }
}
