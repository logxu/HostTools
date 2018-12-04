package xyz.xyz0z0.hosttools.ui.add;

import android.content.Intent;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.jakewharton.rxbinding3.widget.RxTextView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import java.util.Objects;
import xyz.xyz0z0.hosttools.MvpApp;
import xyz.xyz0z0.hosttools.R;
import xyz.xyz0z0.hosttools.base.BaseActivity;
import xyz.xyz0z0.hosttools.data.DataManager;
import xyz.xyz0z0.hosttools.ui.main.MainActivity;

public class AddServerActivity extends BaseActivity<AddContract.Presenter> implements AddContract.View {

  AddContract.Presenter mAddPresenter;
  @BindView(R.id.input_et_id) TextInputEditText etId;
  @BindView(R.id.input_et_key) TextInputEditText etKey;
  @BindView(R.id.mainact_back_button) MaterialButton btnBack;
  @BindView(R.id.mainact_submit_button) MaterialButton btnSubmit;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_server);
    ButterKnife.bind(this);
    DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
    new AddPresenter(this);
    btnBack.setOnClickListener(v -> onBackButtonClick());
    btnSubmit.setOnClickListener(v -> onSubmitButtonClick());

    Observable<CharSequence> o1 = RxTextView.textChanges(etId);
    Observable<CharSequence> o2 = RxTextView.textChanges(etKey);
    Disposable d = Observable.combineLatest(o1, o2, new BiFunction<CharSequence, CharSequence, Boolean>() {
      @Override public Boolean apply(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence.toString().trim().length() > 0 && charSequence2.toString().trim().length() == 32;
      }
    }).subscribe(new Consumer<Boolean>() {
      @Override public void accept(Boolean aBoolean) throws Exception {
        btnSubmit.setEnabled(aBoolean);
      }
    });

    // ProgressDialog progressDialog = new ProgressDialog(this);
    // progressDialog.setMessage(getString(R.string.base_loading_text));
    // progressDialog.show();

  }


  @Override protected void onResume() {
    super.onResume();
    mAddPresenter.subscribe();
  }


  @Override protected void onPause() {
    super.onPause();
    mAddPresenter.unsubscribe();
  }


  @Override public void openMainActivity() {
    Intent intent = new Intent(AddServerActivity.this, MainActivity.class);
    startActivity(intent);
    finish();
  }


  @Override public void onBackButtonClick() {
    finish();
  }


  @Override public void onSubmitButtonClick() {
    String veid = Objects.requireNonNull(etId.getText()).toString();
    String apikey = Objects.requireNonNull(etKey.getText()).toString();
    mAddPresenter.submit(veid, apikey);
  }


  @Override public void setPresenter(AddContract.Presenter presenter) {
    mAddPresenter = presenter;
  }
}
