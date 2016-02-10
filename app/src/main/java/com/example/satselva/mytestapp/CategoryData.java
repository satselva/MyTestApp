package com.example.satselva.mytestapp;

import android.provider.BaseColumns;

/**
 * Created by satselva on 2/10/2016.
 */
public final class CategoryData {

        public CategoryData() {}

        /* Inner class that defines the table contents */
        public static abstract class CategoryEntry implements BaseColumns {
            public static final String TABLE_NAME = "category";
            public static final String COLUMN_NAME_ID = "id";
            public static final String COLUMN_NAME_NAME = "name";
            public static final String COLUMN_NAME_ICON_URL = "icon_url";
            public static final String COLUMN_NAME_CREATED_BY = "created_by";
            public static final String COLUMN_NAME_MODIFIED_BY = "modified_by";
            public static final String COLUMN_NAME_CREATED_DATE = "created_date";
            public static final String COLUMN_NAME_MODIFIED_DATE = "modified_date";

        }
    }
