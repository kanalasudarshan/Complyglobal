package app.complyglobal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import app.complyglobal.dto.UserDTO;

/**
 * Created by skanala on 10/4/2017.
 */

public class UserDAO extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Complyglobal";

    private static final String TABLE_USER = "b_user";

    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String PASSWORD = "password";
    private static final String MOBILE_PIN = "mobile_pin";
    private static final String IS_ACTIVE="is_active";

    public UserDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + USER_ID + " INTEGER PRIMARY KEY NOT NULL,"
                + USER_NAME + " TEXT NOT NULL,"
                + FIRST_NAME + " TEXT NOT NULL,"
                + LAST_NAME + " TEXT,"
                + PASSWORD + " TEXT NOT NULL,"
                + MOBILE_PIN + " INT,"
                + IS_ACTIVE + " INT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // Create tables again
        onCreate(db);
    }

    // Adding new User Data
    public void addUser(UserDTO userDTO) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, userDTO.getUserId());
        values.put(USER_NAME, userDTO.getUserName());
        values.put(FIRST_NAME, userDTO.getFirstName());
        values.put(LAST_NAME, userDTO.getLastName());
        values.put(PASSWORD, userDTO.getPassword());
        values.put(MOBILE_PIN, userDTO.getMobilePin());
        values.put(IS_ACTIVE, userDTO.getIsActive());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); //
    }

    // Getting single User Data
    public UserDTO getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        UserDTO userDTO =null;
        Cursor cursor = db.query(TABLE_USER,
                new String[] { USER_ID,USER_NAME,FIRST_NAME,LAST_NAME,PASSWORD,MOBILE_PIN,IS_ACTIVE }, USER_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            userDTO=mappingCursorToUserDTO(cursor);
        }
        return userDTO;
    }

    // Getting All User Data
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userList = new ArrayList<UserDTO>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserDTO userDTO=mappingCursorToUserDTO(cursor);
                userList.add(userDTO);
            } while (cursor.moveToNext());
        }

        return userList;
    }

    // Getting Users Data Count
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    // Updating single User Data
    public int updateUser(UserDTO userDTO) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_NAME, userDTO.getUserName());
        values.put(FIRST_NAME, userDTO.getFirstName());

        // updating row
        return db.update(TABLE_USER, values,USER_ID + " = ?",
                new String[] { String.valueOf(userDTO.getUserId()) });
    }

    // Deleting single User Data
    public void deleteUser(UserDTO userDTO) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, USER_ID + " = ?",
                new String[] { String.valueOf(userDTO.getUserId()) });
        db.close();
    }

    private UserDTO mappingCursorToUserDTO(Cursor cursor){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(Integer.parseInt(cursor.getString(0)));
        userDTO.setUserName(cursor.getString(1));
        userDTO.setFirstName(cursor.getString(2));
        userDTO.setLastName(cursor.getString(3));
        userDTO.setPassword(cursor.getString(4));
        userDTO.setMobilePin(Integer.parseInt(cursor.getString(5)));
        userDTO.setIsActive(Integer.parseInt(cursor.getString(6)));
        return userDTO;
    }

}
