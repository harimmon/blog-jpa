package shop.mtcoding.blog.reply;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

public class ReplyRequest {

    @Data
    public static class SaveDTO {

        @NotEmpty(message = "댓글을 입력하세요.")
        private String content;
        
        @NotEmpty(message = "board의 id가 전달되어야 합니다.")
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
