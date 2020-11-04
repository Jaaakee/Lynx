package net.projectlynx.lynx.storage;

import lombok.Getter;
import org.mineacademy.fo.settings.YamlConfig;

@Getter
public class Configuration extends YamlConfig {

    @Getter
    private final static Configuration instance = new Configuration();

    private String host, database, user, password;
    private int port;

    public Configuration() {
        loadConfiguration("config.yml");
    }

    @Override
    protected void onLoadFinish() {
        host = getString("mysql.host");
        port = getInteger("mysql.port");

        database = getString("mysql.database");
        user = getString("mysql.user");
        password = getString("mysql.password");
    }
}
