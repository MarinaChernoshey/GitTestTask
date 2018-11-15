package test.support.appodeal.com.gittesttask.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.util.Const;

public class WebRepositoriesFragment extends Fragment {

    @BindView(R.id.web_view)
    WebView webView;

    private String url;

    public WebRepositoriesFragment() {
    }

    public static WebRepositoriesFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString(Const.KEY_SAVE_REPOSITORY_URL, url);
        WebRepositoriesFragment fragment = new WebRepositoriesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_web_repositories_fragment, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null && getArguments().containsKey(Const.KEY_SAVE_REPOSITORY_URL)) {
            url = getArguments().getString(Const.KEY_SAVE_REPOSITORY_URL);

            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);
        }

        return view;
    }

}
