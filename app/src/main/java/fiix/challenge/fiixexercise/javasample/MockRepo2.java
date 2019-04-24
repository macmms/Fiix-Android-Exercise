package fiix.challenge.fiixexercise.javasample;

import java.util.Arrays;
import java.util.List;


public class MockRepo2 {
    private final List<TriviaQuestion2> triviaQuestions = Arrays.asList(
            new TriviaQuestion2("How many books are in the Chronicles of Narnia series?", "7", false),
            new TriviaQuestion2("Green Eggs and Ham is a book by which author?", "Dr. Seuss", false),
            new TriviaQuestion2("What is the title of the first Sherlock Holmes book by Arthur Conan Doyle?", "A Study in Scarlet", false),
            new TriviaQuestion2("Typically, how many keys are on a piano?", "88", false),
            new TriviaQuestion2("In what year was the first Transformers movie released?", "1986", false),
            new TriviaQuestion2("Which movie includes a giant bunny-like spirit who has magic powers including growing trees?", "My Neighbor Totoro", false),
            new TriviaQuestion2("In the original Star Wars trilogy, David Prowse was the actor who physically portrayed Darth Vader.", "True", false),
            new TriviaQuestion2("In Big Hero 6, what fictional city is the Big Hero 6 from?", "San Fransokyo", false),
            new TriviaQuestion2("Where does the original Friday The 13th movie take place?", "Camp Crystal Lake", false),
            new TriviaQuestion2("How many pieces are there on the board at the start of a game of chess?", "32", false),
            new TriviaQuestion2("How many points is the Z tile worth in Scrabble?", "10", false),
            new TriviaQuestion2("Talos, the mythical giant bronze man, was the protector of which island?", "Crete", false)
    );
    public List<TriviaQuestion2> getTriviaQuestions() {
        return triviaQuestions;
    }
}
