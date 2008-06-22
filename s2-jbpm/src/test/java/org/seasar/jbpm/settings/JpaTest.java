package org.seasar.jbpm.settings;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.jbpm.JbpmContext;
import org.seasar.jbpm.impl.S2JbpmConfigurationImpl;

/**
 * S2Hibernate-JPA の動作確認。
 * 
 * @author glad
 */
public class JpaTest extends JbpmTest {

    S2JbpmConfigurationImpl jbpmConfiguration;

    EntityManager entityManager;

    protected void setUp() throws Exception {
        include("s2jbpm-jpa.dicon");
    }

    protected JbpmContext createJbpmContext() {
        return jbpmConfiguration.createJbpmContext();
    }

    protected Session getSession() {
        return (Session) entityManager.getDelegate();
    }

    <T> T load(Class<T> clazz, Serializable id) {
        return entityManager.getReference(clazz, id);
    }

    public void testJpaTx() {
        assertNotNull(entityManager);
    }

    public void testJbpmContextTx() {
        super.testJbpmContextTx();
    }

}
