package goat.inform_backend.entity.articles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "club_articles")
public class ClubArticles extends BaseArticle {

    @OneToMany(mappedBy = "clubArticle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ClubAttachment> attachments = new ArrayList<>();
}