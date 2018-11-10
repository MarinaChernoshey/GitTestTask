package test.support.appodeal.com.gittesttask.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import test.support.appodeal.com.gittesttask.util.Const;

public abstract class BaseDetailFragment extends BaseMainFragment {

    String login;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(Const.KEY_PARCELABLE_USER)) {
            login = getArguments().getString(Const.KEY_PARCELABLE_USER);
        }
    }
}
