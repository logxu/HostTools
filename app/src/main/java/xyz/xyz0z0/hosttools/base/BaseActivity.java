package xyz.xyz0z0.hosttools.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import xyz.xyz0z0.hosttools.R;

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


  @Override public void showToast(int resId) {
    Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
  }


  @Override public void showErrorDialog(int resId, DialogInterface.OnClickListener listener) {
    new AlertDialog.Builder(this)
        .setMessage(getString(resId))
        .setPositiveButton(getString(R.string.base_dialog_ok), listener)
        .show();
  }


  @Override protected void onPause() {
    super.onPause();
    if (mProgressDialog != null && mProgressDialog.isShowing()) {
      mProgressDialog.dismiss();
    }
  }
}
