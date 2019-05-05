package ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

public class MainApp {

    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.igniteInstance(app.igniteConfiguration());
        System.out.println("hello world");
    }
    @Bean
    public IgniteConfiguration igniteConfiguration() {
        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        igniteConfiguration.setPeerClassLoadingEnabled(false);

        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setName("session-cache");
        cacheConfiguration.setCacheMode(CacheMode.REPLICATED);

        igniteConfiguration.setCacheConfiguration(cacheConfiguration);

        return igniteConfiguration;
    }

    @Bean("igniteInstance")
    public Ignite igniteInstance(IgniteConfiguration igniteConfiguration) {
        Ignite start = Ignition.start(igniteConfiguration);
        start.active(true);
        return start;
    }
}

