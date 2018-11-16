package test.support.appodeal.com.gittesttask.view.main.details.user;

import test.support.appodeal.com.gittesttask.base.Presenter;
import test.support.appodeal.com.gittesttask.core.App;
import test.support.appodeal.com.gittesttask.util.CallbackClickEditUser;
import test.support.appodeal.com.gittesttask.util.ThrowableUtil;
import test.support.appodeal.com.gittesttask.view.main.MvpContractMain;

public class UserPresenter extends Presenter implements MvpContractUser.Presenter {

    private MvpContractUser.View view;
    private MvpContractMain.Model model;
    private CallbackClickEditUser callbackClickEditUser;

    public UserPresenter(MvpContractUser.View view, MvpContractMain.Model model,
                         CallbackClickEditUser callbackClickEditUser ) {
        this.view = view;
        this.model = model;
        this.callbackClickEditUser = callbackClickEditUser;
        view.attachPresenter(this);
    }

    @Override
    public void startView(String username) {
        disposable = model.requestGetUser(username)
                .subscribe(
                        user -> view.showUser(user,
                                isUserMain(username),
                                user.getEmail() != null,
                                user.getCompany() != null),
                        ThrowableUtil.getConsumerForShowThrowableScreen(view)
                );

    }

    @Override
    public void clickEditUser(String username) {
        callbackClickEditUser.onClick(username);
    }

    private boolean isUserMain(String username) {
        return username.equals(App.getInstance().getLoginUser());
    }

    @Override
    public void destroyView() {
        super.destroyView();

        view = null;
    }
}
