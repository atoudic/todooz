package fr.todooz.service;

import fr.todooz.domain.Task;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

public class TaskService {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Task task) {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(task);

        transaction.commit();

        session.close();

    }

    public void delete(Long id) {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.createQuery("delete from Task where Id = :id")
                .setLong("id", id)
                .executeUpdate();

        transaction.commit();

        session.close();

    }

    public List<Task> findAll() {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        List<Task> tasks = session.createQuery("from Task").list();

        transaction.commit();

        session.close();

        return tasks;
    }

    public List<Task> findByQuery(String query) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Task.class);

        criteria.add(Restrictions.ilike("title", query, MatchMode.ANYWHERE));

        List<Task> tasks = criteria.list();

        session.close();

        return tasks;
    }

    public int count() {
        Session session = sessionFactory.openSession();

        int count = ((Long)session.createQuery("select count(*) from Task").uniqueResult()).intValue();

        session.close();

        return count;
    }
}
