package com.sluno.itf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StoreData {
    private int storeNo;
    private String storeName;
    private String phone;
    private String email;
    private String orderEmail;
}
