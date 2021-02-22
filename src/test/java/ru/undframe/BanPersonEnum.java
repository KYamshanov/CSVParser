package ru.undframe;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum BanPersonEnum {


    KOS("1", "Kostya", 12L, 7L, 5L);


    private String id;
    private String name;
    private long income;
    private long costs;
    private long remainder;


    BanPersonEnum(String id, String name, long income, long costs, long remainder) {
        this.id = id;
        this.name = name;
        this.income = income;
        this.costs = costs;
        this.remainder = remainder;
    }
}
