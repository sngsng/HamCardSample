package hamsample.slogup.com.hamcardmanagesample.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sngjoong on 2017. 3. 22..
 */

public class Card implements Parcelable {



    public Card(String cardName) {
        mCardName = cardName;
    }

    private String mCardName;
    private String mSeq;


    public String getCardName() {
        return mCardName;
    }

    public void setCardName(String cardName) {
        mCardName = cardName;
    }

    public String getSeq() {
        return mSeq;
    }

    public void setSeq(String seq) {
        mSeq = seq;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mCardName);
        dest.writeString(this.mSeq);
    }

    protected Card(Parcel in) {
        this.mCardName = in.readString();
        this.mSeq = in.readString();
    }

    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel source) {
            return new Card(source);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
}
