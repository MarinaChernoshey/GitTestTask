package test.support.appodeal.com.gittesttask.details.user;

public class PresenterUser implements MvpContractUser.Presenter {

    private MvpContractUser.View view;

    public PresenterUser(MvpContractUser.View view) {
        this.view = view;
    }

    @Override
    public void startView(String username) {

    }
}
