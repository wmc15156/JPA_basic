package day01;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();


        try {
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getName() = " + findMember.getName());
//            System.out.println("findMember.getId() = " + findMember.getId());

            List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();

            System.out.println("resultList = " + resultList);
            for(Member mem : resultList) {
                System.out.println("mem = " + mem.getName());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
