package fiix.challenge.fiixexercise.kotlinsample.repository

import fiix.challenge.fiixexercise.kotlinsample.dto.TriviaDTO
import fiix.challenge.fiixexercise.kotlinsample.model.Trivia
import fiix.challenge.fiixexercise.kotlinsample.model.TriviaQuestion

object TriviaMapper {

    fun entityToDto(trivia: Trivia) =
        TriviaDTO(
            trivia.id,
            trivia.question,
            trivia.answer,
            trivia.status
        )

    fun dtoToEntity(dto: TriviaDTO) =
        Trivia(
            dto.id,
            dto.question,
            dto.answer,
            dto.status
        )

}