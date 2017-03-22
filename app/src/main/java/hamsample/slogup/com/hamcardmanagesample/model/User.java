package hamsample.slogup.com.hamcardmanagesample.model;

import java.util.ArrayList;

/**
 * Created by sngjoong on 2017. 3. 22..
 */

public class User {


    private ArrayList<Card> mCards = new ArrayList<>();
    private String mUserName;

    public User(String userName) {
        mUserName = userName;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public ArrayList<Card> getCards() {
        return mCards;
    }

    public void setCards(ArrayList<Card> cards) {
        mCards = cards;
    }

    public static User create(int index) {

        User user = new User("User" + (index));

        ArrayList<Card> cards = new ArrayList<>();

        for (int i = 0; i < index; i++) {

            Card card = new Card(user.getUserName() + "'s card (" + (i + 1) + ")" );
            cards.add(card);
        }
        user.setCards(cards);

        return user;
    }
}
