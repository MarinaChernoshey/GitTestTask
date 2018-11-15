package test.support.appodeal.com.gittesttask.view.main.details.user.editable;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.support.appodeal.com.gittesttask.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditableUserFragment extends Fragment {


    public EditableUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editable_user, container, false);
    }

}
