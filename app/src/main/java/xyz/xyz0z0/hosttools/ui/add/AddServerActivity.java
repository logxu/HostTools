package xyz.xyz0z0.hosttools.ui.add;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import xyz.xyz0z0.hosttools.MainActivity;
import xyz.xyz0z0.hosttools.MvpApp;
import xyz.xyz0z0.hosttools.R;
import xyz.xyz0z0.hosttools.data.DataManager;

public class AddServerActivity extends AppCompatActivity implements AddMvpView {

  AddPresenter mAddPresenter;
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
    mAddPresenter = new AddPresenter(dataManager);
    mAddPresenter.onAttach(this);
    btnBack.setOnClickListener(v -> onBackButtonClick());
    btnSubmit.setOnClickListener(v -> onSubmitButtonClick());
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


    mAddPresenter.btnSubmitListener();
  }


  @Override public void showLoadingDialog() {

  }


  @Override public void showSuccessDialog() {

  }


  @Override public void showErrorDialog() {

  }
}
