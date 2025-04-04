package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        User user = joinDTO.toEntity(); // 1. 비영속 객체
        System.out.println("비영속 user : " + user.getId());
        userRepository.save(user);
        // user 객체 -> 4. 영속객체
        System.out.println("영속/동기화 user : " + user.getId());

        // TODO: 정리하기
        //System.out.println("--------------");
        userRepository.findById(4);
        //System.out.println("--------------");
    }

    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if (!user.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("유저네임 혹은 비밀번호가 틀렸습니다");
        }
        return user;
    }
}
