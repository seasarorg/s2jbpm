package org.seasar.jbpm.settings;

import org.hibernate.Session;
import org.jbpm.JbpmContext;
import org.seasar.jbpm.hibernate.jpa.impl.S2SessionFactoryImpl;
import org.seasar.jbpm.impl.S2JbpmConfigurationImpl;

/**
 * JPA の設定で S2Hibernate の動作確認。
 * 
 * @author glad
 */
public class HibernateJpaTest extends JbpmTest {

    S2JbpmConfigurationImpl jbpmConfiguration;

    S2SessionFactoryImpl sessionFactory;

    protected void setUp() throws Exception {
        include("s2jbpm-hibernate-jpa.dicon");
    }

    protected JbpmContext createJbpmContext() {
        return jbpmConfiguration.createJbpmContext();
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
