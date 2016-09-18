/**
 * Copyright 2016 Kamran Zafar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.kamranzafar.cas.client.infinispan;

import org.infinispan.Cache;
import org.jasig.cas.client.proxy.ProxyGrantingTicketStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kamran Zafar (N000989)
 *         Created on 18/09/2016
 */
public class InfinispanProxyGrantingTicketStorage implements ProxyGrantingTicketStorage {
    private static final Logger logger = LoggerFactory.getLogger(InfinispanProxyGrantingTicketStorage.class);

    private final Cache cache;

    public InfinispanProxyGrantingTicketStorage(Cache cache) {
        this.cache = cache;
    }

    public void save(String proxyGrantingTicketIou, String proxyGrantingTicket) {
        logger.info("Creating new CAS Proxy granting ticket: [" + proxyGrantingTicketIou
                + ", " + proxyGrantingTicket + "]");

        cache.put(proxyGrantingTicketIou, proxyGrantingTicket);
    }

    public String retrieve(String proxyGrantingTicketIou) {
        return (String) cache.get(proxyGrantingTicketIou);
    }

    public void cleanUp() {
    }
}
