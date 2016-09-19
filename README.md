# CAS Client and distributed Infinispan Integration
This library provides integration with distributed Infinispan cache.

## Building

Checkout the code and use  the following maven command to build the project

<pre><code>mvn clean install</code></pre>

## Usage

Below is an example on how to use this library on a Spring Boot application on Wildfly/JBoss

### Dependency

<pre><code>&lt;dependency&gt;
    &lt;groupId&gt;org.kamranzafar.cas.client&lt;/groupId&gt;
    &lt;artifactId&gt;cas-client-support-distributed-infinispan&lt;/artifactId&gt;
    &lt;version&gt;1.0&lt;/version&gt;
&lt;/dependency&gt;
</code></pre>

### Example

<pre><code>// Lookup the relevant Cache that will be used to store CAS proxy granting tickets
@Bean
public Cache defaultCache() throws NamingException {
    return (Cache) ((DefaultCacheContainer) new JndiTemplate().lookup("java:jboss/infinispan/container/sso")).getCache();
}
.
.
.
// Create the bean
@Bean
public ProxyGrantingTicketStorage proxyGrantingTicketStorage(){
    return new InfinispanProxyGrantingTicketStorage(defaultCache());
}
.
.
.
// Setup CasAuthenticationFilter to use Infinispan
CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
casAuthenticationFilter.setProxyGrantingTicketStorage(proxyGrantingTicketStorage());
.
.
// Setup Cas20ProxyTicketValidator to use Infinispan
Cas20ProxyTicketValidator cas20ProxyTicketValidator = new Cas20ProxyTicketValidator("...");
cas20ProxyTicketValidator.setProxyGrantingTicketStorage(proxyGrantingTicketStorage());
</code></pre>