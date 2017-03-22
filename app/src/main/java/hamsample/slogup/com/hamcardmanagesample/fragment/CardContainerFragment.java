package hamsample.slogup.com.hamcardmanagesample.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hamsample.slogup.com.hamcardmanagesample.R;
import hamsample.slogup.com.hamcardmanagesample.StateManager;
import hamsample.slogup.com.hamcardmanagesample.adapter.CardFragmentAdapter;
import hamsample.slogup.com.hamcardmanagesample.model.Card;
import hamsample.slogup.com.hamcardmanagesample.model.User;


public class CardContainerFragment extends Fragment {

    private static final String LOG_TAG = CardContainerFragment.class.getSimpleName();
    private ViewPager mViewPager;
    private TextView mEmptyTextView;
    private CardFragmentAdapter mCardFragmentAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public CardContainerFragment() {
        // Required empty public constructor
    }

    public static CardContainerFragment newInstance() {

        return new CardContainerFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.i(LOG_TAG, "onStart");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(LOG_TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_card_container, container, false);
        bindWidgets(rootView);
        setUpPager();

        if (mFragments.isEmpty()) {

            updateCardFragments();
        }


        return rootView;
    }

    public void updateCardFragments() {

        mFragments = getCurrentUserCardFragments();
        updateCardFragments(mFragments);
    }

    private void bindWidgets(View rootView) {

        mViewPager = (ViewPager) rootView.findViewById(R.id.card_container_view_pager);
        mEmptyTextView = (TextView) rootView.findViewById(R.id.card_container_empty_textview);

    }

    private void setUpPager() {

        mCardFragmentAdapter = new CardFragmentAdapter(getFragmentManager());
        mViewPager.setAdapter(mCardFragmentAdapter);
    }

    private ArrayList<Fragment> getCurrentUserCardFragments() {

        ArrayList<Fragment> fragments = new ArrayList<>();

        StateManager stateManager = StateManager.getInstance();
        User user = stateManager.getUser();

        if (user != null) {

            for (int i = 0; i < user.getCards().size(); i++) {

                Card card = user.getCards().get(i);
                CardFragment cardFragment = CardFragment.newInstance(card);
                fragments.add(cardFragment);
            }
        }

        return fragments;
    }

    private void updateCardFragments(ArrayList<Fragment> fragments) {


        if (fragments.isEmpty()) {

            mViewPager.setVisibility(View.GONE);
            mEmptyTextView.setVisibility(View.VISIBLE);

        }
        else {

            mViewPager.setVisibility(View.VISIBLE);
            mEmptyTextView.setVisibility(View.GONE);

            mCardFragmentAdapter.setFragments(fragments);
            mCardFragmentAdapter.notifyDataSetChanged();
        }


    }

}
