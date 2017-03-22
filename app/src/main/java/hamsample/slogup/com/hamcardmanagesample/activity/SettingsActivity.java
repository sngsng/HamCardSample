package hamsample.slogup.com.hamcardmanagesample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import hamsample.slogup.com.hamcardmanagesample.R;
import hamsample.slogup.com.hamcardmanagesample.StateManager;
import hamsample.slogup.com.hamcardmanagesample.model.User;

public class SettingsActivity extends AppCompatActivity {


    private Button mLoginButton;
    private Button mLogoutButton;
    private TextView mUserIdTextView;

    private boolean mIsLoginStateChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        bindWidgets();
        setUpActions();
        updateButton();
    }

    @Override
    public void finish() {

        if (mIsLoginStateChanged) setResult(RESULT_OK);

        super.finish();
    }

    private void bindWidgets() {

        mLoginButton = (Button) findViewById(R.id.settings_login_button);
        mLogoutButton = (Button) findViewById(R.id.settings_logout_button);
        mUserIdTextView = (TextView) findViewById(R.id.settings_id_textview);
    }

    private void setUpActions() {

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StateManager stateManager = StateManager.getInstance();
                stateManager.setLoginState(false);
                stateManager.setUser(null);

                mIsLoginStateChanged = true;
                updateButton();
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new MaterialDialog.Builder(SettingsActivity.this)
                        .title("로그인할 유저 선택")
                        .items(R.array.titles_user_ids)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                                // 로그인 처리
                                StateManager stateManager = StateManager.getInstance();
                                User user = User.create(which);
                                stateManager.setUser(user);
                                stateManager.setLoginState(true);

                                mIsLoginStateChanged = true;
                                updateButton();

                            }
                        })
                        .show();
            }
        });
    }

    private void updateButton() {

        StateManager stateManager = StateManager.getInstance();

        if (stateManager.isLoginState()) {

            mLoginButton.setVisibility(View.GONE);
            mLogoutButton.setVisibility(View.VISIBLE);

            if (stateManager.getUser() != null)
                mUserIdTextView.setText(stateManager.getUser().getUserName());

        } else {

            mLoginButton.setVisibility(View.VISIBLE);
            mLogoutButton.setVisibility(View.GONE);

            mUserIdTextView.setText("");
        }
    }
}
