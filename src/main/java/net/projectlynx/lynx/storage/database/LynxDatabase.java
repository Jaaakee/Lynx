package net.projectlynx.lynx.storage.database;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.projectlynx.lynx.LynxPlugin;
import net.projectlynx.lynx.storage.Configuration;
import net.projectlynx.lynx.storage.PlayerData;
import org.bukkit.Bukkit;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.database.SimpleFlatDatabase;
import org.mineacademy.fo.debug.LagCatcher;
import sun.security.krb5.Config;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LynxDatabase extends SimpleFlatDatabase<PlayerData> {

    @Getter
    private final static LynxDatabase instance = new LynxDatabase();

    @Override
    protected void onLoad(final SerializedMap map, final PlayerData data) {
        Common.log("Data 1: " + data.toString());
        Common.log("Map 1: " + map.toString());

//        data.setBanned(map.getBoolean("Banned"));
//        data.setMuted(map.getBoolean("Muted"));
//
//        if (data.getBanDate() != null)
//            data.setBanDate(map.getString("Ban_Date"));
//
//        if (data.getMuteDate() != null)
//            data.setMuteDate(map.getString("Mute_Date"));
    }

    @Override
    protected SerializedMap onSave(final PlayerData data) {
        final SerializedMap map = new SerializedMap();
//
    map.put("Banned", data.isBanned());
        Common.log("Data 2: " + data.toString());
        Common.log("Map 2: " + map.toString());
//        map.put("Muted", data.isBanned());
//
//        if (data.getBanDate() != null)
//            map.put("Banned", data.getBanDate());
//
//        if (data.getMuteDate() != null)
//            map.put("Banned", data.isBanned());
//
//        return map;
        return map;
    }

    public void connectDatabase() {
        Configuration configuration = Configuration.getInstance();

        LagCatcher.start("mysql");
        LynxDatabase.getInstance().connect(configuration.getHost(), configuration.getPort(), configuration.getDatabase(), configuration.getUser(), configuration.getPassword(), "lynx");
        LagCatcher.end("mysql", 0, "Connection to MySQL established. Took {time}ms.");
    }
}