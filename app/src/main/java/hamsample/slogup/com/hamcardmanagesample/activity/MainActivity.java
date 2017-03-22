package hamsample.slogup.com.hamcardmanagesample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;

import hamsample.slogup.com.hamcardmanagesample.R;
import hamsample.slogup.com.hamcardmanagesample.StateManager;
import hamsample.slogup.com.hamcardmanagesample.fragment.CardContainerFragment;
import hamsample.slogup.com.hamcardmanagesample.fragment.LoginFormFragment;
import hamsample.slogup.com.hamcardmanagesample.model.User;

public class MainActivity extends AppCompatActivity implements LoginFormFragment.OnFragmentInteractionListener {

    private CardContainerFragment mCardContainerFragment;

    private static final int REQ_CODE_LOGIN_CHANGED = 0;

    private LoginFormFragment mLoginFormFragment;
    private Button mSettingsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindWidgets();
        setUpActions();
        updateFragmentByLoginState();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateFragmentByLoginState();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case REQ_CODE_LOGIN_CHANGED:
                    refreshCard();
                    break;
            }

        }
    }

    private void refreshCard() {

        if (StateManager.getInstance().isLoginState()) {

            mCardContainerFragment = CardContainerFragment.newInstance();
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.main_fragment_container, mCardContainerFragment).
                    commit();
        }
    }

    /**
     * Fragment Interaction Listener
     */
    @Override
    public void onLoginButtonClick() {

        new MaterialDialog.Builder(this)
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

                        // 프래그먼트 교체
                        updateFragmentByLoginState();
                    }
                })
                .show();

    }


    /**
     * Private Fucntions
     */

    private void updateFragmentByLoginState() {

        // 로그인 상태일 경우 카드 컨테이너 프래그먼트 설정
        if (StateManager.getInstance().isLoginState()) {

            if (mCardContainerFragment == null)
                mCardContainerFragment = CardContainerFragment.newInstance();

            if (!mCardContainerFragment.isAdded()) {

                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.main_fragment_container, mCardContainerFragment).
                        commit();

                mLoginFormFragment = null;
            }

        }
        // 로그인 상태가 아닐경우 로그인 프래그먼트 설정
        else {

            if (mLoginFormFragment == null)
                mLoginFormFragment = LoginFormFragment.newInstance();

            if (!mLoginFormFragment.isAdded()) {

                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.main_fragment_container, mLoginFormFragment).
                        commit();


                mCardContainerFragment = null;

            }

        }
    }




    /**
     * ViewHelper Alternative
     */

    private void bindWidgets() {

        mSettingsButton = (Button) findViewById(R.id.main_settings_button);
    }

    private void setUpActions() {

        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(intent, REQ_CODE_LOGIN_CHANGED);
            }
        });
    }
}
