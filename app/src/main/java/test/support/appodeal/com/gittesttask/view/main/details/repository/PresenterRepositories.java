package test.support.appodeal.com.gittesttask.details.repository;

public class PresenterRepositories implements MvpContractRepositories.Presenter {

    private MvpContractRepositories.View view;

    public PresenterRepositories(MvpContractRepositories.View view) {
        this.view = view;
    }

    @Override
    public void startView(String username) {

    }

    @Override
    public void chooseRepositoryFromList(String username) {

    }
}
