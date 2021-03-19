package net.projectlynx.lynx.storage.database;

import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.database.SimpleFlatDatabase;
import org.mineacademy.fo.debug.LagCatcher;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.projectlynx.lynx.storage.Configuration;
import net.projectlynx.lynx.storage.PlayerData;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LynxDatabase extends SimpleFlatDatabase<PlayerData> {

	@Getter
	private final static LynxDatabase instance = new LynxDatabase();

	@Override
	protected void onLoad(final SerializedMap map, final PlayerData data) {
		data.setBanned(map.getBoolean("Banned", false));
		data.setMuted(map.getBoolean("Muted", false));

		if (map.containsKey("Ban_Date"))
			data.setBanDate(map.getString("Ban_Date"));

		if (map.containsKey("Mute_Date"))
			data.setMuteDate(map.getString("Mute_Date"));
	}

	@Override
	protected SerializedMap onSave(final PlayerData data) {
		final SerializedMap map = new SerializedMap();

		map.put("Banned", data.isBanned());
		map.put("Muted", data.isBanned());
		map.putIf("Ban_Date", data.getBanDate());
		map.putIf("Mute_Date", data.getMuteDate());

		return map;
	}

	public void connectDatabase() {
		final Configuration configuration = Configuration.getInstance();

		LagCatcher.start("mysql");
		LynxDatabase.getInstance().connect(configuration.getHost(), configuration.getPort(), configuration.getDatabase(), configuration.getUser(), configuration.getPassword(), "lynx");
		LagCatcher.end("mysql", 0, "Connection to MySQL established. Took {time}ms.");
	}
}