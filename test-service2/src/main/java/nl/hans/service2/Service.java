package nl.hans.service2;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class Service {

  @PersistenceContext(unitName = "TestPersistence2")
  EntityManager em;

  public <T> void persist(T foo) {
    em.persist(foo);
  }

  public <T> T find(Class<T> clazz, String id) {
    return em.find(clazz, id);
  }
}
