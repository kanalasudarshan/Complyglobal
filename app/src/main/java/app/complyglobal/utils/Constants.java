package app.complyglobal.utils;

import java.text.SimpleDateFormat;

/**
 * Created by SUDARSHAN REDDY on 01-10-2017.
 */
public class Constants {
    public static String PREFERENCES_KEY="Complyglobal";

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static int IS_ACTIVE_TRUE=1;
    public static int IS_ACTIVE_FALSE=2;

    public static int NOTIFICATION_ONE_ID=1;
    public static int NOTIFICATION_TWO_ID=2;

    public static int NOTIFICATION_ONE_REQUEST_CODE=2011;
    public static int NOTIFICATION_TWO_REQUEST_CODE=2012;


    public static int SORTING_BY_DATE_DESC=1;
    public static int SORTING_BY_DATE_ASC=2;
    public static int SORTING_BY_TYPE_DESC=3;
    public static int SORTING_BY_TYPE_ASC=4;
    public static int SORTING_BY_CATEGORY_DESC=5;
    public static int SORTING_BY_CATEGORY_ASC=6;
}
