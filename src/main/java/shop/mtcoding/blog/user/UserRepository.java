package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public User findById(int id) {
        return em.find(User.class, id);
    }

    /*
        1. createNativeQuery -> 기본 쿼리 (근본)
        2. createQuery -> JPA가 제공해주는 객체지향 쿼리
        3. NameQuery -> Query Method는 함수 이름으로 쿼리 생성 - 하지마 !
        4. EntityGraph -> 지금 이해 못 함
        JPQL 알아서 방언으로 바꿔줌 (마이그레이션 안 해도 됨)
     */

    public void save(User user) {
        em.persist(user); // 2. user는 이제 영속 객체
        // 3. user는 데이터베이스와 동기화 됨
    }

    // 객체지향 쿼리
    public User findByUsername(String username) {
        return em.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter(username, username)
                .getSingleResult();
    }
}