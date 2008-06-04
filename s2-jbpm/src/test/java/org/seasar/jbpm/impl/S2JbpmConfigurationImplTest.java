package org.seasar.jbpm.impl;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.hibernate3.S2SessionFactory;
import org.seasar.jbpm.S2JbpmContext;

public class S2JbpmConfigurationImplTest extends S2TestCase {

    S2JbpmConfigurationImpl jbpmConfiguration;

    S2SessionFactory sessionFactory;

    protected void setUp() throws Exception {
        include("s2jbpm.dicon");
    }

    public void testResourceName() {
        jbpmConfiguration = new S2JbpmConfigurationImpl();
        assertNull(jbpmConfiguration.getResourceName());
        jbpmConfiguration.setResourceName("s2jbpm.cfg.xml");
        assertEquals("s2jbpm.cfg.xml", jbpmConfiguration.getResourceName());
    }

    public void testJbpmConfiguration() {
        jbpmConfiguration = new S2JbpmConfigurationImpl();
        assertNull(jbpmConfiguration.jbpmConfiguration);
        
        JbpmConfiguration singleton = JbpmConfiguration.getInstance();
        assertSame(singleton, jbpmConfiguration.getJbpmConfiguration());
        assertSame(singleton, jbpmConfiguration.jbpmConfiguration);
        
        jbpmConfiguration.setJbpmConfiguration(null);
        assertNull(jbpmConfiguration.jbpmConfiguration);

        jbpmConfiguration.setJbpmConfiguration(singleton);
        assertSame(singleton, jbpmConfiguration.jbpmConfiguration);
        assertSame(singleton, jbpmConfiguration.getJbpmConfiguration());
        assertSame(singleton, jbpmConfiguration.jbpmConfiguration);
    }

    public void testSessionFactory() {
        jbpmConfiguration = new S2JbpmConfigurationImpl();
        assertNull(jbpmConfiguration.getSessionFactory());
        assertNotNull(sessionFactory);
        jbpmConfiguration.setSessionFactory(sessionFactory);
        assertSame(sessionFactory, jbpmConfiguration.getSessionFactory());
    }

    public void testCreateJbpmContextTx() {
        JbpmContext jbpmContext = jbpmConfiguration.createJbpmContext();
        try {
            assertSame(sessionFactory.getSession(), jbpmContext.getSession());
        } finally {
            jbpmContext.close();
        }
    }

    public void testCreateS2JbpmContextTx() {
        S2JbpmContext jbpmContext = jbpmConfiguration.createS2JbpmContext();
        try {
            assertSame(sessionFactory.getSession(),
                    jbpmContext.getJbpmContext().getSession());
        } finally {
            jbpmContext.close();
        }
    }

}
