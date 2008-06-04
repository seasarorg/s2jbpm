package org.seasar.jbpm.settings;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.jbpm.JbpmContext;
import org.seasar.jbpm.hibernate.jpa.impl.S2SessionFactoryImpl;

/**
 * JPA の設定で S2Hibernate の動作確認。
 * 
 * @author glad
 */
public class HibernateJpaTest extends JbpmTest {

    S2SessionFactoryImpl sessionFactory;

    protected void setUp() throws Exception {
        include("jpa.dicon");
        sessionFactory = new S2SessionFactoryImpl(
                (EntityManager) getComponent(EntityManager.class));
    }

    protected JbpmContext createJbpmContext() {
        JbpmContext jbpmContext = super.createJbpmContext();
        jbpmContext.setSession(getSession());
        return jbpmContext;
    }

    protected Session getSession() {
        return sessionFactory.getSession();
    }

    public void testHibernateTx() {
        Session session = getSession();
        assertNotNull(session);
    }

    public void testJbpmContextTx() {
        super.testJbpmContextTx();
    }

}
