package org.seasar.jbpm.settings;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.jbpm.JbpmContext;

/**
 * S2Hibernate-JPA の動作確認。
 * 
 * @author glad
 */
public class JpaTest extends JbpmTest {

    EntityManager entityManager;

    protected void setUp() throws Exception {
        include("jpa.dicon");
    }

    protected JbpmContext createJbpmContext() {
        JbpmContext jbpmContext = super.createJbpmContext();
        jbpmContext.setSession(getSession());
        return jbpmContext;
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
