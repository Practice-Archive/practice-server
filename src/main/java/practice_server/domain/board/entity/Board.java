package practice_server.domain.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import practice_server.domain.member.entity.Member;
import practice_server.domain.reply.entity.Reply;
import practice_server.global.common.BaseTimeEntity;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Board extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String title;
    private String content;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Reply> replies;

    @Builder
    public Board(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }
}
