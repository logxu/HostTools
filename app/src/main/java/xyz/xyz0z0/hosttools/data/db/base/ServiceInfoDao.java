package xyz.xyz0z0.hosttools.data.db.base;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.List;

@Dao
public interface ServiceInfoDao {

  @Insert
  Maybe<Long> insert(ServiceInfo serviceInfo);

  @Update
  Maybe<Integer> update(ServiceInfo serviceInfor);

  @Query("SELECT * FROM service_info")
  Flowable<List<ServiceInfo>> queryAllInfo();

}