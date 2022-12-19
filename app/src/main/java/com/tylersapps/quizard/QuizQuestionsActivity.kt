package com.tylersapps.quizard

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import timber.log.Timber

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var btnSubmit: Button? = null

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null
    private var mSelectedOptionPosition: Int = 0
    private var mUserSelection: Boolean = false
    private var mIfAnswerSubmit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)
        mQuestionsList = QuestionUtil.getQuestions()

        setQuestion()

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionsList = QuestionUtil.getQuestions()

    }

    private fun setQuestion() {

        mIfAnswerSubmit = false

        val question: Question =
            mQuestionsList!![mCurrentPosition - 1] // Getting the question from the list with the help of current position.
        defaultOptionsView()
        if (mCurrentPosition == mQuestionsList!!.size) {
            btnSubmit?.text = getString(R.string.finish)
        } else {
            btnSubmit?.text = getString(R.string.submit)
        }
        progressBar?.progress =
            mCurrentPosition // Setting the current progress in the progressbar using the position of question
        tvProgress?.text =
            "$mCurrentPosition" + getString(R.string.out_of) + progressBar?.max // Setting up the progress text

        // Now set the current question and the options in the UI
        tvQuestion?.text = question.question
        ivImage?.setImageResource(question.image)
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour
    }

    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0, it)
        }
        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }
        tvOptionFour?.let {
            options.add(3, it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {

            R.id.tv_option_one -> {
                if (!mIfAnswerSubmit) {
                    Timber.i("User selected option 1")

                    tvOptionOne?.let {
                        selectedOptionView(it, 1)
                        mUserSelection = true
                    }
                }
            }

            R.id.tv_option_two -> {
                if (!mIfAnswerSubmit) {
                    Timber.i("User selected option 2")

                    tvOptionTwo?.let {
                        selectedOptionView(it, 2)
                        mUserSelection = true
                    }
                }
            }

            R.id.tv_option_three -> {
                if (!mIfAnswerSubmit) {
                    Timber.i("User selected option 3")

                    tvOptionThree?.let {
                        selectedOptionView(it, 3)
                        mUserSelection = true
                    }
                }
            }

            R.id.tv_option_four -> {
                if (!mIfAnswerSubmit) {
                    Timber.i("User selected option 4")

                    tvOptionFour?.let {
                        selectedOptionView(it, 4)
                        mUserSelection = true
                    }
                }
            }

            R.id.btn_submit -> {
                if (!mUserSelection) {
                    Toast.makeText(this, "Please select an answer to continue.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    if (mSelectedOptionPosition == 0) {

                        mCurrentPosition++

                        when {
                            mCurrentPosition <= mQuestionsList!!.size -> {

                                setQuestion()
                                mUserSelection = false
                            }
                            else -> {

                                val intent =
                                    Intent(this@QuizQuestionsActivity, ResultActivity::class.java)
                                intent.putExtra(Constants.USER_NAME, mUserName)
                                intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                                intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                                startActivity(intent)
                                finish()
                            }
                        }
                    } else {
                        val question = mQuestionsList?.get(mCurrentPosition - 1)

                        if (question!!.correctAnswer != mSelectedOptionPosition) {
                            Timber.i("User selected the wrong answer")

                            answerView(mSelectedOptionPosition,
                                R.drawable.incorrect_option_border_bg)
                        } else {
                            Timber.i("User selected the correct answer")

                            mCorrectAnswers++
                        }

                        answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                        if (mCurrentPosition == mQuestionsList!!.size) {
                            btnSubmit?.text = getString(R.string.finish)
                        } else {
                            btnSubmit?.text = getString(R.string.go_to_next_question)
                            mIfAnswerSubmit = true
                        }

                        mSelectedOptionPosition = 0
                    }
                }
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizQuestionsActivity,
            R.drawable.selected_option_border_bg
        )
    }

    private fun answerView(answer: Int, drawableView: Int) {

        when (answer) {

            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}