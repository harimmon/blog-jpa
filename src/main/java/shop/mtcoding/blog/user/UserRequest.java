package shop.mtcoding.blog.user;

import lombok.Data;

public class UserRequest {

    // insert되는 용도의 DTO에는 toEntity 메소드를 만든다.
    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        }
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
        private String rememberMe; // check되면 on, 안되면 null
    }
}