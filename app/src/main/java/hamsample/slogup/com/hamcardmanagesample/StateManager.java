package hamsample.slogup.com.hamcardmanagesample;


import hamsample.slogup.com.hamcardmanagesample.model.User;

/**
 * Created by sngjoong on 2017. 3. 22..
 */

public class StateManager {

    private boolean mIsLoggedIn = false;
    public static boolean sIsLoginStateChanged = false;


    private static StateManager sStateManager = new StateManager();
    private User mUser;

    public static StateManager getInstance() {

        return sStateManager;
    }


    public boolean isLoginState() {

        return mIsLoggedIn;

    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public void setLoginState(boolean isLoggedIn) {

        mIsLoggedIn = isLoggedIn;

    }


}
