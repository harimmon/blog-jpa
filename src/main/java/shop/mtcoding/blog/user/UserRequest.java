package shop.mtcoding.blog.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class UserRequest {

    @Data
    public static class UpdateDTO {
        @NotEmpty(message = "유저네임을 입력하세요.")
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "유저네임은 2-20자이며, 특수문자, 한글이 포함될 수 없습니다.")
        private String username;

        @NotEmpty(message = "비밀번호를 입력하세요. 비밀번호는 4자 이상 20자 이하 입니다.")
        @Size(min = 4, max = 20)
        private String password;

        @NotEmpty(message = "이메일을 입력하세요.")
        @Pattern(regexp = "^[a-zA-Z0-9.]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$", message = "이메일 형식으로 작성해주세요.")
        private String email;
    }

    // insert 용도의 dto에는 toEntity 메서드를 만든다.
    @Data
    public static class JoinDTO {
        @NotEmpty(message = "유저네임을 입력하세요.")
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "유저네임은 2-20자이며, 특수문자, 한글이 포함될 수 없습니다.")
        private String username;

        @NotEmpty(message = "비밀번호를 입력하세요. 비밀번호는 4자 이상 20자 이하 입니다.")
        @Size(min = 4, max = 20)
        private String password;

        @NotEmpty(message = "이메일을 입력하세요.")
        @Pattern(regexp = "^[a-zA-Z0-9.]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$", message = "이메일 형식으로 작성해주세요.")
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
        @NotEmpty(message = "유저네임을 입력하세요.")
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "유저네임은 2-20자이며, 특수문자, 한글이 포함될 수 없습니다.")
        private String username;

        @NotEmpty(message = "비밀번호를 입력하세요. 비밀번호는 4자 이상 20자 이하 입니다.")
        @Size(min = 4, max = 20)
        private String password;

        private String rememberMe; // check되면 on, 안되면 null
    }
}
