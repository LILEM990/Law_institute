package com.sang.law.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Knowledge {
    private int id;
    private String describ;
    private String type;
    private int level;
}
