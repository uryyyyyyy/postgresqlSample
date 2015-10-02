package com.github.uryyyyyyy.table3;

import java.sql.Array;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table3Entity {
    private String name;
    private Array keys;
}
