package practice_server.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice_server.domain.board.entity.Board;
import practice_server.domain.reply.entity.Reply;
import practice_server.global.common.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long memberId;
    private String password;
    private String nickname;

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Reply> replies = new ArrayList<>();

    @Builder
    private Member(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public static Member createMember(String nickname, String password) {
        return Member.builder()
                .nickname(nickname)
                .password(password)
                .build();
    }
}
