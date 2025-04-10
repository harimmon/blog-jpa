package shop.mtcoding.blog.reply;

import lombok.Data;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

public class ReplyRequest {

    @Data
    public static class SaveDTO {
        private String content;
        private Integer boardId;

        public Reply toEntity(User sessionUser) {
            return Reply.builder()
                    .content(content)
                    .board(Board.builder().id(boardId).build())
                    .user(sessionUser)
                    .build();
        }
    }
}
