package xyz.xyz0z0.hosttools.ui.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private final int TYPE_NORMAL = 1;
  private final int TYPE_FOOTER = 2;
  private final int TYPE_HEADER = 3;
  private final String FOOTER = "footer";
  private final String HEADER = "header";
  private Context context;
  private List<String> mItems;
  private int color = 0;
  private View parentView;


  public RecyclerViewAdapter(Context context) {
    this.context = context;
    mItems = new ArrayList<>();
  }


  public void setItems(List<String> data) {
    this.mItems.addAll(data);
    notifyDataSetChanged();
  }


  public void addItem(int position, String insertData) {
    mItems.add(position, insertData);
    notifyItemInserted(position);
  }


  public void addItems(List<String> data) {
    mItems.add(HEADER);
    mItems.addAll(data);
    notifyItemInserted(mItems.size() - 1);
  }


  public void addHeader() {
    this.mItems.add(HEADER);
    notifyItemInserted(mItems.size() - 1);
  }


  public void addFooter() {
    mItems.add(FOOTER);
    notifyItemInserted(mItems.size() - 1);
  }


  public void removeFooter() {
    mItems.remove(mItems.size() - 1);
    notifyItemRemoved(mItems.size());
  }


  public void setColor(int color) {
    this.color = color;
    notifyDataSetChanged();
  }


  @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return null;
  }


  @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

  }


  @Override public int getItemCount() {
    return 0;
  }
}
