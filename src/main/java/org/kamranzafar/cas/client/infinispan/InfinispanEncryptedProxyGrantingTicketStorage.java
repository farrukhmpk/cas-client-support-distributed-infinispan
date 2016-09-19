/**
 * Copyright 2016 Kamran Zafar
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kamranzafar.cas.client.infinispan;

import org.infinispan.Cache;
import org.jasig.cas.client.proxy.AbstractEncryptedProxyGrantingTicketStorageImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kamran Zafar
 *         Created on 19/09/2016
 */
public class InfinispanEncryptedProxyGrantingTicketStorage extends AbstractEncryptedProxyGrantingTicketStorageImpl {
    private static final Logger logger = LoggerFactory.getLogger(InfinispanEncryptedProxyGrantingTicketStorage.class);

    private final Cache cache;

    public InfinispanEncryptedProxyGrantingTicketStorage(Cache cache) {
        this.cache = cache;
    }

    /**
     * Store the proxy granting ticket to cache
     *
     * @param proxyGrantingTicketIou
     * @param proxyGrantingTicket
     */
    protected void saveInternal(String proxyGrantingTicketIou, String proxyGrantingTicket) {
        logger.info("Creating new CAS Proxy granting ticket: [" + proxyGrantingTicketIou
                + ", " + proxyGrantingTicket + "]");

        cache.put(proxyGrantingTicketIou, proxyGrantingTicket);
    }

    /**
     * Retrieve proxy granting ticket
     *
     * @param proxyGrantingTicketIou
     * @return
     */
    protected String retrieveInternal(String proxyGrantingTicketIou) {
        return (String) cache.get(proxyGrantingTicketIou);
    }

    /**
     * Not implemented
     */
    public void cleanUp() {

    }
}
