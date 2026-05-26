package practice_server.domain.board.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record BoardPageResponse(
        List<BoardListResponse> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean last
) {
    public static BoardPageResponse from(Page<BoardListResponse> page) {
        return new BoardPageResponse(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}