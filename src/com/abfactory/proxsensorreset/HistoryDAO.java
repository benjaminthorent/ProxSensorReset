package com.abfactory.proxsensorreset;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class HistoryDAO extends SQLiteOpenHelper {

    // If we change the database schema, we must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "History.db";
    
    // SQL Commands
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + HistoryEntry.TABLE_NAME;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + HistoryEntry.TABLE_NAME + " (" +
        HistoryEntry._ID + " INTEGER PRIMARY KEY," +
        HistoryEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
        HistoryEntry.COLUMN_NAME_TYPE + TEXT_TYPE + COMMA_SEP +
        HistoryEntry.COLUMN_NAME_VALUE + TEXT_TYPE + COMMA_SEP +
        " )";
    
	// Define a projection that specifies which columns from the database
	// we will actually use after this query.
	private static String[] PROJECTION = {
	    HistoryEntry._ID,
	    HistoryEntry.COLUMN_NAME_DATE,
	    HistoryEntry.COLUMN_NAME_TYPE,
	    HistoryEntry.COLUMN_NAME_VALUE
	    };
    
	public HistoryDAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/* Inner class that defines the table contents */
    public static abstract class HistoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "history";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_VALUE = "value";
    }

    // Database creation
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    // This database is only a cache for online data, so its upgrade policy is
	    // to simply to discard the data and start over
	    db.execSQL(SQL_DELETE_ENTRIES);
	    onCreate(db);
	}
	
	@Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
	
	// CRUD methods
	public long createHistory(String date, String type, String value){
		// Gets the data repository in write mode
		SQLiteDatabase db = this.getWritableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(HistoryEntry.COLUMN_NAME_DATE, date);
		values.put(HistoryEntry.COLUMN_NAME_TYPE, type);
		values.put(HistoryEntry.COLUMN_NAME_VALUE, value);

		// Insert the new row, returning the primary key value of the new row
		long newRowId;
		newRowId = db.insert(HistoryEntry.TABLE_NAME, null, values);
		return newRowId;
	}
	
	public History getHistoryById(String id){
		SQLiteDatabase db = this.getReadableDatabase();

		// How we want the results sorted in the resulting Cursor
		String sortOrder = 	HistoryEntry.COLUMN_NAME_DATE + " DESC";

		Cursor cursor = db.query(HistoryEntry.TABLE_NAME, // The table to query
		    PROJECTION,      							  // The columns to return
		    HistoryEntry._ID,                         	  // The columns for the WHERE clause
		    new String[]{id},                         	  // The values for the WHERE clause
		    null,                                    	  // don't group the rows
		    null,                                     	  // don't filter by row groups
		    sortOrder                                 	  // The sort order
		    );		
		
		History history = null;
		if(cursor.moveToFirst()){
			history = new History();
			history.setId(cursor.getString(cursor.getColumnIndexOrThrow(HistoryEntry._ID)));
			history.setDate(cursor.getString(cursor.getColumnIndexOrThrow(HistoryEntry.COLUMN_NAME_DATE)));
			history.setType(cursor.getString(cursor.getColumnIndexOrThrow(HistoryEntry.COLUMN_NAME_TYPE)));
			history.setValue(cursor.getString(cursor.getColumnIndexOrThrow(HistoryEntry.COLUMN_NAME_VALUE)));
		}
		return history;
	}
	
	public void deleteHistoryById(String rowId){
		
		SQLiteDatabase db = this.getReadableDatabase();
		
		// Define 'where' part of query.
		String selection = HistoryEntry._ID + " LIKE ?"; // TODO: better to use = ?
		// Specify arguments in placeholder order.
		String[] selectionArgs = { String.valueOf(rowId) };
		// Issue SQL statement.
		db.delete(HistoryEntry.TABLE_NAME, selection, selectionArgs);
	}
	
	// No need of update right? :)
}
