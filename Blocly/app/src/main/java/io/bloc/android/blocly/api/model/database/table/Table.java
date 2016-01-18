package io.bloc.android.blocly.api.model.database.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Daksh on 1/17/16.
 */


public abstract class Table {


    public static interface Builder {
        public long insert(SQLiteDatabase writeableDB);
    }

    protected static final String COLUMN_ID = "id";

    public abstract String getName ();

    public abstract String getCreateStatement ();

    public void onUpgrade (SQLiteDatabase writableDatabase, int oldVersion, int newVersion) {
    }
}