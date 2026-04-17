package practice_server.domain.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice_server.domain.reply.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Reply findReplyByReplyId(Long replyId);
}
