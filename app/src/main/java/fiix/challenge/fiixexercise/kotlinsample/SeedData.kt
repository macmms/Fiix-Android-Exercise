package fiix.challenge.fiixexercise.kotlinsample

import fiix.challenge.fiixexercise.dp.DataSource
import fiix.challenge.fiixexercise.kotlinsample.data.TriviaQuestion

class SeedData : DataSource{
    override fun getData(): List<TriviaQuestion> {
        return arrayListOf(
                TriviaQuestion(1L,"How many books are in the Chronicles of Narnia series?","7"),
                TriviaQuestion(2L,"Green Eggs and Ham is a book by which author?","Dr. Seuss"),
                TriviaQuestion(3L,"What is the title of the first Sherlock Holmes book by Arthur Conan Doyle?","A Study in Scarlet",),
                TriviaQuestion(4L, "Typically, how many keys are on a piano?","88"),
                TriviaQuestion(5L, "In what year was the first Transformers movie released?","1986"),
                TriviaQuestion(6L, "Which movie includes a giant bunny-like spirit who has magic powers including growing trees?", "My Neighbor Totoro"),
                TriviaQuestion(7L, "In the original Star Wars trilogy, David Prowse was the actor who physically portrayed Darth Vader.","True"),
                TriviaQuestion(8L, "In Big Hero 6, what fictional city is the Big Hero 6 from?","San Fransokyo"),
                TriviaQuestion(9L, "Where does the original Friday The 13th movie take place?","Camp Crystal Lake"),
                TriviaQuestion(10L, "How many pieces are there on the board at the start of a game of chess?","32"),
                TriviaQuestion(11L, "How many points is the Z tile worth in Scrabble?","10"),
                TriviaQuestion(12L, "Talos, the mythical giant bronze man, was the protector of which island?","Crete")
        )
    }
}