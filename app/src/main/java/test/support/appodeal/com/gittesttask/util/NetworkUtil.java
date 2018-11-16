package test.support.appodeal.com.gittesttask.util;

import java.io.IOException;

import retrofit2.HttpException;
import test.support.appodeal.com.gittesttask.R;

public class NetworkUtil {

    private static final int IO_THROWABLE = R.string.connection_enternet;
    private static final int HTTP_THROWABLE = R.string.http_throwable;
    private static final int DEFAULT_THROWABLE = R.string.default_throwable;

    public static int getIdDescription(Throwable throwable) {
        if (throwable instanceof IOException)
            return IO_THROWABLE;
        else if (throwable instanceof HttpException)
            return HTTP_THROWABLE;
        return DEFAULT_THROWABLE;
    }

}
