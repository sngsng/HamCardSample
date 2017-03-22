package hamsample.slogup.com.hamcardmanagesample.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hamsample.slogup.com.hamcardmanagesample.R;
import hamsample.slogup.com.hamcardmanagesample.model.Card;


public class CardFragment extends Fragment {


    private static final String ARG_CARD = "argCard";
    private Card mCard;
    private TextView mCardNameTextView;

    public CardFragment() {

    }


    public static CardFragment newInstance(Card card) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CARD, card);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCard = getArguments().getParcelable(ARG_CARD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_card, container, false);
        bindWidgets(rootView);
        updateCardInfo(mCard);
        return rootView;
    }


    private void bindWidgets(View rootView) {

        mCardNameTextView = (TextView) rootView.findViewById(R.id.card_name_textview);

    }

    private void updateCardInfo(Card card) {

        mCardNameTextView.setText(card.getCardName());
    }


}
