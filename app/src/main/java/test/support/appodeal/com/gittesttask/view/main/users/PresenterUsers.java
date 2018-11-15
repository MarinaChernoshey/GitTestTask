package test.support.appodeal.com.gittesttask.users;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;
import test.support.appodeal.com.gittesttask.model.Model;
import test.support.appodeal.com.gittesttask.model.ModelMain;
import test.support.appodeal.com.gittesttask.model.User;

public class PresenterUsers implements MvpContractUsers.Presenter {
    private MvpContractUsers.View view;
    private Model model;

    public PresenterUsers(MvpContractUsers.View view, ItemClickUser itemClickUser) {
        this.view = view;
        model = new ModelMain();
    }

    @Override
    public void requestGetUsers() {

        model.requestGetUsers()
                .subscribe(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(List<User> users) {
                        view.showUsers(users);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
