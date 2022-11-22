package ru.ilnur.market.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ilnur.market.models.Company;
import ru.ilnur.market.models.Worker;

import java.util.List;

@Component
public class CompaniesDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public CompaniesDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Company> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select c from Company c", Company.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Company show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Company.class, id);
    }

    @Transactional
    public void save(Company company) {
        Session session = sessionFactory.getCurrentSession();
        session.save(company);
    }
}
