package test.support.appodeal.com.gittesttask.view.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;

import test.support.appodeal.com.gittesttask.core.MvpContractMain;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.view.BaseViewMain;

public abstract class BaseMainFragment extends Fragment
        implements BaseViewMain<MvpContractMain.Presenter> {

    MvpContractMain.Presenter presenter;

    @Override
    public void attachPresenter(MvpContractMain.Presenter presenter) {
        this.presenter = presenter;
    }

    public abstract void detachPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        detachPresenter();
    }

    @Override
    public void showErrorInternet() {
        Log.d(Const.TAG, "showErrorInternet: ");
    }
}
