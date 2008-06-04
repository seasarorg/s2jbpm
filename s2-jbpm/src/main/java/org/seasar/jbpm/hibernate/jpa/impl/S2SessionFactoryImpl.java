/*
 * Copyright 2008 the Seasar Foundation and the Others.
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
package org.seasar.jbpm.hibernate.jpa.impl;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.seasar.hibernate3.S2SessionFactory;

/**
 * @author glad
 */
public class S2SessionFactoryImpl implements S2SessionFactory {

    final EntityManager entityManager;

    public S2SessionFactoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public String getConfigPath() {
        throw new UnsupportedOperationException();
    }

    public Session getSession() {
        return (Session) entityManager.getDelegate();
    }

}
