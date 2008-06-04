package org.seasar.jbpm.settings;

import org.hibernate.Session;
import org.jbpm.JbpmContext;
import org.seasar.hibernate3.impl.S2SessionFactoryImpl;

/**
 * S2Hibernate の動作確認。
 * 
 * @author glad
 */
public class HibernateTest extends JbpmTest {

    S2SessionFactoryImpl sessionFactory;

    protected void setUp() throws Exception {
        include("s2hibernate3.dicon");
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
