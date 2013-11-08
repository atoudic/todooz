package fr.todooz.service;


import fr.todooz.domain.Task;
import fr.todooz.util.TagCloud;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class TagCloudServiceImpl implements TagCloudService {
    @Inject
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public TagCloud buildTagCloud() {
        Session session = sessionFactory.getCurrentSession();

        TagCloud tagCloud = new TagCloud();

        List<String> tags = session.createQuery("select tags from Task").list();

        for(String items : tags){
            tagCloud.add(StringUtils.split(items, ","));
        }

        return tagCloud;
    }

    private List<String> findTags() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select tags from Task").list();
    }
}
