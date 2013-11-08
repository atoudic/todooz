package fr.todooz.service;

import fr.todooz.domain.Task;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Inject
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(Task task) {
        Session session = sessionFactory.getCurrentSession();

              session.saveOrUpdate(task);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();

        session.createQuery("delete from Task where Id = :id")
                .setLong("id", id)
                .executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findAll() {
        Session session = sessionFactory.getCurrentSession();

        List<Task> tasks = session.createQuery("from Task").list();

        return tasks;
    }

    @Override
     @Transactional(readOnly = true)
     public List<Task> findByQuery(String query) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Task.class);

        criteria.add(Restrictions.ilike("title", query, MatchMode.ANYWHERE));

        List<Task> tasks = criteria.list();

        return tasks;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findByTag(String tag) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Task.class);

        criteria.add(Restrictions.ilike("tags", tag, MatchMode.ANYWHERE));

        List<Task> tasks = criteria.list();

        return tasks;
    }

    @Override
    @Transactional(readOnly = true)
    public Task findById(Long id) {
        Session session = sessionFactory.getCurrentSession();

        return (Task) session.get(Task.class, id);

    }

    @Override
    @Transactional(readOnly = true)
    public int count() {
        Session session = sessionFactory.getCurrentSession();

        int count = ((Long)session.createQuery("select count(*) from Task").uniqueResult()).intValue();

        return count;
    }
}
