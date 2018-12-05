package xyz.xyz0z0.hosttools.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import xyz.xyz0z0.hosttools.R;
import xyz.xyz0z0.hosttools.data.DataRepository;
import xyz.xyz0z0.hosttools.database.ServiceInfo;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.swipe_refresh_layout_recycler_view) SwipeRefreshLayout swipeRefreshLayout;
  @BindView(R.id.recycler_view) RecyclerView recyclerView;
  // @BindView(R.id.main_act_toolbar) Toolbar toolbar;
  private FloatingActionButton fab;

  private int color = 0;
  private List<ServiceInfo> serverData;
  private String insertData;
  private boolean loading;
  private int loadTimes;
  private DataRepository dataRepository;
  private RecyclerViewAdapter adapter;
  RecyclerView.OnScrollListener scrollChangeListener = new RecyclerView.OnScrollListener() {
    @Override public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
      super.onScrolled(recyclerView, dx, dy);

      final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
      if (!loading && linearLayoutManager.getItemCount() == (linearLayoutManager.findLastVisibleItemPosition() + 1)) {
        new Handler().postDelayed(new Runnable() {
          @Override public void run() {
            if (loadTimes <= 5) {
              adapter.removeFooter();
              loading = false;
              // adapter.addItems(data);
              adapter.addFooter();
              loadTimes++;
            } else {
              adapter.removeFooter();
              Toast.makeText(MainActivity.this, "没有数据了", Toast.LENGTH_SHORT).show();
              new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                  loading = false;
                  // adapter.addFooter();
                }
              }, 1000);

            }
          }
        }, 1500);
        loading = true;
      }
    }
  };


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    Toolbar toolbar = findViewById(R.id.main_act_toolbar);
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    initData();
    initView();

  }


  private void initData() {
    dataRepository = DataRepository.getDefault();
    serverData = dataRepository.getServiceInfoList();

    insertData = "0";
    loadTimes = 0;
  }


  private void initView() {
    if (getScreenWidthDp() >= 1200) {
      final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
      recyclerView.setLayoutManager(gridLayoutManager);
    } else if (getScreenWidthDp() >= 800) {
      final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
      recyclerView.setLayoutManager(gridLayoutManager);
    } else {
      final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
      recyclerView.setLayoutManager(linearLayoutManager);
    }

    adapter = new RecyclerViewAdapter(this);
    recyclerView.setAdapter(adapter);
    adapter.addHeader();
    adapter.setItems(serverData);
    adapter.addFooter();

    ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
    ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
    mItemTouchHelper.attachToRecyclerView(recyclerView);

    swipeRefreshLayout.setColorSchemeResources(R.color.google_blue, R.color.google_green, R.color.google_red, R.color.google_yellow);
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
          @Override public void run() {
            if (color > 4) {
              color = 0;
            }
            adapter.setColor(++color);
            swipeRefreshLayout.setRefreshing(false);
          }
        }, 2000);
      }
    });
    recyclerView.addOnScrollListener(scrollChangeListener);
  }


  private int getScreenWidthDp() {
    DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
    return (int) (displayMetrics.widthPixels / displayMetrics.density);
  }
}
