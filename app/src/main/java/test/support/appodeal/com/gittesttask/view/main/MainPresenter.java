package test.support.appodeal.com.gittesttask.view.main;

import android.support.v4.app.Fragment;

import test.support.appodeal.com.gittesttask.core.App;
import test.support.appodeal.com.gittesttask.data.ModelMain;
import test.support.appodeal.com.gittesttask.view.main.details.DetailFragment;
import test.support.appodeal.com.gittesttask.view.main.details.MvpContractDetail;
import test.support.appodeal.com.gittesttask.view.main.details.DetailsPresenter;
import test.support.appodeal.com.gittesttask.view.main.details.user.editable.UserEditableFragment;
import test.support.appodeal.com.gittesttask.view.main.details.user.editable.MvpContractUserEditable;
import test.support.appodeal.com.gittesttask.view.main.details.user.editable.UserEditablePresenter;
import test.support.appodeal.com.gittesttask.view.main.issues.IssuesFragment;
import test.support.appodeal.com.gittesttask.view.main.issues.MvpContractIssues;
import test.support.appodeal.com.gittesttask.view.main.issues.IssuesPresenter;
import test.support.appodeal.com.gittesttask.view.main.users.ListUserFragment;
import test.support.appodeal.com.gittesttask.view.main.users.MvpContractUsers;
import test.support.appodeal.com.gittesttask.view.main.users.UsersPresenter;
import test.support.appodeal.com.gittesttask.view.web_view.WebRepositoriesFragment;

public class MainPresenter implements MvpContractMain.Presenter {

    private MvpContractMain.View view;
    private MvpContractMain.Model model;

    MainPresenter(MvpContractMain.View view) {
        this.view = view;
        model = new ModelMain();
    }

    @Override
    public void startView() {
        navigationIssues();
    }

    @Override
    public void navigationIssues() {
        MvpContractIssues.View viewIssues = new IssuesFragment();
        MvpContractIssues.Presenter presenter = new IssuesPresenter(viewIssues, model);
        view.showFragmentWithoutAddInBackStack((Fragment) viewIssues);
    }

    @Override
    public void navigationUsers() {
        MvpContractUsers.View viewUsers = new ListUserFragment();
        MvpContractUsers.Presenter presenter = new UsersPresenter(
                viewUsers,
                model,
                this::navigationDetailsFromUsersFragment);
        view.showFragmentWithoutAddInBackStack((Fragment) viewUsers);
    }

    @Override
    public void navigationDetailsFromMainScreen(String username) {
        view.showFragmentWithoutAddInBackStack((Fragment) navigationDetails(username));
    }

    @Override
    public void navigationDetailsFromUsersFragment(String username) {
        view.showFragmentWithAddInBackStack((Fragment) navigationDetails(username));
    }

    private Fragment createEditableFragment(String usernameEdit) {
        UserEditableFragment userEditableFragment =
                UserEditableFragment.newInstance(usernameEdit);
        MvpContractUserEditable.Presenter presenter = new UserEditablePresenter(
                userEditableFragment, model,
                () -> navigationDetailsFromMainScreen(App.getInstance().getLoginUser()));
        return userEditableFragment;
    }


    private MvpContractDetail.View navigationDetails(String username) {
        MvpContractDetail.View detailFragment = DetailFragment.newInstance(username);
        MvpContractDetail.Presenter presenter = new DetailsPresenter(
                detailFragment, model,
                repositoryUrl -> view.showFragmentWithAddInBackStack(
                        WebRepositoriesFragment.newInstance(repositoryUrl)),
                usernameEdit -> view.showFragmentWithAddInBackStack(
                        createEditableFragment(usernameEdit)));
        detailFragment.attachPresenter(presenter);
        return detailFragment;
    }
}

