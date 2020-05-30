package fiix.challenge.fiixexercise



import android.content.Context
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.espresso.internal.inject.TargetContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import fiix.challenge.fiixexercise.kotlinsample.dto.TriviaDTO
import fiix.challenge.fiixexercise.kotlinsample.model.Trivia
import fiix.challenge.fiixexercise.kotlinsample.repository.AppDatabase
import fiix.challenge.fiixexercise.kotlinsample.repository.TriviaDAO
import fiix.challenge.fiixexercise.kotlinsample.repository.TriviaMapper
import fiix.challenge.fiixexercise.kotlinsample.util.Constants
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    var dto = TriviaDTO(
        1,
        "here comes the question",
        "here comes the anwers",
        Constants.STATUS_ORIGINAL
    )

    private var dao: TriviaDAO
    private var db: AppDatabase

    init{
        var appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = AppDatabase.getDatabase(appContext)
        dao = db.getTriviaDAO()
    }

    @Test
    fun useAppContext() {
        deleteAll()

        saveTrivia()

        loadTrivia()

        updateTrivia()
    }


    private fun deleteAll(){
        runBlocking {
            val rowId = dao.deleteAll()
            assertTrue(rowId > -1)
        }
    }


    private fun saveTrivia(){
        runBlocking {
            val rowId = dao.save(TriviaMapper.dtoToEntity(dto))
            assertTrue(rowId > -1)
        }
    }


    private fun loadTrivia(){
        runBlocking {
            val trivia = dao.getTrivia(1)
            assertTrue(trivia.id == 1)
            assertFalse(trivia.id == 2)
        }
    }

    private fun updateTrivia(){
        dto.status = Constants.STATUS_UPDATED
        runBlocking {
            val rowId = dao.update(TriviaMapper.dtoToEntity(dto))
            assertTrue(rowId > -1)

            val trivia = dao.getTrivia(1)
            assertTrue(trivia.status == Constants.STATUS_UPDATED)

            assertFalse(trivia.status == Constants.STATUS_ORIGINAL)

            assertFalse(trivia.status == Constants.STATUS_REVEALED)
        }
    }
}
