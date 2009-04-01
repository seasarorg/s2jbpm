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
package org.seasar.jbpm.sample.action;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.jbpm.sample.entity.Order;
import org.seasar.jbpm.sample.form.OrderForm;
import org.seasar.jbpm.sample.service.OrderService;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

/**
 * 注文アクションクラス.
 * 
 * @author digitalsoul
 * 
 */
public class OrderAction {

    public List<Order> orderItems;

    @ActionForm
    @Resource
    protected OrderForm orderForm;

    @Resource
    protected OrderService orderService;

    /**
     * 初期表示.
     * 
     * @return
     */
    @Execute(validator = false)
    public String index() {
        orderItems = orderService.findAll();
        return "list.jsp";
    }

    /**
     * 詳細情報表示.
     * 
     * @return
     */
    @Execute(validator = false, urlPattern = "show/{id}")
    public String show() {
        Order entity = orderService.findById(Integer.valueOf(orderForm.id));
        Beans.copy(entity, orderForm).execute();
        return "show.jsp";
    }

    /**
     * 修正画面表示.
     * 
     * @return
     */
    @Execute(validator = false, urlPattern = "edit/{id}")
    public String edit() {
        Order entity = orderService.findById(Integer.valueOf(orderForm.id));
        Beans.copy(entity, orderForm).execute();
        return "edit.jsp";
    }

    /**
     * 新規登録画面表示.
     * 
     * @return
     */
    @Execute(validator = false)
    public String create() {
        return "create.jsp";
    }

    /**
     * 削除実行.
     * 
     * @return
     */
    @Execute(validator = false, urlPattern = "delete/{id}", redirect = true)
    public String delete() {
        Order entity = Beans.createAndCopy(Order.class, orderForm).execute();
        orderService.delete(entity);
        return "/order/";
    }

    /**
     * 新規登録.
     * 
     * @return
     */
    @Execute(input = "create.jsp", validator = true, redirect = true)
    public String insert() {
        Order entity = Beans.createAndCopy(Order.class, orderForm).execute();
        orderService.insert(entity);
        return "/order/";
    }

    /**
     * 更新実行.
     * 
     * @return
     */
    @Execute(input = "edit.jsp", redirect = true)
    public String update() {
        Order entity = Beans.createAndCopy(Order.class, orderForm).execute();
        orderService.update(entity);
        return "/order/";
    }
}