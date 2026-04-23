package practice_server.domain.board.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import practice_server.domain.board.dto.BoardListResponse;

import java.util.List;

import static practice_server.domain.board.entity.QBoard.board;
import static practice_server.domain.member.entity.QMember.member;
import static practice_server.domain.reply.entity.QReply.reply;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<BoardListResponse> findBoardPage(Pageable pageable) {
        List<BoardListResponse> content = queryFactory
                .select(Projections.constructor(BoardListResponse.class,
                        board.boardId,
                        member.nickname,
                        board.title,
                        board.content,
                        reply.count()
                ))
                .from(board)
                .join(board.member, member)
                .leftJoin(board.replies, reply)
                .groupBy(board.boardId, member.nickname, board.title, board.content)
                .orderBy(boardIdDesc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(board.count())
                .from(board)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }

    private OrderSpecifier<Long> boardIdDesc() {
        return new OrderSpecifier<>(Order.DESC, board.boardId);
    }
}