package DAO;

import Interfaces.UserDAOInterface;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserHibernateDAO implements UserDAOInterface {
    private static UserHibernateDAO instance;
    private static SessionFactory sessionFactory;

    private UserHibernateDAO(SessionFactory daoSessionFactory) {
        sessionFactory=daoSessionFactory;
    }

    public static UserHibernateDAO getInstance(SessionFactory sessionFactory){
        if(instance==null){

            instance = new UserHibernateDAO(sessionFactory);
        }
        return instance;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User");
        return (List<User>) query.list();
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    @Override
    public User getUserById(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User where id=:param");
        query.setParameter("param", id);
        List<User> user = query.list();
        return user.get(0);
    }

    @Override
    public void deleteUser(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User WHERE id = :id").setLong("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void editUser(User user) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("update User "
                + "SET login =:login "
                + ", password  =:password "
                + ", email      =:email ");
        query.setParameter("login", user.getLogin());
        query.setParameter("password", user.getPassword());
        query.setParameter("email", user.getEmail());
    }
}
