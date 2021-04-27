package fiix.challenge.fiixexercise.kotlinsample.repository

import fiix.challenge.fiixexercise.kotlinsample.models.TriviaQuestion

class MockRepo {

    val triviaQuestions = mutableListOf(
        TriviaQuestion(
            id = 0,
            question = "How many books are in the Chronicles of Narnia series?",
            answer = "7"
        ),
        TriviaQuestion(
            id = 1,
            question = "Green Eggs and Ham is a book by which author?",
            answer = "Dr. Seuss"
        ),
        TriviaQuestion(
            id = 2,
            question = "What is the title of the first Sherlock Holmes book by Arthur Conan Doyle?",
            answer = "A Study in Scarlet"
        ),
        TriviaQuestion(
            id = 3,
            question = "Typically, how many keys are on a piano?",
            answer = "88"
        ),
        TriviaQuestion(
            id = 4,
            question = "In what year was the first Transformers movie released?",
            answer = "1986"
        ),
        TriviaQuestion(
            id = 5,
            question = "Which movie includes a giant bunny-like spirit who has magic powers including growing trees?",
            answer = "My Neighbor Totoro"
        ),
        TriviaQuestion(
            id = 6,
            question = "In the original Star Wars trilogy, David Prowse was the actor who physically portrayed Darth Vader.",
            answer = "True"
        ),
        TriviaQuestion(
            id = 7,
            question = "In Big Hero 6, what fictional city is the Big Hero 6 from?",
            answer = "San Fransokyo"
        ),
        TriviaQuestion(
            id = 8,
            question = "Where does the original Friday The 13th movie take place?",
            answer = "Camp Crystal Lake"
        ),
        TriviaQuestion(
            id = 9,
            question = "How many pieces are there on the board at the start of a game of chess?",
            answer = "32"
        ),
        TriviaQuestion(
            id = 10,
            question = "How many points is the Z tile worth in Scrabble?",
            answer = "10"
        ),
        TriviaQuestion(
            id = 11,
            question = "Talos, the mythical giant bronze man, was the protector of which island?",
            answer = "Crete"
        )
    )
}