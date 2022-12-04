package ru.ilnur.market.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ilnur.market.models.Company;
import ru.ilnur.market.models.Worker;

import java.util.List;

@Component
public class WorkerDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public WorkerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Worker> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select w from Worker w", Worker.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Worker show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Worker.class, id);
    }

    @Transactional
    public void save(Worker worker) {
        Session session = sessionFactory.getCurrentSession();
        session.save(worker);
    }

    @Transactional
    public void update(Worker updatedWorker, int id) {
        Session session = sessionFactory.getCurrentSession();
        Worker personToBeUpdated = session.get(Worker.class, id);

        personToBeUpdated.setName(updatedWorker.getName());
        personToBeUpdated.setAge(updatedWorker.getAge());
        personToBeUpdated.setGender(updatedWorker.getGender());
        personToBeUpdated.setAddress(updatedWorker.getAddress());
        personToBeUpdated.setEducation(updatedWorker.getEducation());
        personToBeUpdated.setSpeciality(updatedWorker.getSpeciality());
        personToBeUpdated.setExperience(updatedWorker.getExperience());
        personToBeUpdated.setEnglish(updatedWorker.getEnglish());
        personToBeUpdated.setProgLang(updatedWorker.getProgLang());
        personToBeUpdated.setCar(updatedWorker.getCar());
        personToBeUpdated.setComputer(updatedWorker.getComputer());
    }

    @Transactional
    public List<Company> search(int id) {
        Session session = sessionFactory.getCurrentSession();

        Worker worker = session.get(Worker.class, id);

        return session.createQuery("select c from Company c " +
                "where c.speciality=:speciality and c.education<=:education " +
                "and c.experience<=:experience and c.english<=:english " +
                "and c.progLang<=:progLang and c.car<=:car " +
                "and c.computer<=:computer", Company.class)
                .setParameter("speciality", worker.getSpeciality())
                .setParameter("education", worker.getEducation())
                .setParameter("experience", worker.getExperience())
                .setParameter("english", worker.getEnglish())
                .setParameter("progLang", worker.getProgLang())
                .setParameter("car", worker.getCar())
                .setParameter("computer", worker.getComputer()).getResultList();
    }

    @Transactional
    public List<Company> searchAll(int id) {
        Session session = sessionFactory.getCurrentSession();

        Worker worker = session.get(Worker.class, id);

        List<Company> companies = session.createQuery("select c from Company c " +
                        "where c.speciality=:speciality and c.education<=:education " +
                        "and c.experience<=:experience and c.english<=:english " +
                        "and c.progLang<=:progLang and c.car<=:car " +
                        "and c.computer<=:computer", Company.class)
                .setParameter("speciality", worker.getSpeciality())
                .setParameter("education", worker.getEducation())
                .setParameter("experience", worker.getExperience())
                .setParameter("english", worker.getEnglish())
                .setParameter("progLang", worker.getProgLang())
                .setParameter("car", worker.getCar())
                .setParameter("computer", worker.getComputer()).getResultList();

        companies.addAll(session.createQuery("select c from Company c " +
                        "where c.speciality<>:speciality and c.education<=:education " +
                        "and c.experience<=:experience and c.english<=:english " +
                        "and c.progLang<=:progLang and c.car<=:car " +
                        "and c.computer<=:computer", Company.class)
                .setParameter("speciality", worker.getSpeciality())
                .setParameter("education", worker.getEducation())
                .setParameter("experience", worker.getExperience())
                .setParameter("english", worker.getEnglish())
                .setParameter("progLang", worker.getProgLang())
                .setParameter("car", worker.getCar())
                .setParameter("computer", worker.getComputer()).getResultList());

        return companies;
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Worker.class, id));
    }
}
