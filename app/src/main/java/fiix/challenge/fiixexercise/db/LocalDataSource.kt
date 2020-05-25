package fiix.challenge.fiixexercise.db

import fiix.challenge.fiixexercise.dp.DataSource

class LocalDataSource : DataSource<TriviaQuestionSample> {
    override fun getData(): List<TriviaQuestionSample> {
        return listOf(
                TriviaQuestionSample("How many books are in the Chronicles of Narnia series?", "7"),
                TriviaQuestionSample("Green Eggs and Ham is a book by which author?", "Dr. Seuss"),
                TriviaQuestionSample("What is the title of the first Sherlock Holmes book by Arthur Conan Doyle?", "A Study in Scarlet"),
                TriviaQuestionSample("Typically, how many keys are on a piano?", "88"),
                TriviaQuestionSample("In what year was the first Transformers movie released?", "1986"),
                TriviaQuestionSample("Which movie includes a giant bunny-like spirit who has magic powers including growing trees?", "My Neighbor Totoro"),
                TriviaQuestionSample("In the original Star Wars trilogy, David Prowse was the actor who physically portrayed Darth Vader.", "True"),
                TriviaQuestionSample("In Big Hero 6, what fictional city is the Big Hero 6 from?", "San Fransokyo"),
                TriviaQuestionSample("Where does the original Friday The 13th movie take place?", "Camp Crystal Lake"),
                TriviaQuestionSample("How many pieces are there on the board at the start of a game of chess?", "32"),
                TriviaQuestionSample("How many points is the Z tile worth in Scrabble?", "10"),
                TriviaQuestionSample("Talos, the mythical giant bronze man, was the protector of which island?", "Crete")
        )
    }
}

data class TriviaQuestionSample(val question: String, var answer: String)