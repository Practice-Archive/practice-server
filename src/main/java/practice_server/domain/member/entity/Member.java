package practice_server.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import practice_server.domain.board.entity.Board;
import practice_server.domain.reply.entity.Reply;
import practice_server.global.common.BaseTimeEntity;

import java.util.List;

@Entity
@Getter @Setter
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long memberId;
    private String password;
    private String nickname;

    @OneToMany(mappedBy = "member")
    private List<Board> boards;

    @OneToMany(mappedBy = "member")
    private List<Reply> replies;
}
