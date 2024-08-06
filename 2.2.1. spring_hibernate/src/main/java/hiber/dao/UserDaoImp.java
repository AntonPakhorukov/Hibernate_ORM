package hiber.dao;

import hiber.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserOnCarModelAndSeries(String model, int series) {
        User user = null;
        try {
            Query<User> query = sessionFactory.getCurrentSession()
                    .createQuery("from User u where u.car.model = :model and u.car.series = :series", User.class);
            query.setParameter("model", model);
            query.setParameter("series", series);
            user = query.getSingleResult();
        } catch (Exception e) {
        }
        return user;
    }
}
