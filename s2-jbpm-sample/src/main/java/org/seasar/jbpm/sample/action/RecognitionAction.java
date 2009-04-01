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
 * 承認アクション.
 * 
 * @author digitalsoul
 * 
 */
public class RecognitionAction {

    /** １次承認待ち商品一覧 */
    public List<Order> orderItems;

    @ActionForm
    @Resource
    protected OrderForm orderForm;

    @Resource
    protected OrderService orderService;

    /**
     * 初期表示.
     * 
     * @return String 一覧表示画面
     */
    @Execute(validator = false)
    public String index() {

        orderItems = orderService.findByStatus("1");

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
     * １次承認実行.
     * 
     * @return 一覧表示へチェーン
     */
    @Execute(validator = false, urlPattern = "recognize/{id}")
    public String recognize() {

        // 変更対象エンティティ取得
        Order entity = orderService.findById(Integer.valueOf(orderForm.id));

        // 状態変更
        entity.status = "2";
        orderService.update(entity);

        return "/recognition/";
    }
}
