package ConfigTest;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/CredentialsTestData.properties") //это путь для секретного файла с данными
public interface CredentialsConfig extends Config {
    String login();
    String password();
    String testUrl();
}
