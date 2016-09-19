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
