package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    /*
        1. createNativeQuery -> 기본 쿼리 (근본)
        2. createQuery -> JPA가 제공해주는 객체지향 쿼리
        3. NameQuery -> Query Method는 함수 이름으로 쿼리 생성 - 하지마 !
        4. EntityGraph -> 지금 이해 못 함
     */

    public void save(User user) {
        em.persist(user);
    }

    // 객체지향 쿼리
    public User findByUsername(String username) {
        try {
            return em.createQuery("select u from User u where u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }
}