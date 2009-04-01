/*
 * Copyright 2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.jbpm.sample.common;

import java.util.Map;

import org.seasar.framework.container.SingletonS2Container;

/**
 * 注文状態オブジェクト.
 * 
 * @author digitalsoul
 * 
 */
public class OrderStatus {

    /** プロパティへの参照：S2コンテナにより設定される */
    public Map<String, String> status;

    /**
     * インスタンス構築. 
     * ※S2Containerによって使用される。
     */
    public OrderStatus() {

    }

    /**
     * ファクトリメソッド.
     * 
     * @return OrderStatus 注文状態オブジェクト
     */
    public static OrderStatus getInstance() {
        return SingletonS2Container.getComponent(OrderStatus.class);
    }

    /**
     * 注文状態名称取得.
     * 
     * @param statusCode
     *            注文状態値
     * @return String 注文状態名称
     */
    public String nameFor(String statusCode) {
        return status.get(statusCode);
    }

}
