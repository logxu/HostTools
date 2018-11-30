package xyz.xyz0z0.hosttools.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.List;
import xyz.xyz0z0.hosttools.net.response.ServiceInfoResponse;

/**
 * Created by chengxg
 * on 2018/11/26
 */
@Entity(tableName = "service_info")
public class ServiceInfo {

  @PrimaryKey
  @NonNull
  @ColumnInfo(name = "veid")
  private int veId;

  @NonNull
  @ColumnInfo(name = "api_key")
  private String apiKey;

  private String vm_type;
  private String hostname;
  private String node_ip;
  private String node_alias;
  private String node_location;
  private String node_location_id;
  private String node_datacenter;
  private boolean location_ipv6_ready;
  private String plan;
  private long plan_monthly_data;
  private int monthly_data_multiplier;
  private long plan_disk;
  private int plan_ram;
  private int plan_swap;
  private int plan_max_ipv6s;
  private String os;
  private String email;
  private long data_counter;
  private int data_next_reset;
  private boolean rdns_api_available;
  private boolean suspended;
  private List<String> ip_addresses;


  public ServiceInfo() {
  }


  @Ignore
  public ServiceInfo(@NonNull int veId, @NonNull String apiKey, @NonNull ServiceInfoResponse response) {
    this.veId = veId;
    this.apiKey = apiKey;
    this.vm_type = response.getVm_type();
    this.hostname = response.getHostname();
    this.node_ip = response.getNode_ip();
    this.node_alias = response.getNode_alias();
    this.node_location = response.getNode_location();
    this.node_location_id = response.getNode_location_id();
    this.node_datacenter = response.getNode_datacenter();
    this.location_ipv6_ready = response.isLocation_ipv6_ready();
    this.plan = response.getPlan();
    this.plan_monthly_data = response.getPlan_monthly_data();
    this.monthly_data_multiplier = response.getMonthly_data_multiplier();
    this.plan_disk = response.getPlan_disk();
    this.plan_ram = response.getPlan_ram();
    this.plan_swap = response.getPlan_swap();
    this.plan_max_ipv6s = response.getPlan_max_ipv6s();
    this.os = response.getOs();
    this.email = response.getEmail();
    this.data_counter = response.getData_counter();
    this.data_next_reset = response.getData_next_reset();
    this.rdns_api_available = response.isRdns_api_available();
    this.suspended = response.isSuspended();
    this.ip_addresses = response.getIp_addresses();
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


  public String getVm_type() { return vm_type;}


  public void setVm_type(String vm_type) { this.vm_type = vm_type;}


  public String getHostname() { return hostname;}


  public void setHostname(String hostname) { this.hostname = hostname;}


  public String getNode_ip() { return node_ip;}


  public void setNode_ip(String node_ip) { this.node_ip = node_ip;}


  public String getNode_alias() { return node_alias;}


  public void setNode_alias(String node_alias) { this.node_alias = node_alias;}


  public String getNode_location() { return node_location;}


  public void setNode_location(String node_location) { this.node_location = node_location;}


  public String getNode_location_id() { return node_location_id;}


  public void setNode_location_id(String node_location_id) { this.node_location_id = node_location_id;}


  public String getNode_datacenter() { return node_datacenter;}


  public void setNode_datacenter(String node_datacenter) { this.node_datacenter = node_datacenter;}


  public boolean isLocation_ipv6_ready() { return location_ipv6_ready;}


  public void setLocation_ipv6_ready(boolean location_ipv6_ready) { this.location_ipv6_ready = location_ipv6_ready;}


  public String getPlan() { return plan;}


  public void setPlan(String plan) { this.plan = plan;}


  public long getPlan_monthly_data() { return plan_monthly_data;}


  public void setPlan_monthly_data(long plan_monthly_data) { this.plan_monthly_data = plan_monthly_data;}


  public int getMonthly_data_multiplier() { return monthly_data_multiplier;}


  public void setMonthly_data_multiplier(int monthly_data_multiplier) { this.monthly_data_multiplier = monthly_data_multiplier;}


  public long getPlan_disk() { return plan_disk;}


  public void setPlan_disk(long plan_disk) { this.plan_disk = plan_disk;}


  public int getPlan_ram() { return plan_ram;}


  public void setPlan_ram(int plan_ram) { this.plan_ram = plan_ram;}


  public int getPlan_swap() { return plan_swap;}


  public void setPlan_swap(int plan_swap) { this.plan_swap = plan_swap;}


  public int getPlan_max_ipv6s() { return plan_max_ipv6s;}


  public void setPlan_max_ipv6s(int plan_max_ipv6s) { this.plan_max_ipv6s = plan_max_ipv6s;}


  public String getOs() { return os;}


  public void setOs(String os) { this.os = os;}


  public String getEmail() { return email;}


  public void setEmail(String email) { this.email = email;}


  public long getData_counter() { return data_counter;}


  public void setData_counter(long data_counter) { this.data_counter = data_counter;}


  public int getData_next_reset() { return data_next_reset;}


  public void setData_next_reset(int data_next_reset) { this.data_next_reset = data_next_reset;}


  public boolean isRdns_api_available() { return rdns_api_available;}


  public void setRdns_api_available(boolean rdns_api_available) { this.rdns_api_available = rdns_api_available;}


  public boolean isSuspended() { return suspended;}


  public void setSuspended(boolean suspended) { this.suspended = suspended;}


  public List<String> getIp_addresses() { return ip_addresses;}


  public void setIp_addresses(List<String> ip_addresses) { this.ip_addresses = ip_addresses;}

}
