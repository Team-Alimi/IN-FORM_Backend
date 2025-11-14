package com.example.myproject.entity.vendor;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // 이 클래스는 DB 테이블과 매핑됩니다.
@Getter
@Setter
@NoArgsConstructor // 기본 생성자를 자동으로 만들어줍니다.
@Table(name = "vendors") // 'vendors' 테이블과 연결
public class Vendor {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "vendor_id")
    private Integer vendorId; // 스키마의 int는 Java의 Integer와 매핑

    @Column(name = "vendor_name", nullable = false, unique = true, length = 100)
    private String vendorName;

    @Enumerated(EnumType.STRING) // ⭐️ Enum 값을 DB에 '이름' 그대로(SCHOOL, CLUB) 저장
    @Column(name = "vendor_type", nullable = false)
    private VendorType vendorType = VendorType.SCHOOL; // ⭐️ Java 레벨에서 기본값 설정
}