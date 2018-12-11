package xyz.xyz0z0.hosttools.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import xyz.xyz0z0.hosttools.R;
import xyz.xyz0z0.hosttools.base.BaseActivity;
import xyz.xyz0z0.hosttools.data.db.base.ServiceInfo;
import xyz.xyz0z0.hosttools.ui.add.AddServerActivity;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

  @BindView(R.id.swipe_refresh_layout_recycler_view) SwipeRefreshLayout swipeRefreshLayout;
  @BindView(R.id.recycler_view) RecyclerView recyclerView;
  // @BindView(R.id.main_act_toolbar) Toolbar toolbar;
  @BindView(R.id.fab_recycler_view) FloatingActionButton fab;

  private MainContract.Presenter mMainPresenter;

  private int color = 0;
  private boolean loading;
  private int loadTimes;
  private RecyclerViewAdapter adapter;
  RecyclerView.OnScrollListener scrollChangeListener = new RecyclerView.OnScrollListener() {
    @Override public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
      super.onScrolled(recyclerView, dx, dy);

      final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
      Log.d("cxg1", "item-count " + layoutManager.getItemCount());
      int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
      Log.d("cxg1", "lastVisiblePosition " + lastVisiblePosition);
      int visibleChildCount = layoutManager.getChildCount();
      Log.d("cxgx", "visibleChildCount " + visibleChildCount);

      int lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();
      Log.d("cxgx", "lastPosition " + lastPosition);
      int itemCount = layoutManager.getItemCount();
      Log.d("cxg", "loading " + loading);
      if (!loading && itemCount <= visibleChildCount) {
        recyclerView.post(new Runnable() {
          @Override public void run() {
            Log.d("cxg", "ififif");
            adapter.removeFooter();
            loading = true;
          }
        });

      } else if (!loading && layoutManager.getItemCount() == (layoutManager.findLastVisibleItemPosition() + 1)) {
        Log.d("cxg1", "---");
        loading = true;
        new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
            if (loadTimes <= 3) {
              adapter.removeFooter();
              loading = false;
              adapter.addFooter();
              loadTimes++;
            } else {
              adapter.removeFooter();
              Toast.makeText(MainActivity.this, "没有数据了", Toast.LENGTH_SHORT).show();
              new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                  loading = false;
                  adapter.addFooter();
                }
              }, 1000);
            }
          }
        }, 2000);
        //

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
    new MainPresenter(this);
    initView();
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, AddServerActivity.class);
        startActivity(intent);
      }
    });

    demo();
  }


  private void demo() {

    // //
    // Observable.fromArray(Arrays.asList(new String[] {"1", "2", "3", }))
    //     .map(new Function<List<String>, Object>() {
    //       @Override public Object apply(List<String> s) throws Exception {
    //         return Integer.valueOf(s);
    //       }
    //     })
    //     .reduce(new ArrayList<Integer>, (list, s) -> {
    //       list.add(s);
    //       return list;
    //     })
    //     .subscribe(new Consumer<ArrayList<Integer>>() {
    //       @Override public void accept(ArrayList<Integer> i) throws Exception {
    //         // Do some thing with 'i', it's a list of Integer.
    //       }
    //     });

    List<String> list = new ArrayList<>();
    list.add("ABC1");
    list.add("ABC2");
    list.add("ABC3");
    Flowable.fromIterable(list)
        .map(new Function<String, Object>() {
          @Override public Object apply(String s) throws Exception {
            Log.d("cxg11", " " + s);

            return s + " --";
          }
        })
        .subscribe(new Subscriber<Object>() {
          @Override public void onSubscribe(Subscription s) {
            Log.d("cxg11", "onSubscribe");
            s.request(list.size());
          }


          @Override public void onNext(Object o) {
            Log.d("cxg11", "onNext");
            Log.d("cxg11", "onNext " + (String) o);
          }


          @Override public void onError(Throwable t) {
            t.printStackTrace();
            Log.d("cxg11", "onError");
          }


          @Override public void onComplete() {
            Log.d("cxg11", "onComplete");
          }
        });
  }


  @Override public void initView() {
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
    // adapter.addHeader();
    // adapter.setItems(serverData);
    // adapter.addFooter();

    ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
    ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
    mItemTouchHelper.attachToRecyclerView(recyclerView);

    swipeRefreshLayout.setColorSchemeResources(R.color.google_blue, R.color.google_green, R.color.google_red, R.color.google_yellow);
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
          @Override public void run() {
            mMainPresenter.refresh();
            loading = false;
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


  @Override public void showData(List<ServiceInfo> data) {
    // dataRepository = DataRepository.getDefault();
    // serverData = new ArrayList<>();
    // data = new ArrayList<>();
    // for (int i = 0; i < 2; i++) {
    //   serverData.addAll(dataRepository.getServiceInfoList());
    //   data.addAll(dataRepository.getServiceInfoList());
    // }
    adapter.setItems(data);
    loadTimes = 0;
  }


  private int getScreenWidthDp() {
    DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
    return (int) (displayMetrics.widthPixels / displayMetrics.density);
  }


  @Override public void setPresenter(MainContract.Presenter presenter) {
    mMainPresenter = presenter;
  }


  @Override protected void onResume() {
    super.onResume();
    mMainPresenter.subscribe();
  }


  @Override protected void onPause() {
    super.onPause();
    mMainPresenter.unsubscribe();
  }
}
