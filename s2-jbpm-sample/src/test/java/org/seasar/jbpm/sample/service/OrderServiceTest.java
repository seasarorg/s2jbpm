package org.seasar.jbpm.sample.service;

import org.seasar.extension.unit.S2TestCase;

/**
 * {@link OrderService}のテストクラスです。
 * 
 * @author S2JDBC-Gen
 */
public class OrderServiceTest extends S2TestCase {

    private OrderService orderService;

    /**
     * 事前処理をします。
     * 
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include("app.dicon");
    }

    /**
     * {@link #orderService}が利用可能であることをテストします。
     * 
     * @throws Exception
     */
    public void testAvailable() throws Exception {
        assertNotNull(orderService);
    }
}