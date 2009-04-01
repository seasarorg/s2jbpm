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
package org.seasar.jbpm.sample.form;

import org.seasar.struts.annotation.IntRange;
import org.seasar.struts.annotation.IntegerType;
import org.seasar.struts.annotation.Required;

/**
 * 注文フォーム.
 * 
 * @author digitalsoul
 *
 */
public class OrderForm {

    /** id */
    public String id;

    /** 商品名 */
    @Required
    public String item;

    /** 金額 */
    @Required
    @IntegerType
    public String price;

    /** 担当者 */
    @Required
    public String contact;

    /** 状態 */
    @Required
    @IntRange(min = 0, max = 2)
    public String status;

    /** 備考 */
    public String note;

}