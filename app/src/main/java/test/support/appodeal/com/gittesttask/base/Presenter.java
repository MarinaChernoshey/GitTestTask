package test.support.appodeal.com.gittesttask.base;

import io.reactivex.disposables.Disposable;

public abstract class BaseFragmentsPresenter {
    public Disposable disposable;

    public void disposeDisposable(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void destroyView() {
       disposeDisposable(disposable);
    }
}
