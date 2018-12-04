package xyz.xyz0z0.hosttools.ui.splash;

import androidx.annotation.NonNull;
import xyz.xyz0z0.hosttools.data.DataRepository;

/**
 * Created by chengxg
 * on 2018/11/23
 */
public class SplashPresenter implements SplashContract.Presenter {

  @NonNull
  private final SplashContract.View mSplashContract;
  private DataRepository mDataRepository;


  public SplashPresenter(@NonNull SplashContract.View splashContract) {
    this.mSplashContract = splashContract;
    mSplashContract.setPresenter(this);
    mDataRepository = DataRepository.getDefault();
  }


  @Override public void decideNextActivity() {
    if (mDataRepository.getServerExists()) {
      mSplashContract.openMainActivity();
    } else {
      mSplashContract.openAddServerActivity();
    }
  }


  @Override public void subscribe() {
    decideNextActivity();
  }


  @Override public void unsubscribe() {

  }
}
