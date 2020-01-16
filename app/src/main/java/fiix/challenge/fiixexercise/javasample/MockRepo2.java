package fiix.challenge.fiixexercise.javasample;

import java.util.Arrays;
import java.util.List;


public class MockRepo2 {
    private final List<TriviaQuestion2> triviaQuestions = Arrays.asList(
            new TriviaQuestion2("How many books are in the Chronicles of Narnia series?", null),
            new TriviaQuestion2("Green Eggs and Ham is a book by which author?", null),
            new TriviaQuestion2("What is the title of the first Sherlock Holmes book by Arthur Conan Doyle?", null),
            new TriviaQuestion2("Typically, how many keys are on a piano?", null),
            new TriviaQuestion2("In what year was the first Transformers movie released?", null),
            new TriviaQuestion2("Which movie includes a giant bunny-like spirit who has magic powers including growing trees?", null),
            new TriviaQuestion2("In the original Star Wars trilogy, David Prowse was the actor who physically portrayed Darth Vader.", null),
            new TriviaQuestion2("In Big Hero 6, what fictional city is the Big Hero 6 from?", null),
            new TriviaQuestion2("Where does the original Friday The 13th movie take place?", null),
            new TriviaQuestion2("How many pieces are there on the board at the start of a game of chess?", null),
            new TriviaQuestion2("How many points is the Z tile worth in Scrabble?", null),
            new TriviaQuestion2("Talos, the mythical giant bronze man, was the protector of which island?", null)
    );
    public List<TriviaQuestion2> getTriviaQuestions() {
        return triviaQuestions;
    }
}
