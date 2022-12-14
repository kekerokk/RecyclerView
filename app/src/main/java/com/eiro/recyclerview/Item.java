package com.eiro.recyclerview;

import androidx.annotation.NonNull;

import java.util.Calendar;
import java.util.Objects;

public class Item {

    private String name;
    private Calendar created;
    private boolean done;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Item(String name, Calendar created)
    {
        this.name = name;
        this.created = created;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }
}
