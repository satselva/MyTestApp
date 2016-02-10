package com.example.satselva.mytestapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by satselva on 2/10/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "category.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CategoryData.CategoryEntry.TABLE_NAME + " (" +
                    CategoryData.CategoryEntry.COLUMN_NAME_ID + TEXT_TYPE +  " PRIMARY KEY," +
                    CategoryData.CategoryEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    CategoryData.CategoryEntry.COLUMN_NAME_ICON_URL + TEXT_TYPE + COMMA_SEP +
                    CategoryData.CategoryEntry.COLUMN_NAME_CREATED_BY + TEXT_TYPE + COMMA_SEP +
                    CategoryData.CategoryEntry.COLUMN_NAME_MODIFIED_BY + TEXT_TYPE +
            " )";

    private static final String CATEGORY_ALL_QUERY = "SELECT * FROM " + CategoryData.CategoryEntry.TABLE_NAME;

    protected static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CategoryData.CategoryEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This creates the tables needed for this Android Application.
     * Add the tables which needed to be created for the startup of application.
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onDowngrade(db, oldVersion, newVersion);
    }

    /**
     * Insert Category into the local databas with the table name in the CategoryEntry.
     * @param category
     */
    public void addCategory(Category category) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(CategoryData.CategoryEntry.COLUMN_NAME_ID, category.getId());
            values.put(CategoryData.CategoryEntry.COLUMN_NAME_NAME, category.getName());
            values.put(CategoryData.CategoryEntry.COLUMN_NAME_ICON_URL, category.getIconUrl());
            values.put(CategoryData.CategoryEntry.COLUMN_NAME_CREATED_BY, category.getCreatedBy());
            values.put(CategoryData.CategoryEntry.COLUMN_NAME_MODIFIED_BY, category.getModifiedBy());

            db.insert(CategoryData.CategoryEntry.TABLE_NAME, null, values);
        } catch(Exception e ) {
            Log.e("SQLERROR", String.format("Exception occurred while inserting the Category details into DB"), e);
        }
    }

    public List<Category> listAllCategories() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor queryCursor = db.rawQuery(CATEGORY_ALL_QUERY, null);
            List<Category> categories = new ArrayList<>();
            if (queryCursor.moveToFirst()) {
                do {
                    Category category = toCategoryFromDBCursor(queryCursor);
                    categories.add(category);
                    Log.d("QUERY", String.format("Returned Category %s from DB", category));
                } while (queryCursor.moveToNext());
            }
            return categories;
        } catch(Exception e) {
            Log.e("SQLERROR", String.format("Exception occurred while querying category details from DB"), e);
        }
        return new ArrayList<>();
    }

    public Category getCategory(final String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CategoryData.CategoryEntry.TABLE_NAME, null, CategoryData.CategoryEntry.COLUMN_NAME_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return toCategoryFromDBCursor(cursor);
    }


    private Category toCategoryFromDBCursor(Cursor categoryCursor) {
        return new Category.CategoryBuilder().withId(categoryCursor.getString(0)).withName(categoryCursor.getString(1))
                .withIconUrl(categoryCursor.getString(2)).withCreatedBy(categoryCursor.getString(3))
                .withModifiedBy(categoryCursor.getString(4)).build();
    }


    /**
     * TODO: Implement this method along with the POJO for article.
     */
    public void addArticle() {

    }
}
