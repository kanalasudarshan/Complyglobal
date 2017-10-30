package app.complyglobal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.complyglobal.dto.NotificationDTO;
import app.complyglobal.dto.UserDTO;
import app.complyglobal.utils.Constants;

/**
 * Created by SUDARSHAN REDDY on 28-10-2017.
 */

public class NotificationTimeDao extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "Complyglobal";

    private static final String TABLE_NOTIFICATION= "b_notification";

    private static final String NOTIFICATION_ID = "notification_id";
    private static final String NOTIFICATION_NAME = "notification_name";
    private static final String SCHEDULE_TIME = "schedule_time";
    private static final String FREQUENCY_TYPE = "frequency_type";
    private static final String IS_ACTIVE="is_active";

    public NotificationTimeDao(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NOTIFICATION + "("
                + NOTIFICATION_ID + " INTEGER PRIMARY KEY NOT NULL,"
                + NOTIFICATION_NAME + " TEXT NOT NULL,"
                + SCHEDULE_TIME + " TEXT NOT NULL,"
                + FREQUENCY_TYPE + " TEXT,"
                + IS_ACTIVE + " INT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        executeBootstap(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        // Create tables again
        onCreate(db);
    }

    // Adding new User Data
    public void addNotificaton(NotificationDTO dto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOTIFICATION_ID, dto.getNotificaitonId());
        values.put(NOTIFICATION_NAME, dto.getNotificaionName());
        values.put(SCHEDULE_TIME, dto.getScheduleTime());
        values.put(FREQUENCY_TYPE, dto.getFrequencyType());
        values.put(IS_ACTIVE, dto.getIsActive());

        // Inserting Row
        db.insert(TABLE_NOTIFICATION, null, values);
        db.close(); //
    }

    // Getting single User Data
    public NotificationDTO getNotificationById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        NotificationDTO dto =null;
        Cursor cursor = db.query(TABLE_NOTIFICATION,
                new String[] { NOTIFICATION_ID,NOTIFICATION_NAME,SCHEDULE_TIME,FREQUENCY_TYPE,IS_ACTIVE }, NOTIFICATION_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            dto=mappingCursorToNoticationDTO(cursor);
        }
        return dto;
    }

    // Getting All User Data
    public List<NotificationDTO> getAllNotifications() {
        List<NotificationDTO> notificationList = new ArrayList<NotificationDTO>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTIFICATION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NotificationDTO dto=mappingCursorToNoticationDTO(cursor);
                notificationList.add(dto);
            } while (cursor.moveToNext());
        }

        return notificationList;
    }

    // Getting Users Data Count
    public int getNotificationCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NOTIFICATION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    // Updating single User Data
    public int updateNotification(NotificationDTO dto) {
        Log.i("Settings","Notification time updating to db :"+dto.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SCHEDULE_TIME, dto.getScheduleTime());

        // updating row
        return db.update(TABLE_NOTIFICATION, values,NOTIFICATION_ID + " = ?",
                new String[] { String.valueOf(dto.getNotificaitonId()) });
    }


    // Updating single User Data
    public int setNotificationOff(NotificationDTO dto) {
        Log.i("Settings","Notification time updating to db :"+dto.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(IS_ACTIVE, dto.getIsActive());

        // updating row
        return db.update(TABLE_NOTIFICATION, values,NOTIFICATION_ID + " = ?",
                new String[] { String.valueOf(dto.getNotificaitonId()) });
    }

    // Deleting single User Data
    public void deleteNotification(UserDTO userDTO) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTIFICATION, NOTIFICATION_ID + " = ?",
                new String[] { String.valueOf(userDTO.getUserId()) });
        db.close();
    }

    private NotificationDTO mappingCursorToNoticationDTO(Cursor cursor){
        NotificationDTO dto = new NotificationDTO();
        dto.setNotificaitonId(Integer.parseInt(cursor.getString(0)));
        dto.setNotificaionName(cursor.getString(1));
        dto.setScheduleTime(cursor.getString(2));
        dto.setFrequencyType(cursor.getString(3));
        dto.setIsActive(Integer.parseInt(cursor.getString(4)));
        return dto;
    }

    private void executeBootstap(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(NOTIFICATION_ID, Constants.NOTIFICATION_ONE_ID);
        values.put(NOTIFICATION_NAME, "Schedule 1");
        values.put(SCHEDULE_TIME, "15/06/1998 06:30");
        values.put(FREQUENCY_TYPE, "Daily");
        values.put(IS_ACTIVE, Constants.IS_ACTIVE_TRUE);
        // Inserting Row
        db.insert(TABLE_NOTIFICATION, null, values);

        values.clear();
        values.put(NOTIFICATION_ID, Constants.NOTIFICATION_TWO_ID);
        values.put(NOTIFICATION_NAME, "Schedule 2");
        values.put(SCHEDULE_TIME, "15/06/1998 18:30");
        values.put(FREQUENCY_TYPE, "Daily");
        values.put(IS_ACTIVE, Constants.IS_ACTIVE_TRUE);

        // Inserting Row
        db.insert(TABLE_NOTIFICATION, null, values);
    }

}
