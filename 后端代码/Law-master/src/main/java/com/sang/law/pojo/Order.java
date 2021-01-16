package com.sang.law.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String order_id;
    private String user_id;
    private String user_name;
    private String lawyer_id;
    private String lawyer_name;
    private String order_descrip;
    private String order_type;
    private boolean done;
    private String start;
    private String end;
}
