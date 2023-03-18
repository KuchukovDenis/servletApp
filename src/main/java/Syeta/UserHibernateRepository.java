package Syeta;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserHibernateRepository  {
    private static UserHibernateRepository repository;

    public static UserHibernateRepository getRepository() {
        if (repository == null) {
            repository = new UserHibernateRepository();
        }

        return repository;
    }

    private UserHibernateRepository() {
    }


    public void save(People user) {
        Session session = MySessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }


    public People find(Integer id) {
        return (People) MySessionFactory.getSessionFactory().openSession().get(People.class, id);
    }


    public People find(String login) {
        Session session = MySessionFactory.getSessionFactory().openSession();
        People user = (People) session.byNaturalId(People.class).using("login", login).load();
        session.close();
        return user;
    }
}