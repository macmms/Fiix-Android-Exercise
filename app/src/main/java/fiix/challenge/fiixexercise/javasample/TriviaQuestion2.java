package fiix.challenge.fiixexercise.javasample;


import android.os.Parcel;
import android.os.Parcelable;

public class TriviaQuestion2 implements Parcelable {
    final String question;
    final String answer;
    private boolean answered;

    protected TriviaQuestion2(Parcel in) {
        question = in.readString();
        answer = in.readString();
        answered = in.readByte() != 0;
    }

    public static final Creator<TriviaQuestion2> CREATOR = new Creator<TriviaQuestion2>() {
        @Override
        public TriviaQuestion2 createFromParcel(Parcel in) {
            return new TriviaQuestion2(in);
        }

        @Override
        public TriviaQuestion2[] newArray(int size) {
            return new TriviaQuestion2[size];
        }
    };

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    TriviaQuestion2(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(answer);
        dest.writeByte((byte) (answered ? 1 : 0));
    }
}
