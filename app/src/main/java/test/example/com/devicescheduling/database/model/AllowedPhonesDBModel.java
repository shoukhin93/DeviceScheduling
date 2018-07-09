package test.example.com.devicescheduling.database.model;

/**
 * Created by Shoukhin on 7/5/2018.
 */

public class AllowedPhonesDBModel {
    public static final String TABLE_NAME = "allowedphones";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PHONE + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    private int id;
    private String phone;
    private String timestamp;

    public AllowedPhonesDBModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
