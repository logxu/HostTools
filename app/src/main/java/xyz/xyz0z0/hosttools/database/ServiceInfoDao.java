package xyz.xyz0z0.hosttools.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Flowable;
import java.util.List;

@Dao
public interface ServiceInfoDao {

  @Insert
  long[] insert(ServiceInfo... serviceInfos);

  @Query("SELECT * FROM service_info")
  Flowable<List<ServiceInfo>> queryAllInfo();

}