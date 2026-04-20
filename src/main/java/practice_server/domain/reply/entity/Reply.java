package practice_server.domain.reply.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice_server.domain.board.entity.Board;
import practice_server.domain.member.entity.Member;
import practice_server.global.common.BaseTimeEntity;

@Entity
@Getter @Setter
@NoArgsConstructor
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

    @Builder
    private Reply(Board board, Member member, String content) {
        this.board = board;
        this.member = member;
        this.content = content;
    }

    public static Reply createReply(Board board, Member member, String content) {
        Reply reply = Reply.builder()
                .member(member)
                .board(board)
                .content(content)
                .build();
        member.getReplies().add(reply);
        return reply;
    }
}
