package goat.inform_backend.entity.articles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "club_articles")
public class ClubArticles extends BaseArticle {

    // ClubArticles만의 고유 필드: attachment (varbinary(MAX))
    @Lob // ⭐️ Large Object (BLOB/CLOB) - 긴 바이너리 데이터를 담을 때 사용
    @Column(name = "attachment")
    private byte[] attachment; // Java에서 바이너리 데이터는 byte 배열로 처리합니다.
}