package test.support.appodeal.com.gittesttask.view.splash;

public interface MvpContractSplash {

    interface ViewSplash {
        void startLoginView();

        void startMainView();
    }

    interface Presenter {
        void startSplash(String authenticationHeader);
    }
}
