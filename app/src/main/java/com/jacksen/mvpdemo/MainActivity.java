package com.jacksen.mvpdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jacksen.mvpdemo.bean.WeatherInfo;
import com.jacksen.mvpdemo.presenter.WeatherPresenter;
import com.jacksen.mvpdemo.presenter.WeatherPresenterImpl;
import com.jacksen.mvpdemo.view.WeatherView;

/**
 * main activity
 *
 * @author jacksen
 */
public class MainActivity extends AppCompatActivity implements WeatherView {

    private WeatherPresenter weatherPresenter;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userName = intent.getExtras().getString("userName", "");
        Toast.makeText(this, userName, Toast.LENGTH_SHORT).show();

        //
        weatherPresenter = new WeatherPresenterImpl();
        weatherPresenter.attachView(this);
    }

    @Override
    public void showData(WeatherInfo weatherInfo) {

    }

    @Override
    public void showError(String errorInfo) {
        Toast.makeText(this, errorInfo, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, null, "正在查询...");
        }
    }

    @Override
    public void hideLoadingDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
