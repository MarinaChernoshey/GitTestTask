package test.support.appodeal.com.gittesttask.util;

import io.reactivex.functions.Consumer;
import test.support.appodeal.com.gittesttask.base.BaseViewMain;

public abstract class ThrowableUtil {

    public static void showThrowableInScreen(Throwable e, BaseViewMain view) {
        view.showErrorToast(e.getMessage());
        view.hideProgressBar();
    }

    public static Consumer<Throwable> getConsumerForShowThrowableScreen(BaseViewMain view) {
        return e -> {
            view.showErrorToast(e.getMessage());
            view.hideProgressBar();
        };
    }
}
