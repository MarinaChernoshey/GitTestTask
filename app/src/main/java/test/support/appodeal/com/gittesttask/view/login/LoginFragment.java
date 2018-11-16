package test.support.appodeal.com.gittesttask.view.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.base.BaseFragment;
import test.support.appodeal.com.gittesttask.view.main.MainActivity;

public class LoginFragment extends BaseFragment implements MvpContractAuthentication.View {

    @BindView(R.id.et_login)
    EditText editTextLogin;
    @BindView(R.id.et_password)
    EditText editTextPassword;
    @BindView(R.id.button_enter)
    Button buttonEnter;
    @BindView(R.id.progress_bar_login)
    ProgressBar progressBar;

    MvpContractAuthentication.Presenter presenter;

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        presenter = new LoginPresenter(this);
        return view;
    }

    @Override
    public void StartMainView() {
        hideProgressBar();
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

    @Override
    public void attachPresenter(MvpContractAuthentication.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void saveAuthDataInSharedPreferences(String nameFile, String key,
                                                int mode, String authHeader) {
        SharedPreferences.Editor editor = getActivity()
                .getSharedPreferences(nameFile, mode)
                .edit();
        editor.putString(key, authHeader);
        editor.commit();
    }

    @OnClick(R.id.button_enter)
    public void onClick() {
        progressBar.setVisibility(android.view.View.VISIBLE);
        presenter.authenticationUser(
                editTextLogin.getText().toString(),
                editTextPassword.getText().toString());
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(android.view.View.INVISIBLE);
    }
}
