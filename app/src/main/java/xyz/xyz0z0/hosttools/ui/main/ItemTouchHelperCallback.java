package xyz.xyz0z0.hosttools.ui.main;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

  private final int TYPE_NORMAL = 1;
  private onMoveAndSwipedListener moveAndSwipedListener;


  public ItemTouchHelperCallback(onMoveAndSwipedListener listener) {
    this.moveAndSwipedListener = listener;
  }


  @Override public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

    if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
      final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
      final int swipeFlags = 0;
      return makeMovementFlags(dragFlags, swipeFlags);
    } else {
      if (viewHolder.getItemViewType() == TYPE_NORMAL) {
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
      } else {
        return 0;
      }
    }
  }


  @Override public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
    if (viewHolder.getItemViewType()!=target.getItemViewType()){
      return false;
    }
    moveAndSwipedListener.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
    return true;
  }


  @Override public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
    moveAndSwipedListener.onItemDismiss(viewHolder.getAdapterPosition());
  }
}
