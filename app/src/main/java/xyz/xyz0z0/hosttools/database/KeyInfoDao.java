package xyz.xyz0z0.hosttools.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by chengxg
 * on 2018/11/26
 */
@Dao
public interface KeyInfoDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long[] insert(KeyInfo... keyInfos);

  @Query("SELECT * FROM key_info")
  Flowable<List<KeyInfo>> queryAllInfo();

}
