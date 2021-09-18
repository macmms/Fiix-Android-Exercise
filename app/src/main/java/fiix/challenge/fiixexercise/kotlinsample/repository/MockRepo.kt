package fiix.challenge.fiixexercise.kotlinsample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestion

class MockRepo {
    private val dp = DataProcessor(LocalDataSource())
    val triviaQuestions: LiveData<List<TriviaQuestion>>
        get() = _triviaQuestions
    private val _triviaQuestions = MutableLiveData<List<TriviaQuestion>>()
    private val answers = dp.getAnswers()
    private val questions = arrayListOf(
        TriviaQuestion("How many books are in the Chronicles of Narnia series?"),
        TriviaQuestion("Green Eggs and Ham is a book by which author?"),
        TriviaQuestion("What is the title of the first Sherlock Holmes book by Arthur Conan Doyle?"),
        TriviaQuestion("Typically, how many keys are on a piano?"),
        TriviaQuestion("In what year was the first Transformers movie released?"),
        TriviaQuestion("Which movie includes a giant bunny-like spirit who has magic powers including growing trees?"),
        TriviaQuestion("In the original Star Wars trilogy, David Prowse was the actor who physically portrayed Darth Vader."),
        TriviaQuestion("In Big Hero 6, what fictional city is the Big Hero 6 from?"),
        TriviaQuestion("Where does the original Friday The 13th movie take place?"),
        TriviaQuestion("How many pieces are there on the board at the start of a game of chess?"),
        TriviaQuestion("How many points is the Z tile worth in Scrabble?"),
        TriviaQuestion("Talos, the mythical giant bronze man, was the protector of which island?")
    )
    private val triviaQuestionsList = questions.mapIndexed { index, triviaQuestion ->
        TriviaQuestion(triviaQuestion.question, answers[index])
    }

    init {
        _triviaQuestions.postValue(triviaQuestionsList)
    }
}