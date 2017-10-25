package app.complyglobal.utils;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by SUDARSHAN REDDY on 14-10-2017.
 */
public class RestCallUtil {

    private static OkHttpClient okHttpClient;
    private RequestBody requestBody;
    private Context context;
    private Request request;

    public RestCallUtil(Context context){
        this.context=context;
        if(okHttpClient==null) {
            okHttpClient = new OkHttpClient.Builder().authenticator(new Authenticator() {
                @Override
                public Request authenticate(Route route, Response response) throws IOException {
                    if (response.request().header("Authorization") != null) {
                        Log.i("RestCallUtil", "Failed to Authorization");
                    }

                    Log.i("RestCallUtil", route.address().toString());
                    Log.i("RestCallUtil", route.proxy().toString());
                    Log.i("RestCallUtil", route.socketAddress().toString());
                    String credential = Credentials.basic("user", "passwd");
                    return response.request().newBuilder().header("Authorization", credential).build();
                }
            }).build();
        }
    }


    public void getRequestData(){
        Log.i("RestCallUtil","getRequestData method calling for uri :"+RESTConstants.ENTITY_DASHBOARD_DATA_METHOD);
        request=new Request.Builder()
                .url(RESTConstants.ENTITY_DASHBOARD_DATA_METHOD)
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("RestCallUtil",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("RestCallUtil",response.toString());
                Log.i("RestCallUtil",response.body().string());
            }
        });
    }
}
