package ru.undframe;


import java.util.Objects;

public class Matrix {

    private String v1;
    private String v2;
    private int value1;
    private int value2;

    public Matrix(String v1, String v2, int value1, int value2) {
        this.v1 = v1;
        this.v2 = v2;
        this.value1 = value1;
        this.value2 = value2;
    }

    public String getV1() {
        return v1;
    }

    public String getV2() {
        return v2;
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "v1='" + v1 + '\'' +
                ", v2='" + v2 + '\'' +
                ", value1=" + value1 +
                ", value2=" + value2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return value1 == matrix.value1 &&
                value2 == matrix.value2 &&
                Objects.equals(v1, matrix.v1) &&
                Objects.equals(v2, matrix.v2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(v1, v2, value1, value2);
    }
}
