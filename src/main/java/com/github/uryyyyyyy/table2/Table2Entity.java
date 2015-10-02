package com.github.uryyyyyyy.table2;


import java.sql.Time;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table2Entity {
    private Timestamp dateTime;
    private Timestamp onlyDate;
    private Time onlyTime;
}