package com.epam.suleimenov.model;

/**
 * Created by admin on 6/27/2016.
 */
public class BaseEntity {

    private int id;

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
