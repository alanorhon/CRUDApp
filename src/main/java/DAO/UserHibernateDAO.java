package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.DBHelper;


import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private SessionFactory sessionFactory;

    public UserHibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = DBHelper.getSessionFactory();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList;
        try (Session session = sessionFactory.openSession()) {
            userList = (List<User>) session.createQuery("FROM User").list();
        }
        return userList;
    }

    @Override
    public void addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }
    @Override
    public boolean validateUser(String login, String password) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User WHERE login=:loginParam AND password=:passParam")
            .setParameter("loginParam", login)
            .setParameter("passParam", password) != null;

        }
    }

    @Override
    public User getUserByLogin(String login) {
        User user;
        try (Session session = sessionFactory.openSession()) {
            user = (User) session.createQuery("FROM User WHERE login=:login")
            .setParameter("login", login).uniqueResult();
        }
        return user;
    }

    @Override
    public User getUserById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    @Override
    public void deleteUser(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(getUserById(id));
            transaction.commit();
        }
    }

    @Override
    public void editUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
    }
}
