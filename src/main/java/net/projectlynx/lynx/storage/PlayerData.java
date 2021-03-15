package net.projectlynx.lynx.storage;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.mineacademy.fo.settings.YamlSectionConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class PlayerData extends YamlSectionConfig {

    private static final Map<UUID, PlayerData> cacheMap = new HashMap<>();

    private final UUID uuid;
    private boolean isBanned, isMuted;
    private String banDate, muteDate;

    protected PlayerData(final UUID uuid) {
        super(uuid.toString());

        this.uuid = uuid;
        loadConfiguration(null, "data.db");
    }

    public static PlayerData getCache(final Player player) {
        return getCache(player.getUniqueId());
    }

    public static PlayerData getCache(UUID uuid) {
        PlayerData data = cacheMap.get(uuid);

        if (data == null) {
            data = new PlayerData(uuid);

            cacheMap.put(uuid, data);
        }

        return data;
    }

    @Override
    protected void onLoadFinish() {

        if (isSet("Banned"))
            isBanned = getBoolean("Banned", false);

        if (isSet("Banned"))
            isMuted = getBoolean("Muted", false);

        if (isSet("Banned"))
            banDate = getString("Ban_Date", "N/A");

        if (isSet("Banned"))
            muteDate = getString("Mute_Date", "N/A");
    }

    public void setBanned(final boolean isBanned) {
        this.isBanned = isBanned;

        save("Banned", isBanned);
    }

    public void setMuted(final boolean isMuted) {
        this.isMuted = isMuted;

        save("Muted", isMuted);
    }

    // --------------------------------------------------------------------------------------------------------------
    // Static methods below
    // --------------------------------------------------------------------------------------------------------------

    public void setBanDate(final String banDate) {
        this.banDate = banDate;

        save("Ban_Date", banDate);
    }

    public void setMuteDate(final String muteDate) {
        this.muteDate = muteDate;

        save("Mute_Date", muteDate);
    }
}