package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public Board findByIdJoinUserAndReplies(Integer id) {
        Query query = em.createQuery("select b from Board b join fetch b.user left join fetch b.replies r left join fetch r.user where b.id = :id order by r.id desc", Board.class);
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
    }

    public Board findByIdJoinUser(Integer id) {
        Query query = em.createQuery("select b from Board b join fetch b.user where b.id = :id", Board.class);
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
    }

    public Board findById(Integer id) {
        return em.find(Board.class, id);
    }

    // 1. 로그인 안 했을 때 -> 4개 (완료)
    // 2. 로그인 했을 때 -> ssar -> 5개
    // 3. 로그인 했을 때 -> ssar이 아니면 -> 4개
    // 그룹함수 -> Long
    public Long totalCount() {
        Query query = em.createQuery("select count(b) from Board b where b.isPublic = true", Long.class);
        return (Long) query.getSingleResult();
    }

    public Long totalCount(int userId) {
        Query query = em.createQuery("select count(b) from Board b where b.isPublic = true or b.user.id = :userId", Long.class);
        query.setParameter("userId", userId);
        return (Long) query.getSingleResult();
    }

    // localhost:8080?page=0
    public List<Board> findAll(int page) {
        String sql = "select b from Board b where b.isPublic = true order by b.id desc";
        Query query = em.createQuery(sql, Board.class);
        query.setFirstResult(page * 3);
        query.setMaxResults(3);

        return query.getResultList();
    }

    public List<Board> findAll(Integer userId, int page) {
        String sql = "select b from Board b where b.isPublic = true or b.user.id = :userId order by b.id desc";
        Query query = em.createQuery(sql, Board.class);
        query.setParameter("userId", userId);
        query.setFirstResult(page * 3);
        query.setMaxResults(3);
        return query.getResultList();
    }

//    public List<Board> findAll() {
//        String sql = "select b from Board b where b.isPublic = true order by b.id desc";
//        Query query = em.createQuery(sql, Board.class);
//        return query.getResultList();
//    }
//
//    public List<Board> findAll(Integer userId) {
//        String sql = "select b from Board b where b.isPublic = true or b.user.id = :userId order by b.id desc";
//        Query query = em.createQuery(sql, Board.class);
//        query.setParameter("userId", userId);
//        return query.getResultList();
//    }

    public void save(Board board) {
        em.persist(board);
    }

    public void deleteById(Integer id) {
        Board board = em.find(Board.class, id);
        if (board != null) {
            em.remove(board);
        }
    }
}
