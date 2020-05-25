package fiix.challenge.fiixexercise.db

import java.util.concurrent.Executor
import java.util.concurrent.Executors

private val diskIO: Executor = Executors.newSingleThreadExecutor()

fun ioThread(f: () -> Unit) {
    diskIO.execute(f)
}
