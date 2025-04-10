package shop.mtcoding.blog.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.erorr.ex.Exception403;
import shop.mtcoding.blog._core.erorr.ex.Exception404;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    @Transactional
    public void 댓글쓰기(ReplyRequest.SaveDTO reqDTO, User sessionUser) {
        replyRepository.save(reqDTO.toEntity(sessionUser));
    }

    @Transactional
    public Integer 댓글삭제(Integer id, Integer sessionUserId) {
        Reply replyPS = replyRepository.findById(id);

        // TODO Exception404
        if (replyPS == null) throw new Exception404("댓글을 찾을 수 없습니다.");

        if (!(replyPS.getUser().getId().equals(sessionUserId))) {
            throw new Exception403("해당 댓글을 삭제할 권한이 없습니다.");
        }

        int boardId = replyPS.getBoard().getId();

        replyRepository.deleteById(id);

        return boardId;
    }
}
