package hamsample.slogup.com.hamcardmanagesample.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hamsample.slogup.com.hamcardmanagesample.R;


public class LoginFormFragment extends Fragment {

    private Button mLoginButton;

    private OnFragmentInteractionListener mListener;

    public LoginFormFragment() {
        // Required empty public constructor
    }


    public static LoginFormFragment newInstance() {
        return new LoginFormFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_login_form, container, false);
        bindWidgets(rootView);
        setUpActions();
        return rootView;
    }

    private void bindWidgets(View rootView) {

        mLoginButton = (Button) rootView.findViewById(R.id.login_form_login_button);
    }

    private void setUpActions() {

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mListener != null) mListener.onLoginButtonClick();
            }
        });
    }

    public interface OnFragmentInteractionListener {

        void onLoginButtonClick();
    }
}
