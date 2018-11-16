package test.support.appodeal.com.gittesttask.base;

import io.reactivex.disposables.Disposable;

public abstract class Presenter implements BasePresenter {
    protected Disposable disposable;

    protected void disposeDisposable(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void destroyView() {
       disposeDisposable(disposable);
    }
}
