package test.support.appodeal.com.gittesttask.view.main.details.user.editable;

import io.reactivex.disposables.Disposable;
import test.support.appodeal.com.gittesttask.base.Presenter;
import test.support.appodeal.com.gittesttask.network.pojo.User;
import test.support.appodeal.com.gittesttask.util.CallbackClickSaveEditableUser;
import test.support.appodeal.com.gittesttask.util.ThrowableUtil;
import test.support.appodeal.com.gittesttask.view.main.MvpContractMain;

public class UserEditablePresenter extends Presenter
        implements MvpContractUserEditable.Presenter {

    private MvpContractUserEditable.View view;
    private MvpContractMain.Model model;
    private CallbackClickSaveEditableUser callbackClickSaveEditableUser;

    private Disposable disposableSaveChanges;

    public UserEditablePresenter(MvpContractUserEditable.View view, MvpContractMain.Model model,
                                 CallbackClickSaveEditableUser callbackClickSaveEditableUser) {
        this.view = view;
        this.model = model;
        this.callbackClickSaveEditableUser = callbackClickSaveEditableUser;
        view.attachPresenter(this);
    }

    @Override
    public void startView(String username) {
        disposable = model.requestGetUser(username)
                .subscribe(
                        user -> view.showEditableData(user),
                        ThrowableUtil.getConsumerForShowThrowableScreen(view));
    }

    @Override
    public void clickSaveChanges(String newName, String newEmail, String newOrganization,
                                 String newBlog, String newLocation, boolean newHireable,
                                 String newBio) {
        User userEdit = new User(
                newName.isEmpty() ? null : newName,
                newEmail.isEmpty() ? null : newEmail,
                newBlog.isEmpty() ? null : newBlog,
                newOrganization.isEmpty() ? null : newOrganization,
                newLocation.isEmpty() ? null : newLocation,
                newHireable,
                newBio.isEmpty() ? null : newBio);

        disposableSaveChanges = model.editUser(userEdit)
                .subscribe(
                        user -> callbackClickSaveEditableUser.onClick(),
                        e -> {
                            ThrowableUtil.showThrowableInScreen(e, view);
                            view.setVisibleButton(true);
                            view.showErrorToast(e.getMessage());
                        }
                );
    }

    @Override
    public void destroyView() {
        super.destroyView();
        disposeDisposable(disposableSaveChanges);
        view = null;
    }
}
