package app.complyglobal.utils;

import okhttp3.MediaType;

/**
 * Created by SUDARSHAN REDDY on 14-10-2017.
 */
public class RESTConstants {

    public static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public static final MediaType MEDIA_TYPE_JSON= MediaType.parse("application/json; charset=utf-8");

    public static final String BASE_URI="https://app.complyglobal.com/csuite/login/Login.action";

    /**
     *  GET METHODS
     */
    public static final String ENTITY_DASHBOARD_DATA_METHOD="http://httpbin.org/basic-auth/user/passwd";
    public static final String GROUP_DASHBOARD_DATA_METHOD="";

    public static final String DATA_METHOD_FOR_CALENDAR_COMPLIANCE="";
    public static final String DATA_METHOD_FOR_CHECKED_LIST_COMPLIANCE="";
    public static final String DATA_METHOD_FOR_REGISTER_COMPLIANCE="";
    public static final String DATA_METHOD_FOR_REGISTRATIONS_COMPLIANCE="";

    public static final String DATA_METHOD_FOR_COMPLIANCE_DETAILS="";




    /**
     * UPDATE METHODS
     */
    public static final String UPDATE_COMPLIANCE_DETAILS="";




    /**
     * INSERT METHODS
     */
    public static final String INSERT_USER_DETAILS="";
}
