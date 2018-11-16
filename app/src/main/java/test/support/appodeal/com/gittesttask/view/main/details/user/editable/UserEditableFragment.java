package test.support.appodeal.com.gittesttask.view.main.details.user.editable;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.base.BaseFragment;
import test.support.appodeal.com.gittesttask.network.pojo.User;
import test.support.appodeal.com.gittesttask.util.Const;

public class UserEditableFragment extends BaseFragment implements MvpContractUserEditable.View {

    @BindView(R.id.et_editable_name)
    EditText editTextName;
    @BindView(R.id.et_editable_email)
    EditText editTextEmail;
    @BindView(R.id.et_editable_blog)
    EditText editTextBlog;
    @BindView(R.id.et_editable_organization)
    EditText editTextOrganization;
    @BindView(R.id.et_editable_location)
    EditText editTextLocation;
    @BindView(R.id.et_editable_bio)
    EditText editTextBio;
    @BindView(R.id.switch_editable_hireable)
    Switch switchHireable;
    @BindView(R.id.button_save_changes)
    Button buttonSaveChanges;
    @BindView(R.id.progress_bar_editable)
    ProgressBar progressBar;

    private MvpContractUserEditable.Presenter presenter;
    private Toast toast;
    private String username;

    public static UserEditableFragment newInstance(String username) {
        Bundle args = new Bundle();
        args.putString(Const.KEY_PARCELABLE_USER, username);

        UserEditableFragment fragment = new UserEditableFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_editable_user, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null && getArguments().containsKey(Const.KEY_PARCELABLE_USER)) {
            username = getArguments().getString(Const.KEY_PARCELABLE_USER);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.startView(username);
    }

    @OnClick(R.id.button_save_changes)
    public void onClickSave() {
        buttonSaveChanges.setVisibility(android.view.View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        presenter.clickSaveChanges(
                String.valueOf(editTextName.getText()),
                String.valueOf(editTextEmail.getText()),
                String.valueOf(editTextOrganization.getText()),
                String.valueOf(editTextBlog.getText()),
                String.valueOf(editTextLocation.getText()),
                switchHireable.getSplitTrack(),
                String.valueOf(editTextBio.getText())
        );
    }

    @Override
    public void showEditableData(User user) {
        hideProgressBar();
        editTextName.setHint(user.getName());
        editTextEmail.setHint(user.getEmail());
        editTextOrganization.setHint(user.getCompany());
        editTextBlog.setHint(user.getBlog());
        editTextLocation.setHint(user.getLocation());
        editTextBio.setHint(user.getBio());
        switchHireable.setChecked(user.isHireable());

    }

    @Override
    public void setVisibleButton(boolean isVisible) {
        if (isVisible)
            buttonSaveChanges.setVisibility(android.view.View.VISIBLE);
        else
            buttonSaveChanges.setVisibility(android.view.View.INVISIBLE);
    }

    @Override
    public void showErrorSave(int errorDescriptionResource) {
        toast = Toast.makeText(getContext(), getString(errorDescriptionResource),
                Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void attachPresenter(MvpContractUserEditable.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroyView();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
