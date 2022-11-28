package sk.figlar.quiz

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentQuestionPosition: Int = 1
    private var mQuestionList: List<Question>? = null
    private var mSelectedOptionPosition: Int = -1

    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null

    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null

    private var btnSubmit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tvProgress)
        tvQuestion = findViewById(R.id.tvQuestion)
        ivImage = findViewById(R.id.ivImage)

        tvOptionOne = findViewById(R.id.tvOptionOne)
        tvOptionTwo = findViewById(R.id.tvOptionTwo)
        tvOptionThree = findViewById(R.id.tvOptionThree)
        tvOptionFour = findViewById(R.id.tvOptionFour)

        btnSubmit = findViewById(R.id.btnSubmit)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionList = Constants.getQuestions()

        setQuestion()
    }

    private fun defaultOptionsView() {
        val options: MutableList<TextView> = mutableListOf()
        options.add(tvOptionOne!!)
        options.add(tvOptionTwo!!)
        options.add(tvOptionThree!!)
        options.add(tvOptionFour!!)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7a8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg,
            )
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363a43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg,
        )
    }

    private fun setQuestion() {
        defaultOptionsView()

        val question = mQuestionList!![mCurrentQuestionPosition - 1]
        ivImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentQuestionPosition
        tvProgress?.text = "$mCurrentQuestionPosition / ${progressBar?.max}"
        tvQuestion?.text = question.text
        tvOptionOne?.text = question.options[0]
        tvOptionTwo?.text = question.options[1]
        tvOptionThree?.text = question.options[2]
        tvOptionFour?.text = question.options[3]

        if (mCurrentQuestionPosition == mQuestionList!!.size) {
            btnSubmit?.text = "FINISH"
        } else {
            btnSubmit?.text = "SUBMIT"
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            0 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            1 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvOptionOne   -> {
                tvOptionOne?.let {
                    selectedOptionView(it, 0)
                }
            }
            R.id.tvOptionTwo   -> {
                tvOptionTwo?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.tvOptionThree -> {
                tvOptionThree?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.tvOptionFour  -> {
                tvOptionFour?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.btnSubmit     -> {
                if (mSelectedOptionPosition == -1) {
                    mCurrentQuestionPosition++

                    if (mCurrentQuestionPosition <= mQuestionList!!.size) {
                        setQuestion()
                    } else {
                        Toast.makeText(this, "You made it to the end", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val question = mQuestionList!![mCurrentQuestionPosition - 1]
                    if (question.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentQuestionPosition == mQuestionList!!.size) {
                        btnSubmit?.text = "FINISH"
                    } else {
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = -1
                }
            }
        }
    }
}