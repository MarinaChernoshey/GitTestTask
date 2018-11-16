package test.support.appodeal.com.gittesttask.view.main.users;

import com.jakewharton.rxbinding2.InitialValueObservable;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import test.support.appodeal.com.gittesttask.util.ThrowableUtil;
import test.support.appodeal.com.gittesttask.base.Presenter;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.util.CallbackGetSelectUser;
import test.support.appodeal.com.gittesttask.view.main.MvpContractMain;

public class UsersPresenter extends Presenter implements MvpContractUsers.Presenter {

    private MvpContractUsers.View view;
    private MvpContractMain.Model model;
    private CallbackGetSelectUser callbackGetSelectUser;

    private String textSearchUser;
    private long pageSearchUser = 1;
    private long idTailUser = 0;

    private Disposable disposableGetSearchUsers;

    public UsersPresenter(MvpContractUsers.View view,
                          MvpContractMain.Model model,
                          CallbackGetSelectUser callbackGetSelectUser) {
        this.view = view;
        this.model = model;
        this.callbackGetSelectUser = callbackGetSelectUser;
        view.attachPresenter(this);
    }

    @Override
    public void requestGetUsers() {
        disposeDisposable(disposableGetSearchUsers);
        disposable = model.requestGetUsers(idTailUser)
                .subscribe(
                        users -> {
                            view.showUsers(users);
                            idTailUser = users.get(users.size() - 1).getId();
                        },
                        ThrowableUtil.getConsumerForShowThrowableScreen(view)
                );
    }

    @Override
    public void clickUser(String username) {
        callbackGetSelectUser.onClickUser(username);
    }

    @Override
    public void setListenerSearchViewEditChanges(InitialValueObservable<CharSequence> observable) {
        Disposable disposableSearch = observable
                .debounce(Const.COUNT_MILLISECONDS_WAIT_EDIT_TEXT_RESPONSE, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(charSequence -> {
                            if (!charSequence.toString().isEmpty()) {
                                requestGetSearchUsers(charSequence.toString());
                            } else {
                                view.showAllUsersAfterCloseSearch();
                            }
                        },
                        ThrowableUtil.getConsumerForShowThrowableScreen(view));
    }

    @Override
    public void requestGetSearchUsers(String text) {
        disposeDisposable(disposable);
        if (!text.isEmpty()) {
            textSearchUser = text.toLowerCase();
            disposableGetSearchUsers = model.requestSearchUsers(textSearchUser, pageSearchUser)
                    .subscribe(
                            searchUser -> view.showSearchUsers(searchUser.getUsers()),
                            ThrowableUtil.getConsumerForShowThrowableScreen(view)
                    );
        }
    }

    @Override
    public void requestGetNextPageSearchUser() {
        pageSearchUser += 1;
        disposableGetSearchUsers = model.requestSearchUsers(textSearchUser, pageSearchUser)
                .subscribe(
                        searchUser -> view.showSearchUsersScroll(searchUser.getUsers()),
                        e -> {
                            view.resetSettingsAdapterSearch();
                            ThrowableUtil.showThrowableInScreen(e, view);
                        }
                );
    }

    @Override
    public void destroyView() {
        super.destroyView();
        view = null;
    }
}

