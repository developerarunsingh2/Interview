package com.ey;

import java.util.Collections;
import java.util.List;

public final class ImmutableTest {

    private final int id;
    private final List<String> name;


    public ImmutableTest(int id, List<String> name) {
        this.id = id;
        //this.name = ListUtils.un;
        this.name = Collections.unmodifiableList(name);
    }


    public int getId() {
        return id;
    }

    public List<String> getName() {
        //super.clone();
        return name;
    }

    @Override
    public String toString() {
        return "ImmutableTest{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
