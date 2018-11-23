package xyz.xyz0z0.hosttools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.input_layout_password) TextInputLayout ilPassword;
  @BindView(R.id.input_password) TextInputEditText etPassword;
  @BindView(R.id.mainact_back_button) MaterialButton btnBack;


  public static Intent getStartIntent(Context context) {
    return new Intent(context, MainActivity.class);
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    RxView.clicks(btnBack).throttleFirst(2, TimeUnit.SECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .map(o -> btnBack.getText().toString())
        .subscribe(new Consumer<String>() {
          @Override public void accept(String s) throws Exception {
            Toast.makeText(MainActivity.this, s + " test", Toast.LENGTH_SHORT).show();
          }
        });

    Disposable d = RxTextView.textChanges(etPassword)
        .map(charSequence -> charSequence.toString())
        .subscribe(s -> {
          if (s.length() > ilPassword.getCounterMaxLength()) {
            etPassword.setError("max length " + ilPassword.getCounterMaxLength());
          } else {
            etPassword.setError(null);
          }
          Log.d("cxg", "charSequence " + s.length());
          Log.d("cxg", "charSequence " + s);
        }, throwable -> {

        });

  }
}
