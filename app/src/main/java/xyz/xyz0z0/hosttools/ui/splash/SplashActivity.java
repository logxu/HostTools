package xyz.xyz0z0.hosttools.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import xyz.xyz0z0.hosttools.R;
import xyz.xyz0z0.hosttools.base.BaseActivity;
import xyz.xyz0z0.hosttools.ui.add.AddServerActivity;
import xyz.xyz0z0.hosttools.ui.main.MainActivity;

public class SplashActivity extends BaseActivity<SplashContract.Presenter> implements SplashContract.View {

  SplashContract.Presenter mSplashPresenter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    new SplashPresenter(this);
    mSplashPresenter.subscribe();
  }


  @Override protected void onResume() {
    super.onResume();
  }


  @Override public void openMainActivity() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    finish();
  }


  @Override public void openAddServerActivity() {
    Intent intent = new Intent(this, AddServerActivity.class);
    startActivity(intent);
    finish();
  }


  @Override public void setPresenter(SplashContract.Presenter presenter) {
    mSplashPresenter = presenter;
  }
}
