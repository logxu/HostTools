package xyz.xyz0z0.hosttools.data.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * Created by chengxg
 * on 2018/11/26
 */
@Database(entities = { ServiceInfo.class }, version = 1, exportSchema = false)
@TypeConverters({ ListConverters.class })
public abstract class RoomDb extends RoomDatabase {

  private static volatile RoomDb INSTANCE;


  public static RoomDb getDatabase(Context context) {
    if (INSTANCE == null) {
      synchronized (RoomDb.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomDb.class, "server.db").build();

        }
      }
    }
    return INSTANCE;
  }


  public abstract ServiceInfoDao serviceInfoDao();

}
