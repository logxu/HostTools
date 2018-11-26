package xyz.xyz0z0.hosttools.ui.add;

import androidx.annotation.NonNull;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by chengxg
 * on 2018/11/24
 */
public class AddPresenter implements AddContract.Presenter {

  @NonNull
  private final AddContract.View mAddServerView;

  @NonNull
  private CompositeDisposable mCompositeDisposable;


  public AddPresenter(@NonNull AddContract.View addServerView) {
    this.mAddServerView = addServerView;
    mCompositeDisposable = new CompositeDisposable();
    mAddServerView.setPresenter(this);

  }


  @Override public void submit(String veid, String apikey) {

  }


  @Override public void subscribe() {

  }


  @Override public void unsubscribe() {
    mCompositeDisposable.clear();
  }
}
