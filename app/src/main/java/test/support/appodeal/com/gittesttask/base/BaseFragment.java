package test.support.appodeal.com.gittesttask.base;

import android.support.v4.app.Fragment;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment implements BaseViewMain {

    @Override
    public void showErrorToast(String text) {
        if (getContext() != null)
            Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }
}
