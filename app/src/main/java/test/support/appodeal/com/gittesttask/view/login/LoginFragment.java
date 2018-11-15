package test.support.appodeal.com.gittesttask.view.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.core.MvpContractAuthentication;
import test.support.appodeal.com.gittesttask.view.main.MainActivity;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment implements MvpContractAuthentication.ViewLogin {

    @BindView(R.id.et_login)
    EditText editTextLogin;
    @BindView(R.id.et_password)
    EditText editTextPassword;
    @BindView(R.id.textViewError)
    TextView textViewError;
    @BindView(R.id.button_enter)
    Button buttonEnter;
    @BindView(R.id.progress_bar_login)
    ProgressBar progressBar;


    MvpContractAuthentication.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void showAuthenticationError() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorAuthenticationLogin() {
        progressBar.setVisibility(View.INVISIBLE);
        editTextLogin.setError(String.valueOf(R.string.error_login));
    }

    @Override
    public void showErrorAuthenticationPassword() {
        progressBar.setVisibility(View.INVISIBLE);
        editTextPassword.setError(String.valueOf(R.string.error_password));
    }

    @Override
    public void showInternetError() {

    }

    @Override
    public void StartMainView() {
        textViewError.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

    @Override
    public void attachPresenter(MvpContractAuthentication.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return getActivity().getPreferences(MODE_PRIVATE);
    }

    @OnClick(R.id.button_enter)
    public void onClick() {
        textViewError.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        presenter.authenticationUser(
                editTextLogin.getText().toString(),
                editTextPassword.getText().toString());
    }
}
