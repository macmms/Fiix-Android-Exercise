package fiix.challenge.fiixexercise.kotlinsample.repository

import android.content.Context
import fiix.challenge.fiixexercise.kotlinsample.dto.TriviaDTO
import fiix.challenge.fiixexercise.kotlinsample.model.Trivia
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TriviaRepository(context: Context) {

    private val triviaDAO: TriviaDAO =
        AppDatabase.getDatabase(context).getTriviaDAO()

    suspend fun save(trivia: Trivia) {
        triviaDAO.save(trivia)
    }

    suspend fun updateTrivia(dto: TriviaDTO) : Int{
        return triviaDAO.update(TriviaMapper.dtoToEntity(dto))
    }

    fun getTriviaList() : Flow<List<TriviaDTO>> {
        return triviaDAO.getTriviaList()
            .map {list->
                list.map { trivia ->
                    TriviaMapper.entityToDto(trivia)
                }
            }
    }

    suspend fun areThereQuestions(): Boolean {
        return triviaDAO.areThereQuestions() > 0
    }

    suspend fun resetTrivia(): Boolean {
        return triviaDAO.resetTrivia() > 0
    }
}