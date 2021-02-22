package ru.undframe;

import java.util.Objects;

public class MultiplayObject {

    private String name;
    private int age;

    public MultiplayObject(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiplayObject that = (MultiplayObject) o;
        return age == that.age &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "MultiplayObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
