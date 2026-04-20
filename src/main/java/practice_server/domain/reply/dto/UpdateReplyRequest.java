package practice_server.domain.reply.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateReplyRequest {
    private final String content;
}

