package practice_server.domain.reply.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import practice_server.domain.board.entity.Board;
import practice_server.domain.member.entity.Member;
import practice_server.global.common.BaseTimeEntity;

@Entity
public class Reply extends BaseTimeEntity {
    @Id
    private Long replyId;
    @ManyToOne
    private Board board;
    @ManyToOne
    private Member member;
    private String content;
}
