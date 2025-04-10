package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private String title;
        private String content;
        private String isPublic;

        public Board toEntity(User user) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .isPublic(isPublic == null ? false : true)
                    .user(user) // user객체 필요
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;

        // 기존 board 엔티티에 반영
        public void updateEntity(Board board) {
            board.update(title, content); // Board 엔티티 내부에 update 메서드 필요
        }
    }

}
