package xyz.xyz0z0.hosttools.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import xyz.xyz0z0.hosttools.ui.main.MainActivity;
import xyz.xyz0z0.hosttools.MvpApp;
import xyz.xyz0z0.hosttools.R;
import xyz.xyz0z0.hosttools.data.DataManager;
import xyz.xyz0z0.hosttools.ui.add.AddServerActivity;

public class SplashActivity extends AppCompatActivity implements SplashMvpView {

  SplashPresenter mSplashPresenter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
    mSplashPresenter = new SplashPresenter(dataManager);
    mSplashPresenter.onAttach(this);
    mSplashPresenter.decideNextActivity();
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
}
