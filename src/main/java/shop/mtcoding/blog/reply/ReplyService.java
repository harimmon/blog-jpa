package shop.mtcoding.blog.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardRepository;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRepository;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository; // 게시글 정보 필요
    private final UserRepository userRepository;

    @Transactional
    public void 댓글쓰기(ReplyRequest.SaveDTO requestDTO, User sessionUser) {
        Board board = boardRepository.findById(requestDTO.getBoardId());

        User userPS = userRepository.findById(sessionUser.getId());

        Reply reply = new Reply(requestDTO.getComment(), userPS, board);
        replyRepository.save(reply);
    }
}
