package xyz.xyz0z0.hosttools;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jakewharton.rxbinding3.widget.RxTextView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.input_layout_password) TextInputLayout ilPassword;
  @BindView(R.id.input_password) TextInputEditText etPassword;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    Disposable d = RxTextView.textChanges(etPassword)
        .subscribe(new Consumer<CharSequence>() {
          @Override public void accept(CharSequence charSequence) throws Exception {
            if (charSequence.length() > ilPassword.getCounterMaxLength()) {
              etPassword.setError("max length " + ilPassword.getCounterMaxLength());
            } else {
              etPassword.setError(null);
            }
            Log.d("cxg", "charSequence " + charSequence.length());
            Log.d("cxg", "charSequence " + charSequence.toString());
          }
        });

    // etPassword.addTextChangedListener(new TextWatcher() {
    //   @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    //
    //   }
    //
    //
    //   @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
    //
    //   }
    //
    //
    //   @Override public void afterTextChanged(Editable s) {
    //     if (s.length() > ilPassword.getCounterMaxLength()) {
    //       etPassword.setError("最大字符" + ilPassword.getCounterMaxLength());
    //     } else {
    //       etPassword.setError(null);
    //     }
    //   }
    // });
  }
}
