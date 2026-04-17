package practice_server.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateMemberRequest {
    @NotBlank(message = "닉네임은 필수입니다.")
    private final String nickname;

    @Size(min = 2 , message = "비밀번호는 2자리 이상이어야 합니다.")
    private final String password;
}
