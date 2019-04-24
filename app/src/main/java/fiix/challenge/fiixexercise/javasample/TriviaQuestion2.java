package fiix.challenge.fiixexercise.javasample;

public class TriviaQuestion2 {

    String question;
    String answer;
    public Boolean mIsViewed;

    TriviaQuestion2(String question, String answer, boolean isViewed) {
        this.question = question;
        this.answer = answer;
        this.mIsViewed = isViewed;
    }

    public void setViewed(){
        mIsViewed = true;
    }
}
