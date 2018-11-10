package test.support.appodeal.com.gittesttask.view;

import test.support.appodeal.com.gittesttask.core.MvpContractMain;

public interface BaseViewMain<Presenter extends MvpContractMain.Presenter> {
    void attachPresenter(Presenter presenter); 
    
    void showErrorInternet();
}
