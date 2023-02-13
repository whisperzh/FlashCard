package com.bignerdranch.android.flashcard

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.flashcard.databinding.ActivityFlashCardBinding

private const val TAG = "FlashCard: AppCompatActivity"

class FlashCard : AppCompatActivity() {
    private lateinit var binding:ActivityFlashCardBinding
    private val questionViewModel: QuestionViewModel by viewModels()
    private val gameViewModel: GameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card)
        binding=ActivityFlashCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(gameViewModel.isGameStarted()){
            displayCurrentQuestions()
        }else{
            val intent = intent
            val name = intent.getStringExtra("username")
            Toast.makeText(this, "Welcome $name!", Toast.LENGTH_SHORT).show()

        }

        binding.generateButton.setOnClickListener {
            if (!gameViewModel.isGameStarted()){
                questionViewModel.generateQuestions()
                gameViewModel.setGameStartedToTrue()
                displayCurrentQuestions()
            }
        }


        binding.answerSubmitButton.setOnClickListener{
            if (gameViewModel.gameStarted){
                val strAnswer:String = getUserAnswer()
                if (checkAnswer(strAnswer)){
                    //if submit last question, do something
                    if (questionViewModel.isLastQuestion()){
                        compareAnswer(strAnswer.toDouble())
                        afterOneRoundActions()
                    }else{
                        //if not the last, go to next question:
                        compareAnswerAndShowNextQuestion(strAnswer)
                    }
                }
            }
            else{
                Toast.makeText(this, R.string.game_not_started,Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getQuestionVM(): QuestionViewModel{
        return questionViewModel
    }

    fun getGameVM(): GameViewModel{
        return gameViewModel
    }

    private fun afterOneRoundActions() {
        val options: List<String> = listOf("YES", "NO")
        val endGameMessage: String = createEndGameMessage()
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(endGameMessage)
        builder.setPositiveButton(R.string.yes) { _, _ ->
            replay()
        }
        builder.setNegativeButton(R.string.no) { dialog, which ->
            this.finishAffinity()
        }
        builder.show()
    }

    private fun replay() {
        binding.num1Text.setText("0")
        binding.num2Text.setText("0")
        binding.operatorText.setText(R.string.unknown)
        questionViewModel.resetAllDataToStartGame()
        gameViewModel.resetAllDataToStartGame()
    }

    private fun createEndGameMessage(): String {
        return "${questionViewModel.getCorrectCounts()} out of ${questionViewModel.getTotalQuestionNumber()}. Replay?"
    }

    private fun compareAnswerAndShowNextQuestion(strAnswer: String) {
        compareAnswer(strAnswer.toDouble())
        questionViewModel.moveToNextQuestion()
        displayCurrentQuestions()
    }

    private fun compareAnswer(userAnswer: Double){
        val correctAnswer: Double = questionViewModel.getCorrectAnswer().toDouble()
        if (userAnswer == correctAnswer){
            questionViewModel.increaseCorrectCountByOne()
        }
    }

    private fun displayCurrentQuestions(){
        try{
            val currentQuestion: Question = questionViewModel.getCurrentQuestion()
            binding.num1Text.setText(currentQuestion.firstOperand.toString())
            binding.num2Text.setText(currentQuestion.secondOperand.toString())
            binding.operatorText.setText(currentQuestion.operation)
        }catch (e: java.lang.IndexOutOfBoundsException){
            Log.e(TAG, e.toString())
        }
    }

    private fun getUserAnswer(): String{
        return binding.answerText.text.toString()
    }

    private fun checkAnswer(userAnswer: String): Boolean {
        if (userAnswer.equals("")){
            Toast.makeText(this, R.string.invalid_empty_answer, Toast.LENGTH_SHORT).show()
            return false
        }
        try {
            userAnswer.toDouble()
            return true
        }catch (error: java.lang.NumberFormatException){
            println("here")
            Toast.makeText(this, R.string.invalid_answer, Toast.LENGTH_SHORT).show()
            return false
        }
    }

}