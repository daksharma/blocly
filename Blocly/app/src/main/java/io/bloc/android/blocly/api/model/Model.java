package io.bloc.android.blocly.api.model;

/**
 * Created by Daksh on 1/17/16.
 */
public abstract class Model {

    private final long rowId;

    public Model(long rowId) {
        this.rowId = rowId;
    }

    public long getRowId() {
        return rowId;
    }
}