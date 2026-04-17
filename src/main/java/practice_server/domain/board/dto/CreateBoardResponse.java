package practice_server.domain.board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateBoardResponse {
    private final Long id;
}
