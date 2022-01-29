package com.github.elenaAeternaNox.mobile_testing.configs;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:mobile.properties"})
public interface MobileConfig extends Config {
    String user();

    String key();

    String url();
}
