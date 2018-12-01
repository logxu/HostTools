package xyz.xyz0z0.hosttools.ui.main;

import android.os.Bundle;
import android.util.DisplayMetrics;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import xyz.xyz0z0.hosttools.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.swipe_refresh_layout_recycler_view) SwipeRefreshLayout swipeRefreshLayout;
  @BindView(R.id.recycler_view) RecyclerView recyclerView;
  // @BindView(R.id.main_act_toolbar) Toolbar toolbar;
  private FloatingActionButton fab;

  private int color = 0;
  private List<String> data;
  private String insertData;
  private boolean loading;
  private int loadTimes;


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

    // RxView.clicks(btnBack).throttleFirst(2, TimeUnit.SECONDS)
    //     .observeOn(AndroidSchedulers.mainThread())
    //     .map(o -> btnBack.getText().toString())
    //     .subscribe(new Consumer<String>() {
    //       @Override public void accept(String s) throws Exception {
    //         Toast.makeText(MainActivity.this, s + " test", Toast.LENGTH_SHORT).show();
    //       }
    //     });

    // Disposable d = RxTextView.textChanges(etPassword)
    //     .map(charSequence -> charSequence.toString())
    //     .subscribe(s -> {
    //       if (s.length() > ilPassword.getCounterMaxLength()) {
    //         etPassword.setError("max length " + ilPassword.getCounterMaxLength());
    //       } else {
    //         etPassword.setError(null);
    //       }
    //       Log.d("cxg", "charSequence " + s.length());
    //       Log.d("cxg", "charSequence " + s);
    //     }, throwable -> {
    //
    //     });

  }


  private void initData() {
    data = new ArrayList<>();
    for (int i = 1; i <= 20; i++) {
      data.add(i + "");
    }
    insertData = "0";
    loadTimes = 0;
  }


  private void initView() {
    if (getScreenWidthDp()>=1200){
      final GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
      recyclerView.setLayoutManager(gridLayoutManager);
    }else if (getScreenWidthDp()>=800){
      final GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
      recyclerView.setLayoutManager(gridLayoutManager);
    }else {
      final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
      recyclerView.setLayoutManager(linearLayoutManager);
    }
  }


  private int getScreenWidthDp() {
    DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
    return (int) (displayMetrics.widthPixels / displayMetrics.density);
  }
}
