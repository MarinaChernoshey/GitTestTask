package test.support.appodeal.com.gittesttask.view.main.details;

import android.support.v4.app.Fragment;

import test.support.appodeal.com.gittesttask.core.App;
import test.support.appodeal.com.gittesttask.util.CallbackClickEditUser;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.util.CallbackClickItemRepositories;
import test.support.appodeal.com.gittesttask.view.main.MvpContractMain;
import test.support.appodeal.com.gittesttask.view.main.details.repository.MvpContractRepositories;
import test.support.appodeal.com.gittesttask.view.main.details.repository.RepositoriesPresenter;
import test.support.appodeal.com.gittesttask.view.main.details.repository.RepositoriesFragment;
import test.support.appodeal.com.gittesttask.view.main.details.user.MvpContractUser;
import test.support.appodeal.com.gittesttask.view.main.details.user.UserPresenter;
import test.support.appodeal.com.gittesttask.view.main.details.user.UserFragment;

import static android.content.Context.MODE_PRIVATE;

public class DetailsPresenter implements MvpContractDetail.Presenter {

    private MvpContractDetail.View view;
    private MvpContractMain.Model model;
    private CallbackClickItemRepositories callbackClickItemRepositories;


    private CallbackClickEditUser clickEditUser;

    public DetailsPresenter(MvpContractDetail.View view,
                            MvpContractMain.Model model,
                            CallbackClickItemRepositories callbackClickItemRepositories,
                            CallbackClickEditUser clickEditUser) {
        this.view = view;
        this.model = model;
        this.callbackClickItemRepositories = callbackClickItemRepositories;
        this.clickEditUser = clickEditUser;
        view.attachPresenter(this);
    }

    @Override
    public Fragment navigationUser(String username) {
        MvpContractUser.View userFragment = UserFragment.newInstance(username);
        MvpContractUser.Presenter presenter = new UserPresenter(
                userFragment, model,
                username1 -> clickEditUser.onClick(username1));
        return (Fragment) userFragment;
    }

    @Override
    public Fragment navigationRepositories(String username) {
        MvpContractRepositories.View repositoriesFragment =
                RepositoriesFragment.newInstance(username);
        MvpContractRepositories.Presenter presenter = new RepositoriesPresenter(
                repositoriesFragment, model,
                repositoryUrl -> callbackClickItemRepositories.onClick(repositoryUrl));
        return (Fragment) repositoriesFragment;
    }

    @Override
    public void clickLogout() {
        view.clearSharedPreference(
                Const.FILE_SHARED_PREFERENCES_SAVE_AUTHENTICATION, MODE_PRIVATE);
        App.getInstance().setToken(null);
        App.getInstance().setLoginUser(null);
        view.closeView();
    }
}
