package fiix.challenge.fiixexercise.kotlinsample.data.db.trivia

/**
 * Class provides a collection of trivia with just questions to populate the table.
 */
class DefaultTriviaProvider {

    val trivia = initTrivia()

    private fun initTrivia(): List<Trivia> {
        return mutableListOf<Trivia>().apply {
            add(Trivia(
                    question = "How many books are in the Chronicles of Narnia series?"
            ))
            add(Trivia(
                    question = "Green Eggs and Ham is a book by which author? "
            ))
            add(Trivia(
                    question = "What is the title of the first Sherlock Holmes book by Arthur Conan Doyle?"
            ))
            add(Trivia(
                    question = " Typically, how many keys are on a piano?"
            ))
            add(Trivia(
                    question = "In what year was the first Transformers movie released?"
            ))
            add(Trivia(
                    question = "Which movie includes a giant bunny-like spirit who has magic powers including growing trees?"
            ))
            add(Trivia(
                    question = "In the original Star Wars trilogy, David Prowse was the actor who physically portrayed Darth Vader."
            ))
            add(Trivia(
                    question = "In Big Hero 6, what fictional city is the Big Hero 6 from?"
            ))
            add(Trivia(
                    question = "Where does the original Friday The 13th movie take place?"
            ))
            add(Trivia(
                    question = "How many pieces are there on the board at the start of a game of chess?"
            ))
            add(Trivia(
                    question = "How many points is the Z tile worth in Scrabble?"
            ))
            add(Trivia(
                    question = "Talos, the mythical giant bronze man, was the protector of which island?"
            ))
        }
    }
}