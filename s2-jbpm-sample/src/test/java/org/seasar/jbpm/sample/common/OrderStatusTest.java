package org.seasar.jbpm.sample.common;

import junit.framework.Assert;

import org.junit.Test;
import org.seasar.extension.unit.S2TestCase;

public class OrderStatusTest extends S2TestCase {

    // プロパティ定義ファイル
    private static String PROPERTIES_DICON = "app.dicon";

    @Override
    protected void setUp() throws Exception {
        include(PROPERTIES_DICON);
    }

    /** 注文状態オブジェクト */
    private OrderStatus order_status;

    /**
     * 注文状態オブジェクトの動作検証
     */
    @Test
    public void testGeneral() {

        Assert.assertEquals("コード名称取得確認", "仮登録", order_status.nameFor("0"));
        Assert.assertEquals("コード名称取得確認", "１次承認待ち", order_status.nameFor("1"));
        Assert.assertEquals("コード名称取得確認", "２次承認待ち", order_status.nameFor("2"));
        Assert.assertEquals("コード名称取得確認", "注文確定", order_status.nameFor("3"));

    }

}
