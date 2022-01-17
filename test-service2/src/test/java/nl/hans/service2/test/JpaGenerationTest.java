package nl.hans.service2.test;

import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.SingleInstancePostgresRule;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.junit.ClassRule;
import org.junit.Test;

import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class JpaGenerationTest {

    @ClassRule
    public static SingleInstancePostgresRule pgRule = EmbeddedPostgresRules.singleInstance();

    @Test
    public void generateJpaSchema() {
      Persistence.createEntityManagerFactory("it", getProperties());
    }

  public Map<String, String> getProperties() {
    Map<String, String> properties = new HashMap<>();

    properties.put(PersistenceUnitProperties.JDBC_URL, "jdbc:postgresql://localhost:" + pgRule.getEmbeddedPostgres().getPort() + "/postgres");
    properties.put(PersistenceUnitProperties.JDBC_DRIVER, "org.postgresql.Driver");
    properties.put(PersistenceUnitProperties.JDBC_USER, "postgres");
    properties.put(PersistenceUnitProperties.JDBC_PASSWORD, "postgres");
    properties.put(PersistenceUnitProperties.SCHEMA_GENERATION_DATABASE_ACTION,PersistenceUnitProperties.SCHEMA_GENERATION_CREATE_ACTION);

    return properties;
  }

}
