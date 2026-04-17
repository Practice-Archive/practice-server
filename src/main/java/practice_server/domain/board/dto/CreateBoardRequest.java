package practice_server.domain.board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateBoardRequest {
    private final Long memberId;
    private final String title;
    private final String content;
}
