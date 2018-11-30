package xyz.xyz0z0.hosttools.base;

import android.app.ProgressDialog;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by chengxg
 * on 2018/11/30
 */
public abstract class BaseActivity<T extends MvpPresenter> extends AppCompatActivity implements MvpView<T> {

  private ProgressDialog mProgressDialog;


  @Override public void showLoadingDialog(int resId) {
    mProgressDialog = new ProgressDialog(this);
    mProgressDialog.setMessage(getString(resId));
    mProgressDialog.show();
  }


  @Override public void dismissLoadingDialog() {
    if (mProgressDialog != null && mProgressDialog.isShowing()) {
      mProgressDialog.dismiss();
    }
  }


  @Override public void showErrorDialog(String msg) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
  }


  @Override public void showSuccessDialog(String msg) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
  }


  @Override protected void onPause() {
    super.onPause();
    if (mProgressDialog != null && mProgressDialog.isShowing()) {
      mProgressDialog.dismiss();
    }
  }
}
