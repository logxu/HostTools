package xyz.xyz0z0.hosttools.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by chengxg
 * on 2018/11/26
 */
@Database(entities = { KeyInfo.class }, version = 1, exportSchema = false)
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


  public abstract KeyInfoDao keyInfoDao();

}
