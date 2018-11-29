package xyz.xyz0z0.hosttools.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Created by chengxg
 * on 2018/11/26
 */
@Entity(tableName = "key_info", indices = { @Index(value = "veid", unique = true) })
public class KeyInfo {

  @PrimaryKey(autoGenerate = true)
  @NonNull
  @ColumnInfo(name = "id")
  private long infoId;

  @NonNull
  @ColumnInfo(name = "veid")
  private int veId;

  @NonNull
  @ColumnInfo(name = "api_key")
  private String apiKey;


  public KeyInfo(@NonNull int veId, @NonNull String apiKey) {
    this.veId = veId;
    this.apiKey = apiKey;
  }


  @NonNull public long getInfoId() {
    return infoId;
  }


  public void setInfoId(@NonNull long infoId) {
    this.infoId = infoId;
  }


  @NonNull public int getVeId() {
    return veId;
  }


  public void setVeId(@NonNull int veId) {
    this.veId = veId;
  }


  @NonNull public String getApiKey() {
    return apiKey;
  }


  public void setApiKey(@NonNull String apiKey) {
    this.apiKey = apiKey;
  }
}
