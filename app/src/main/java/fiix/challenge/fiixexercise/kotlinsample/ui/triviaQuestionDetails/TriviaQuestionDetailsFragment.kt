package fiix.challenge.fiixexercise.kotlinsample.ui.triviaQuestionDetails

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.activities.main.MainActivityContract
import fiix.challenge.fiixexercise.kotlinsample.viewmodels.TEViewModel
import kotlinx.coroutines.*

class TriviaQuestionDetailsFragment(@LayoutRes resId: Int): Fragment(resId) {

    private var questionContentEditText: EditText? = null
    private var answerContentEditText: EditText? = null
    private var saveButton: MaterialButton? = null
    private var alertDialog: AlertDialog? = null

    private val teViewModel: TEViewModel by activityViewModels()
    private var scope: CoroutineScope? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        showAlertDialog(
            title = getString(R.string.update_q_and_a_fail_title_text),
            message = getString(R.string.update_q_and_a_fail_message_text),
            button1 = getString(R.string.dialog_ok_button_text)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeSpecificTriviaQuestion()
    }

    override fun onDestroyView() {
        // Cancel coroutine scope
        cancelCoroutineScope()
        // Clear dialog
        if (alertDialog != null && alertDialog?.isShowing == true){
            alertDialog?.dismiss()
        }
        alertDialog = null
        super.onDestroyView()
    }

    // region Private Methods
    private fun initViews() {
        questionContentEditText = view?.findViewById(R.id.fe_trivia_question_details_question_content_edit_text)
        answerContentEditText = view?.findViewById(R.id.fe_trivia_question_details_answer_content_edit_text)
        saveButton = view?.findViewById(R.id.fe_trivia_question_details_save_button)
        setUpSaveButton()
    }

    private fun showAlertDialog(title: String, message: String, button1: String, callback1: (() -> Unit)? = null) {
        if (alertDialog == null || alertDialog?.isShowing != true){
            createAlertDialog(title = title, message = message, button1 = button1, callback1 = callback1)
        }
        alertDialog?.show()
    }

    private fun createAlertDialog(title: String, message: String, button1: String, callback1: (() -> Unit)?) {
        alertDialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setNegativeButton(button1) { dialog, _ ->
                callback1?.invoke() ?: dialog.dismiss()
            }
            .create()
    }

    private fun setUpSaveButton() {
        saveButton?.setOnClickListener { _ ->
            teViewModel.selectedTriviaQuestion.value?.id?.let { mId ->
                cancelCoroutineScope()
                scope = CoroutineScope(Dispatchers.Main + exceptionHandler)
                scope?.launch {
                    // Update specific trivia question
                    launch {
                        teViewModel.updateTriviaQuestion(
                            id = mId,
                            newQuestion = questionContentEditText?.text?.toString(),
                            newAnswer = answerContentEditText?.text?.toString()
                        )
                    }.join()
                    // Show update successful dialog if there is no exception
                    showAlertDialog(
                        title = getString(R.string.update_q_and_a_successful_title_text),
                        message = getString(R.string.update_q_and_a_successful_message_text),
                        button1 = getString(R.string.dialog_ok_button_text)
                    ) {
                        popBackToPreviousScreen()
                    }
                }
            }
        }
    }

    private fun observeSpecificTriviaQuestion() {
        teViewModel.selectedTriviaQuestion.observe(viewLifecycleOwner, { mItem ->
            questionContentEditText?.setText(mItem.question ?: "")
            answerContentEditText?.setText(mItem.answer ?: "")
        })
    }

    private fun popBackToPreviousScreen() {
        (activity as? MainActivityContract.Router)?.popBack()
    }

    private fun cancelCoroutineScope() {
        if (scope?.isActive == true) {
            scope?.cancel()
            scope = null
        }
    }
    // endregion
}