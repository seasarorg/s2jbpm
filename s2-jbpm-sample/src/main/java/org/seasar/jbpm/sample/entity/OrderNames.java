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
package org.seasar.jbpm.sample.entity;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Order}のプロパティ名の集合です。
 * 
 * @author S2JDBC-Gen
 */
public class OrderNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * itemのプロパティ名を返します。
     * 
     * @return itemのプロパティ名
     */
    public static PropertyName<String> item() {
        return new PropertyName<String>("item");
    }

    /**
     * priceのプロパティ名を返します。
     * 
     * @return priceのプロパティ名
     */
    public static PropertyName<Integer> price() {
        return new PropertyName<Integer>("price");
    }

    /**
     * contactのプロパティ名を返します。
     * 
     * @return contactのプロパティ名
     */
    public static PropertyName<String> contact() {
        return new PropertyName<String>("contact");
    }

    /**
     * statusのプロパティ名を返します。
     * 
     * @return statusのプロパティ名
     */
    public static PropertyName<String> status() {
        return new PropertyName<String>("status");
    }

    /**
     * noteのプロパティ名を返します。
     * 
     * @return noteのプロパティ名
     */
    public static PropertyName<String> note() {
        return new PropertyName<String>("note");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _OrderNames extends PropertyName<Order> {

        /**
         * インスタンスを構築します。
         */
        public _OrderNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _OrderNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * 
         * @param parent
         *            親
         * @param name
         *            名前
         */
        public _OrderNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * idのプロパティ名を返します。
         *
         * @return idのプロパティ名
         */
        public PropertyName<Integer> id() {
            return new PropertyName<Integer>(this, "id");
        }

        /**
         * itemのプロパティ名を返します。
         *
         * @return itemのプロパティ名
         */
        public PropertyName<String> item() {
            return new PropertyName<String>(this, "item");
        }

        /**
         * priceのプロパティ名を返します。
         *
         * @return priceのプロパティ名
         */
        public PropertyName<Integer> price() {
            return new PropertyName<Integer>(this, "price");
        }

        /**
         * contactのプロパティ名を返します。
         *
         * @return contactのプロパティ名
         */
        public PropertyName<String> contact() {
            return new PropertyName<String>(this, "contact");
        }

        /**
         * statusのプロパティ名を返します。
         *
         * @return statusのプロパティ名
         */
        public PropertyName<String> status() {
            return new PropertyName<String>(this, "status");
        }

        /**
         * noteのプロパティ名を返します。
         *
         * @return noteのプロパティ名
         */
        public PropertyName<String> note() {
            return new PropertyName<String>(this, "note");
        }
    }
}