package test.support.appodeal.com.gittesttask.presenter;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;
import test.support.appodeal.com.gittesttask.core.App;
import test.support.appodeal.com.gittesttask.core.MvpContractMain;
import test.support.appodeal.com.gittesttask.model.Issue;
import test.support.appodeal.com.gittesttask.model.ModelIssue;
import test.support.appodeal.com.gittesttask.model.ModelUsers;
import test.support.appodeal.com.gittesttask.model.ModelRepository;
import test.support.appodeal.com.gittesttask.model.ModelUser;
import test.support.appodeal.com.gittesttask.model.Repository;
import test.support.appodeal.com.gittesttask.model.User;

public class PresenterMain implements MvpContractMain.Presenter {

    private MvpContractMain.View.ViewMain viewMain;
    private MvpContractMain.View.ViewDetailMain viewDetailMain;
    private MvpContractMain.View.ViewIssues viewIssues;
    private MvpContractMain.View.ViewUsers viewUsers;
    private MvpContractMain.View.ViewDetailUser viewDetailUser;
    private MvpContractMain.View.ViewRepositories viewRepositories;
    private MvpContractMain.Model.ModelIssues modelIssue;
    private MvpContractMain.Model.ModelUsers modelUsers;
    private MvpContractMain.Model.ModelDetailUser modelDetailUser;
    private MvpContractMain.Model.ModelRepositories modelRepositories;

    public PresenterMain(MvpContractMain.View.ViewMain viewMain) {
        this.viewMain = viewMain;
        modelIssue = new ModelIssue();
        modelUsers = new ModelUsers();
        modelDetailUser = new ModelUser();
        modelRepositories = new ModelRepository();
    }

    @Override
    public void startViewMain() {
        viewMain.showIssues();
    }

    @Override
    public void navigationIssues() {
        if (viewIssues == null)
            viewMain.showIssues();
    }

    @Override
    public void navigationUsers() {
        if (viewUsers == null)
            viewMain.showUsers();
    }

    @Override
    public void navigationDetailBaseUser() {
        if (viewDetailMain == null)
            viewMain.showBaseUser(App.getLoginUser());
    }

    @Override
    public void chooseUserFromList(String login) {
        viewMain.showSameUser(login);
    }

    @Override
    public void startViewIssues() {
        modelIssue.getIssues()
                .subscribe(new DisposableSingleObserver<List<Issue>>() {
                    @Override
                    public void onSuccess(List<Issue> issues) {
                        if (viewIssues != null)
                            viewIssues.showIssues(issues);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void startViewUser(String login) {
        modelDetailUser.getUser(login)
                .subscribe(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(User user) {
                        if (viewDetailUser != null)
                            viewDetailUser.showDetailUser(user);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void startViewUsers(long idTailUser) {
        modelUsers.getUsers(idTailUser)
                .subscribe(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(List<User> users) {
                        if (viewUsers != null)
                            viewUsers.showListUser(users);
                    }

                    @Override
                    public void onError(Throwable e) {
                        PresenterMain.this.onError(e);
                    }
                });
    }

    @Override
    public void startViewRepositories(String login) {
        modelRepositories.getRepositories(login)
                .subscribe(new DisposableSingleObserver<List<Repository>>() {
                    @Override
                    public void onSuccess(List<Repository> repositories) {
                        if (viewRepositories != null)
                            viewRepositories.showListRepository(repositories);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void searchUser(String text) {
        modelUsers.getSearchUser(text)
                .subscribe(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(List<User> users) {
                        if (viewUsers != null) {
                            viewUsers.showListSearchUser(users);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void attachViewIssue(MvpContractMain.View.ViewIssues viewIssues) {
        this.viewIssues = viewIssues;
        viewIssues.attachPresenter(this);
    }

    @Override
    public void attachViewUsers(MvpContractMain.View.ViewUsers viewUsers) {
        this.viewUsers = viewUsers;
        viewUsers.attachPresenter(this);
    }

    @Override
    public void attachViewDetailMain(MvpContractMain.View.ViewDetailMain viewDetailMain) {
        this.viewDetailMain = viewDetailMain;
        viewDetailMain.attachPresenter(this);
    }

    @Override
    public void attachViewDetailUser(MvpContractMain.View.ViewDetailUser viewDetailUser) {
        this.viewDetailUser = viewDetailUser;
        viewDetailUser.attachPresenter(this);
    }

    @Override
    public void attachViewRepositories(MvpContractMain.View.ViewRepositories viewRepositories) {
        this.viewRepositories = viewRepositories;
        viewRepositories.attachPresenter(this);
    }

    @Override
    public void detachViewIssues() {
        viewIssues = null;
    }

    @Override
    public void detachViewUsers() {
        viewUsers = null;
    }

    @Override
    public void detachViewDetailMain() {
        viewDetailMain = null;
    }

    @Override
    public void detachViewDetailUser() {
        viewDetailUser = null;
    }

    @Override
    public void detachViewRepositories() {
        viewRepositories = null;
    }

    private void onError(Throwable e) {

    }
}