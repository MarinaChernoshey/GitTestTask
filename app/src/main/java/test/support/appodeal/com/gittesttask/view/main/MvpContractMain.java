package test.support.appodeal.com.gittesttask.view.main;

import android.support.v4.app.Fragment;

import java.util.List;

import io.reactivex.Single;
import test.support.appodeal.com.gittesttask.network.pojo.Issue;
import test.support.appodeal.com.gittesttask.network.pojo.Repository;
import test.support.appodeal.com.gittesttask.network.pojo.SearchUser;
import test.support.appodeal.com.gittesttask.network.pojo.User;

public interface MvpContractMain {

    interface View {

        void showFragmentWithAddInBackStack(Fragment fragment);

        void showFragmentWithoutAddInBackStack(Fragment fragment);
    }

    interface Model {
        Single<List<Issue>> requestGetIssues(String authKey);

        Single<List<User>> requestGetUsers(long idTailUser);

        Single<User> requestGetUser(String username);

        Single<List<Repository>> requestGetRepositories(String username);

        Single<User> editUser(User user);

        Single<SearchUser> requestSearchUsers(String text, long page);
    }

    interface Presenter {
        void startView();

        void navigationIssues();

        void navigationUsers();

        void navigationDetailsFromMainScreen(String username);

        void navigationDetailsFromUsersFragment(String username);
    }
}
