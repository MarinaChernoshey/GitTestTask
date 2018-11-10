package test.support.appodeal.com.gittesttask.core;

import java.util.List;

import io.reactivex.Single;
import test.support.appodeal.com.gittesttask.model.Issue;
import test.support.appodeal.com.gittesttask.model.Repository;
import test.support.appodeal.com.gittesttask.model.User;
import test.support.appodeal.com.gittesttask.presenter.BasePresenterMain;
import test.support.appodeal.com.gittesttask.view.BaseViewMain;

public interface MvpContractMain {

    interface View {

        interface ViewMain {
            void showIssues();

            void showUsers();

            void showBaseUser(String login);

            void showSameUser(String login);
        }

        interface ViewIssues extends BaseViewMain<Presenter> {
            void showIssues(List<Issue> issues);
        }

        interface ViewUsers extends BaseViewMain<Presenter> {
            void showListUser(List<User> users);

            void showListSearchUser(List<User> users);
        }

        interface ViewDetailUser extends BaseViewMain<Presenter> {
            void showDetailUser(User user);
        }

        interface ViewRepositories extends BaseViewMain<Presenter> {
            void showListRepository(List<Repository> repositories);
        }

        interface ViewDetailMain extends BaseViewMain<Presenter> {
        }

    }

    interface Model {
        interface ModelIssues {
            Single<List<Issue>> getIssues();
        }

        interface ModelUsers {
            Single<List<User>> getUsers(long idTailUser);

            Single<List<User>> getSearchUser(String text);
        }

        interface ModelDetailUser {
            Single<User> getUser(String login);
        }

        interface ModelRepositories {
            Single<List<Repository>> getRepositories(String login);
        }
    }

    interface Presenter extends BasePresenterMain<View.ViewIssues, View.ViewUsers,
            View.ViewDetailMain, View.ViewDetailUser, View.ViewRepositories> {

        void startViewMain();

        void startViewIssues();

        void startViewUser(String login);

        void startViewUsers(long idTailUser);

        void startViewRepositories(String login);

        void chooseUserFromList(String login);

        void searchUser(String text);

        void navigationIssues();

        void navigationUsers();

        void navigationDetailBaseUser();


    }
}
