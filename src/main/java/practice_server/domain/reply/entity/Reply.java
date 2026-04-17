package practice_server.domain.reply.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import practice_server.domain.board.entity.Board;
import practice_server.domain.member.entity.Member;
import practice_server.global.common.BaseTimeEntity;

@Entity
@Getter @Setter
public class Reply extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;
}
