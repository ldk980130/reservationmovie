package springpractice.reservationmovie.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springpractice.reservationmovie.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public void delete(Member member) {
        em.remove(member);
    }

    public Member findById(String id) {
        Member member = em.find(Member.class, id);
        return member;
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
