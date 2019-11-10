package com.gd.save_ads.service;

import com.gd.model.entity.Ads;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class HibernateSearchService {
    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<Ads> searchByTitle(String searchTerm) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Ads.class).get();
        Query luceneQuery = queryBuilder
                .keyword()
                .onField("title")
                .matching(searchTerm)
                .createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Ads.class);

        List<Ads> BaseballCardList = null;
        try {
            BaseballCardList = jpaQuery.getResultList();
        } catch (NoResultException ignored) {
        }

        return BaseballCardList;
    }
    @SuppressWarnings("unchecked")
    public List<Ads> searchByLocation(String searchTerm) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Ads.class).get();
        Query luceneQuery = queryBuilder
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(2)
                .onFields("location")
                .matching(searchTerm)
                .createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Ads.class);

        List<Ads> BaseballCardList = null;
        try {
            BaseballCardList = jpaQuery.getResultList();
        } catch (NoResultException ignored) {
        }

        return BaseballCardList;
    }
    @SuppressWarnings("unchecked")
    public List<Ads> searchByParam(String title, String location) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Ads.class).get();
        Query luceneQuery = queryBuilder
                .bool()
                .must(queryBuilder.keyword()
                        .onField("title").matching(title)
                        .createQuery())
                .should(queryBuilder.keyword()
                        .onField("location").matching(location)
                        .createQuery())
                .createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Ads.class);

        List<Ads> BaseballCardList = null;
        try {
            BaseballCardList = jpaQuery.getResultList();
        } catch (NoResultException ignored) {
        }

        return BaseballCardList;
    }
}
