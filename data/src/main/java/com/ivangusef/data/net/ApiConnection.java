package com.ivangusef.data.net;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Api Connection class used to retrieve data from the cloud. Implements {@link java.util.concurrent.Callable} so when executed asynchronously can
 * return a value.
 */
public class ApiConnection implements Callable<String> {

    private static final String TAG = ApiConnection.class.getSimpleName();

    public static ApiConnection createGET(@NonNull final String url) throws MalformedURLException {
        return new ApiConnection(url, null);
    }

    public static ApiConnection createGET(@NonNull final String url, @Nullable final String mediaType) throws MalformedURLException {
        return new ApiConnection(url, mediaType);
    }

    private URL    url;
    private String mediaType;
    private String response;

    private ApiConnection(@NonNull final String url, @Nullable final String mediaType) throws MalformedURLException {
        this.url = new URL(url);
        this.mediaType = mediaType;
    }

    /**
     * Do a request to an api synchronously. It should not be executed in the main thread of the application.
     *
     * @return A string response
     */
    @Nullable
    public String requestSyncCall() {
        connectToApi();
        return response;
    }

    private void connectToApi() {
        final Request request = new Request.Builder().url(url).get().build();
        try {
            final Response resp = createClient().newCall(request).execute();
            if (!resp.isSuccessful()) {
                throw new IOException("Unexpected code: " + resp.code() + ", message: " + resp.message());
            }
            final ResponseBody body = resp.body();
            if (mediaType != null) {
                response = ResponseBody.create(MediaType.parse(mediaType), body.contentLength(), body.source()).string();
            } else {
                response = body.string();
            }
        } catch (IOException e) {
            Log.w(TAG, e.getMessage(), e);
        }
    }

    private OkHttpClient createClient() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(10, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(15, TimeUnit.SECONDS);

        return okHttpClient;
    }

    @Override
    public String call() throws Exception {
        return requestSyncCall();
    }
}
