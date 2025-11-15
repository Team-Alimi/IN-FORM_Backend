package goat.inform_backend.entity.vendors;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // Entity임을 명시
@Getter //Lombok 사용
@NoArgsConstructor // 기본 생성자를 자동으로 만들어줌
@Table(name = "vendors") // 'vendors' 테이블과 연결
public class Vendors {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT임을 명시
    @Column(name = "vendor_id")
    private Integer vendorId; // 스키마의 int는 Java의 Integer와 매핑

    @Column(name = "vendor_name", nullable = false, length = 100)
    private String vendorName;

    @Enumerated(EnumType.STRING) // Enum 값을 DB에 '이름' 그대로(SCHOOL, CLUB) 저장
    @Column(name = "vendor_type",unique = true,nullable = false)
    private VendorType vendorType;
}