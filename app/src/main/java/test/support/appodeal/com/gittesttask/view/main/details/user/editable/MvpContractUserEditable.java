package test.support.appodeal.com.gittesttask.view.main.details.user.editable;

import test.support.appodeal.com.gittesttask.base.BasePresenterMain;
import test.support.appodeal.com.gittesttask.base.BaseView;
import test.support.appodeal.com.gittesttask.base.BaseViewMain;
import test.support.appodeal.com.gittesttask.network.pojo.User;

public interface MvpContractUserEditable {
    interface View extends BaseViewMain, BaseView<Presenter> {
        void showEditableData(User user);

        void setVisibleButton(boolean isVisible);

        void showErrorSave(int errorDescriptionResource);
    }

    interface Presenter extends BasePresenterMain {
        void startView(String username);

        void clickSaveChanges(String newName, String email, String newOrganization, String newBlog,
                              String newLoc, boolean newHireable, String newBio);
    }
}
