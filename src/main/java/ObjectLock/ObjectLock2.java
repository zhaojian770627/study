package ObjectLock;

import java.util.concurrent.ConcurrentHashMap;

public class ObjectLock2 {
	private static ConcurrentHashMap<String, Object> lockMap = new ConcurrentHashMap<>();

	public boolean lock(String key) {
		if (lockMap.putIfAbsent(key, key) == null) {
			return true;
		}
		return false;
	}

	public boolean unlock(String key) {
		if (lockMap.get(key) == null)
			throw new UnsupportedOperationException();

		lockMap.remove(key);
		return true;
	}
}
