package database;

public class Constant {
    public static final String DB_NAME = "ATM";
    public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/" + DB_NAME;
    public static final String TIME_ZONE_ERROR = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    // Account
    public static final String TABLE_ACCOUNT = "Account";
    public static final String ACCOUNT_COLUMN_ID = "id";
    public static final String ACCOUNT_COLUMN_FIRST_NAME = "firstName";
    public static final String ACCOUNT_COLUMN_LAST_NAME = "lastName";
    public static final String ACCOUNT_COLUMN_ACCOUNT_NUMBER = "accountNumber";
    public static final String ACCOUNT_COLUMN_ACCOUNT_AMOUNT = "amount";

    public static final String ACCOUNT_QUERY_EVERYTHING = "SELECT * FROM " + TABLE_ACCOUNT + " ORDER BY " + ACCOUNT_COLUMN_FIRST_NAME + " ASC";
    public static final String QUERY_ACCOUNT_BY_ACCOUNT_NUMBER = "SELECT * FROM " + Constant.TABLE_ACCOUNT +
            " WHERE " + Constant.ACCOUNT_COLUMN_ACCOUNT_NUMBER + " = ?";
    public static final String ACCOUNT_QUERY_EVERYTHING_BASED_ON_ID = "SELECT * FROM " + TABLE_ACCOUNT + " WHERE " + ACCOUNT_COLUMN_ID + " = ?";
    public static final String INSERT_ACCOUNT = "INSERT INTO " + TABLE_ACCOUNT + "(" + ACCOUNT_COLUMN_FIRST_NAME + ", " +
            ACCOUNT_COLUMN_LAST_NAME + ", " + ACCOUNT_COLUMN_ACCOUNT_NUMBER + ", " + ACCOUNT_COLUMN_ACCOUNT_AMOUNT + ")" +
            "VALUES(?, ?, ?, ?)";
    public static final String DELETE_ACCOUNT_BASED_ON_ID = "DELETE FROM " + TABLE_ACCOUNT + " WHERE " +
            ACCOUNT_COLUMN_ACCOUNT_NUMBER + " = ?";

    //Update
    public static final String ACCOUNT_UPDATE_FIRST_NAME = "UPDATE " + TABLE_ACCOUNT + " SET " + ACCOUNT_COLUMN_FIRST_NAME + " = ?" +
            " WHERE " + ACCOUNT_COLUMN_ID + " = ?";
    public static final String ACCOUNT_UPDATE_LAST_NAME = "UPDATE " + TABLE_ACCOUNT + " SET " + ACCOUNT_COLUMN_LAST_NAME + " = ?" +
            " WHERE " + ACCOUNT_COLUMN_ID + " = ?";
    public static final String ACCOUNT_UPDATE_ACCOUNT_NUMBER = "UPDATE " + TABLE_ACCOUNT + " SET " + ACCOUNT_COLUMN_ACCOUNT_NUMBER + " = ?" +
            " WHERE " + ACCOUNT_COLUMN_ID + " = ?";

    // Transfer
    public static final String TABLE_TRANSFER = "Transfer";
    public static final String TRANSFER_COLUMN_SOURCE_ACCOUNT = "sourceAccount";
    public static final String TRANSFER_COLUMN_TARGET_ACCOUNT = "targetAccount";
    public static final String TRANSFER_COLUMN_AMOUNT = "amount";

    public static final String TRANSFER_QUERY_EVERYTHING_BASED_ON_ID = "SELECT * FROM " + TABLE_TRANSFER;
    public static final String INSERT_TRANSFER = "INSERT INTO " + TABLE_TRANSFER + "(" + TRANSFER_COLUMN_SOURCE_ACCOUNT + ", " +
            TRANSFER_COLUMN_TARGET_ACCOUNT + ", " + TRANSFER_COLUMN_AMOUNT + ") " + "VALUES (?, ?, ?)";
    public static final String TRANSFER_WITHDRAW = "UPDATE " + TABLE_ACCOUNT + " SET " + ACCOUNT_COLUMN_ACCOUNT_AMOUNT + " = " +
            ACCOUNT_COLUMN_ACCOUNT_AMOUNT + " - ?" + " WHERE " + ACCOUNT_COLUMN_ID + " = ?";
    public static final String TRANSFER_DEPOSIT = "UPDATE " + TABLE_ACCOUNT + " SET " + ACCOUNT_COLUMN_ACCOUNT_AMOUNT + " = " +
            ACCOUNT_COLUMN_ACCOUNT_AMOUNT + " - ?" + " WHERE " + ACCOUNT_COLUMN_ID + " = ?";
}
