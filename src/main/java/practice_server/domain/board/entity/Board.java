package practice_server.domain.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import practice_server.domain.member.entity.Member;
import practice_server.domain.reply.entity.Reply;
import practice_server.global.common.BaseTimeEntity;

import java.util.List;

@Entity
public class Board extends BaseTimeEntity {
    @Id
    private Long boardId;
    @ManyToOne
    private Member member;
    private String title;
    private String content;
    @OneToMany(mappedBy = "board")
    private List<Reply> replies;
}
