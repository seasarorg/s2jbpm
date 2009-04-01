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
package org.seasar.jbpm.sample.taglib;

import org.seasar.jbpm.sample.common.OrderStatus;

/**
 * JSPで使用される関数ライブラリ.
 * 
 * @author digitalsoul
 * 
 */
public class SampleFunctions {

    /**
     * 状態名取得.
     * 
     * 状態コードを状態名に変換します。
     * 
     * @param statusCode
     *            状態コード
     * @return String 状態名
     */
    public static String statusName(Object statusCode) {
        return OrderStatus.getInstance().nameFor(String.valueOf(statusCode));
    }
}
