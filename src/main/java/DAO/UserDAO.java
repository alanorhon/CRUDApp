package DAO;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDAO {
    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }


    public List<User> getAllUsers() {
        Query query = session.createQuery("FROM User");
        return (List<User>) query.list();
    }

    public void addUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    public User getUserById(Long id){
        Query query = session.createQuery("FROM User where id=:param");
        query.setParameter("param", id);
        List<User> user = query.list();
        return user.get(0);
    }

    public void deleteUser(Long id) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User WHERE id = :id").setLong("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public void editUser(User user) {
        Query query = session.createQuery("update User "
                + "SET login =:login "
                + ", password  =:password "
                + ", email      =:email ");
        query.setParameter("login", user.getLogin());
        query.setParameter("password", user.getPassword());
        query.setParameter("email", user.getEmail());
    }
}
