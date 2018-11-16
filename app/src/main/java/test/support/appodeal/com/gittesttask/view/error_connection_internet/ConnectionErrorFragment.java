package test.support.appodeal.com.gittesttask.view.error_connection_internet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.support.appodeal.com.gittesttask.R;

public class ConnectionErrorFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_connection_error, container, false);
    }
}
