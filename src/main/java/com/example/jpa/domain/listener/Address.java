package com.example.jpa.domain.listener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String city; // 시
    private String district; // 구

    @Column(name = "address_detail")
    private String detail; // 상세 주소

    private String zipCode; // 우편 번호
}
