package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

// 비지니스 로직, 트랜잭션 처리, DTO 완료
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        try {
            userRepository.save(joinDTO.toEntity());
        } catch (Exception e) {
            throw new RuntimeException("야 동일한 아이디로 회원가입 하는 ... 하지마라 !! 포스트맨 쓰지마라 !");
        }
    }

    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if (!user.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("유저네임 혹은 비밀번호가 틀렸습니다");
        }
        return user;
    }

    public Map<String, Object> 유저네임중복체크(String username) {
        User user = userRepository.findByUsername(username);
        Map<String, Object> dto = new HashMap<>(); // 한 개 짜리는 DTO 만들지 말고 Map으로 만들어서 써라

        if (user == null) {
            dto.put("available", true);
        } else {
            dto.put("available", false);
        }
        return dto;
    }
}