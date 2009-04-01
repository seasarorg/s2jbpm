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
package org.seasar.jbpm.sample.service;

import java.util.List;

import org.seasar.jbpm.sample.entity.Order;

/**
 * 注文エンティティのサービス.
 * 
 * @author digitalsoul
 * 
 */
public class OrderService extends AbstractService<Order> {

    /**
     * 一件検索.
     * 
     * idによる一件検索.
     * 
     * @param id
     *            ID
     * @return Order 注文エンティティ
     */
    public Order findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 全件取得.
     * 
     * @return List<Order> 検索結果一覧
     */
    @Override
    public List<Order> findAll() {
        return select().orderBy("ID").getResultList();
    }

    /**
     * ステータスコードによる一覧取得
     * 
     * @param statusCode
     * @return
     */
    public List<Order> findByStatus(String statusCode) {

        // １次承認待ち項目一覧取得
        List<Order> orderItems = select().where("status = ?", statusCode)
                .orderBy("id").getResultList();
        return orderItems;
    }

}