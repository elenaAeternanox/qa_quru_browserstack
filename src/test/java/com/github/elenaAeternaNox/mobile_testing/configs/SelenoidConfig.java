package com.github.elenaAeternaNox.mobile_testing.configs;

@org.aeonbits.owner.Config.Sources({"classpath:selenoid.properties"})
public interface SelenoidConfig extends org.aeonbits.owner.Config {
    String selenoidUser();

    String selenoidPassword();
}