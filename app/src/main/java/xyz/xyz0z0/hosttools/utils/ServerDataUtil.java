package xyz.xyz0z0.hosttools.utils;

public class ServerDataUtil {

  public static String byteToGB(long l) {
    long count = l / 1024 / 1024 / 1024;
    return new StringBuffer(String.valueOf(count)).append(" GB").toString();
  }

}
