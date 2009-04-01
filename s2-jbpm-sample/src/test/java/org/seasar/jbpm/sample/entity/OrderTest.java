package org.seasar.jbpm.sample.entity;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

/**
 * {@link Order}のテストクラスです。
 * 
 * @author S2JDBC-Gen
 */
public class OrderTest extends S2TestCase {

    private JdbcManager jdbcManager;

    /**
     * 事前処理をします。
     * 
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include("s2jdbc.dicon");
    }

    /**
     * 識別子による取得をテストします。
     * 
     * @throws Exception
     */
    public void testFindById() throws Exception {
        jdbcManager.from(Order.class).id(1).getSingleResult();
    }
}