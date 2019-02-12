package souldrake.filmmanager.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import souldrake.filmmanager.model.Film;

import java.util.List;
import java.util.Random;

@Repository
public class FilmDAOImpl implements FilmDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Film> allFilms(int page,  String titleSearch, String yearSearch, String genreSearch, String countrySearch) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Film where 1 = 1 and " +
                "(:titleSearch is null or lower(title) like lower(:titleSearch)) and " +
                "(:yearSearch is null or lower(year) like lower(:yearSearch)) and " +
                "(:genreSearch is null or lower(genre) like lower(:genreSearch)) and " +
                "(:countrySearch is null or lower(country) like lower(:countrySearch))");
        query.setParameter("titleSearch", "%" + titleSearch + "%");
        query.setParameter("yearSearch", "%" + yearSearch + "%");
        query.setParameter("genreSearch", "%" + genreSearch + "%");
        query.setParameter("countrySearch", "%" + countrySearch + "%");
        return query.setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    public void add(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(film);
    }

    @Override
    public void delete(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(film);
    }

    @Override
    public void edit(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.update(film);
    }

    @Override
    public Film getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Film.class, id);
    }

    @Override
    public Film getRandomFilm() {
        Session session = sessionFactory.getCurrentSession();
        int maxId = session.createQuery("select max(id) from Film", Number.class)
                .getSingleResult().intValue();
        return (Film) session.createQuery("from Film").setFirstResult(new Random().nextInt(maxId))
                .setMaxResults(1).getSingleResult();
    }

    @Override
    public int filmsCount(String titleSearch, String yearSearch, String genreSearch, String countrySearch) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Film where 1 = 1 and " +
                "(:titleSearch is null or lower(title) like lower(:titleSearch)) and " +
                "(:yearSearch is null or lower(year) like lower(:yearSearch)) and " +
                "(:genreSearch is null or lower(genre) like lower(:genreSearch)) and " +
                "(:countrySearch is null or lower(country) like lower(:countrySearch))", Number.class)
                .setParameter("titleSearch", "%" + titleSearch + "%")
                .setParameter("yearSearch", "%" + yearSearch + "%")
                .setParameter("genreSearch", "%" + genreSearch + "%")
                .setParameter("countrySearch", "%" + countrySearch + "%")
                .getSingleResult().intValue();
    }

    @Override
    public int allFilmsCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Film", Number.class).getSingleResult().intValue();
    }

    @Override
    public boolean isUnique(String title, short year) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Film where title = :title and year = :year")
                .setParameter("title", title)
                .setParameter("year", year)
                .list().isEmpty();
    }
}