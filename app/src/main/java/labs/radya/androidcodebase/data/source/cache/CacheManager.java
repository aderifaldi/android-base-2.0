package labs.radya.androidcodebase.data.source.cache;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import labs.radya.androidcodebase.data.AppDatabase;
import labs.radya.androidcodebase.data.Cache;

/**
 * Created by RadyaLabs PC on 12/12/2017.
 */
public class CacheManager {
    public CacheManager() {
    }

    public void storeCache(AppDatabase db, int cacheType, String data) {
        Cache cached = db.cacheDao().loadCacheById(cacheType);
        Cache cache = new Cache();

        if (cached == null) {
            cache.id = cacheType;
            cache.json = "";
            db.cacheDao().insertCache(cache);
        } else {
            cache.id = cacheType;
            cache.json = data;
            db.cacheDao().updateCache(cache);
        }

    }

    public JsonObject loadCache(AppDatabase db, int cacheType) {
        try {
            return new JsonParser().parse(db.cacheDao().loadCacheById(cacheType).json).getAsJsonObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
