package com.sluno.itf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StoreRawData {
    private int storeNo;
    private String storeName;
    private String phone;
    private String email;
    private String orderEmail;
    private String motherStore;
    private String lastUpdatedAt;
    private String stat;

    @Override
    public String toString() {
        return "StoreRawData{" +
                "storeNo='" + storeNo + '\'' +
                ", storeName='" + storeName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", orderEmail='" + orderEmail + '\'' +
                ", motherStore='" + motherStore + '\'' +
                ", lastUpdatedAt='" + lastUpdatedAt + '\'' +
                ", stat='" + stat + '\'' +
                '}';
    }
}
