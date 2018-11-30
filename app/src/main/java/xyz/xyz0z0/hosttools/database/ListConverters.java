package xyz.xyz0z0.hosttools.database;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

class ListConverters {

  //Type listType = new TypeToken<List<String>>() {}.getType();
  //     List<String> target = new LinkedList<String>();
  //     // target.add("blah");
  //     Gson gson = new Gson();
  //     String json = gson.toJson(target, listType);
  //     // List<String> target2 = gson.fromJson(json, listType);


  @TypeConverter
  public static List<String> fromString(String value) {

    Type listType = new TypeToken<List<String>>() {}.getType();
    Gson gson = new Gson();
    return gson.fromJson(value, listType);
  }


  @TypeConverter
  public static String listToString(List<String> value) {
    Type listType = new TypeToken<List<String>>() {}.getType();
    Gson gson = new Gson();
    return gson.toJson(value, listType);
  }

}
