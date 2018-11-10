package test.support.appodeal.com.gittesttask.presenter;

import test.support.appodeal.com.gittesttask.core.MvpContractMain;

public interface BasePresenterMain<
        ViewIssues extends MvpContractMain.View.ViewIssues,
        ViewUsers extends MvpContractMain.View.ViewUsers,
        ViewDetailMain extends MvpContractMain.View.ViewDetailMain,
        ViewDetailUser extends MvpContractMain.View.ViewDetailUser,
        ViewRepositories extends MvpContractMain.View.ViewRepositories> {

    void attachViewIssue(ViewIssues viewIssues);

    void attachViewUsers(ViewUsers viewUsers);

    void attachViewDetailMain(ViewDetailMain viewDetailMain);

    void attachViewDetailUser(ViewDetailUser viewDetailUser);

    void attachViewRepositories(ViewRepositories viewRepositories);

    void detachViewIssues();

    void detachViewUsers();

    void detachViewDetailMain();

    void detachViewDetailUser();

    void detachViewRepositories();
}
