package app.complyglobal.helper;

import android.content.Context;
import android.util.Log;

import app.complyglobal.utils.RestCallUtil;

/**
 * Created by SUDARSHAN REDDY on 14-10-2017.
 */
public class ServiceHelper {

    private static ServiceHelper serviceHelper;
    private Context context;

    private ServiceHelper(Context context){
        this.context=context.getApplicationContext();
    }

    public static ServiceHelper getInstance(Context context){
        if(serviceHelper==null){
            serviceHelper= new ServiceHelper(context);
        }
        return serviceHelper;
    }

    public void getResponce(){
        Log.i("ServiceHelper","RestCallUtill calling");
        if(false) {
            RestCallUtil util = new RestCallUtil(context);
            util.getRequestData();
        }
    }
}
