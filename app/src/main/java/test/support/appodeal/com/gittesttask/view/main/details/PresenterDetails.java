package test.support.appodeal.com.gittesttask.details;

import test.support.appodeal.com.gittesttask.details.repository.MvpContractRepositories;
import test.support.appodeal.com.gittesttask.details.repository.PresenterRepositories;
import test.support.appodeal.com.gittesttask.details.repository.RepositoriesFragment;
import test.support.appodeal.com.gittesttask.details.user.MvpContractUser;
import test.support.appodeal.com.gittesttask.details.user.PresenterUser;
import test.support.appodeal.com.gittesttask.details.user.UserFragment;

public class PresenterDetails implements MvpContractDetail.Presenter {

    private MvpContractDetail.View view;

    public PresenterDetails(MvpContractDetail.View view) {
        this.view = view;
    }

    @Override
    public MvpContractUser.View navigationUser(String username) {
        MvpContractUser.View userFragment = UserFragment.newInstance(username);
        MvpContractUser.Presenter presenter = new PresenterUser(userFragment);
        userFragment.attachPresenter(presenter);
        return userFragment;
    }

    @Override
    public MvpContractRepositories.View navigationRepositories(String username) {
        MvpContractRepositories.View repositoriesFragment =
                RepositoriesFragment.newInstance(username);
        MvpContractRepositories.Presenter presenter = new PresenterRepositories(repositoriesFragment);
        repositoriesFragment.attachPresenter(presenter);
        return repositoriesFragment;
    }
}
