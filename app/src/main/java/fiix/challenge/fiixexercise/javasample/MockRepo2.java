package fiix.challenge.fiixexercise.javasample;

import java.util.Arrays;
import java.util.List;


public class MockRepo2 {
    private final List<TriviaQuestion2> triviaQuestions = Arrays.asList(
            new TriviaQuestion2("How many books are in the Chronicles of Narnia series?", "7"),
            new TriviaQuestion2("Green Eggs and Ham is a book by which author?", "Dr. Seuss"),
            new TriviaQuestion2("What is the title of the first Sherlock Holmes book by Arthur Conan Doyle?", "A Study in Scarlet"),
            new TriviaQuestion2("Typically, how many keys are on a piano?", "88"),
            new TriviaQuestion2("In what year was the first Transformers movie released?", "1986"),
            new TriviaQuestion2("Which movie includes a giant bunny-like spirit who has magic powers including growing trees?", "My Neighbor Totoro"),
            new TriviaQuestion2("In the original Star Wars trilogy, David Prowse was the actor who physically portrayed Darth Vader.", "True"),
            new TriviaQuestion2("In Big Hero 6, what fictional city is the Big Hero 6 from?", "San Fransokyo"),
            new TriviaQuestion2("Where does the original Friday The 13th movie take place?", "Camp Crystal Lake"),
            new TriviaQuestion2("How many pieces are there on the board at the start of a game of chess?", "32"),
            new TriviaQuestion2("How many points is the Z tile worth in Scrabble?", "10"),
            new TriviaQuestion2("Talos, the mythical giant bronze man, was the protector of which island?", "Crete")
    );
    public List<TriviaQuestion2> getTriviaQuestions() {
        return triviaQuestions;
    }
}
