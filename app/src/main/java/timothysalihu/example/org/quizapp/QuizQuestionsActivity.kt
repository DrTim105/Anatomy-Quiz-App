package timothysalihu.example.org.quizapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import timothysalihu.example.org.quizapp.util.PrefUtil
import java.lang.String.format
import java.util.*
import kotlin.collections.ArrayList


private const val TAG = "QuizQuestActivity"
class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    enum class TimerState{
        Stopped, Paused, Running
    }

    private lateinit var timer: CountDownTimer
    private var timerLengthSecond = 0L
    private var timerState = TimerState.Stopped
    private var secondsRemaining = 0L

    private var mCurrentPosition : Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var clickCheck = false
    private var mCorrectAnswers: Int = 0
    private var mUserName: String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
//        setSupportActionBar(findViewById(R.id.toolbar))
//        supportActionBar?.setIcon(R.drawable.ic_timer)
//        supportActionBar?.setIcon(R.drawable.ic_timer)
//        supportActionBar?.title ="          Timer"

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = Constants.getQuestions()

        Log.d(TAG, "onCreate: called")


        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)

        btn_submit.setOnClickListener(this)
    }


    override fun onResume() {
        Log.d(TAG, "onResume: called")
        super.onResume()
        initTimer()

        removeAlarm(this)
    }

    override fun onPause() {
        super.onPause()

        if(timerState ==  TimerState.Running) {
            timer.cancel()
            val wakeUpTime = setAlarm(this, nowSeconds, secondsRemaining)
        }

        PrefUtil.setPreviousTimerLengthSeconds(timerLengthSecond, this)
        PrefUtil.setSecondsRemaining(secondsRemaining, this)
        PrefUtil.setTimerState(timerState, this)
    }

    private fun initTimer(){
        Log.d(TAG, "initTimer: called")

        timerState = PrefUtil.getTimerState(this)

        if (timerState == TimerState.Stopped)
            setNewTimerLength()

        secondsRemaining = if (timerState == TimerState.Running)
            PrefUtil.getSecondsRemaining(this)
        else
            timerLengthSecond

        val alarmSetTime = PrefUtil.getAlarmSetTime((this))
        if (alarmSetTime > 0)
            secondsRemaining -= nowSeconds - alarmSetTime

        if (secondsRemaining <= 0)
            onTimerFinished()
        else
            startTimer()

        updateCountDownUI()
    }

    private fun onTimerFinished(){
        Log.d(TAG, "onTimerFinished: called")

        timerState = TimerState.Stopped

        setNewTimerLength()

        onSubmit()

        PrefUtil.setSecondsRemaining(timerLengthSecond, this)
        secondsRemaining = timerLengthSecond

        updateCountDownUI()
    }

    private fun startTimer(){
        Log.d(TAG, "startTimer: called")

        timerState = TimerState.Running

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountDownUI()
            }
        }.start()
    }

    private fun setNewTimerLength(){
        Log.d(TAG, "setNewTimerLength: called")

        val lengthInMinutes = PrefUtil.getTimerLength(this)
        timerLengthSecond = (lengthInMinutes * 1L)
    }

    private fun updateCountDownUI(){
        Log.d(TAG, "updateCountDownUI: called")

        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinutesUntilFinished = secondsRemaining % 60
        val secondStr = secondsInMinutesUntilFinished.toString()
        tv_countdown.text = "$minutesUntilFinished:${
            if (secondStr.length == 2) secondStr
            else "0" + secondStr}"
    }

    private fun setQuestion() {

//        startTimer()
        onResume()

        clickCheck = true
        check(clickCheck)

        val currentQuest : Question? = mQuestionsList!![mCurrentPosition - 1]

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size)
            btn_submit.text = "FINISH"
        else
            btn_submit.text = "SUBMIT"

        progressBar.progress = mCurrentPosition
        iv_image.setImageResource(currentQuest!!.image)
        tv_progress.text = format("$mCurrentPosition/${progressBar.max}")
        tv_question.text = currentQuest.question
        tv_option_one.text = currentQuest.optionOne
        tv_option_two.text = currentQuest.optionTwo
        tv_option_three.text = currentQuest.optionThree
        tv_option_four.text = currentQuest.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for(option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one -> {
                selectedOptionView(tv_option_one, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(tv_option_two, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(tv_option_three, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(tv_option_four, 4)
            }
            R.id.btn_submit-> {
                clickCheck = false
                check(clickCheck)

                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            mScrollView.scrollTo(0,0)
                            setQuestion()
                        }
                        else -> {
//                            Toast.makeText(
//                                this, "You have sucessfully completed the Quiz",
//                                Toast.LENGTH_SHORT
//                            ).show()
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {

                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        btn_submit.text = "FINISH"
                    } else {
                        btn_submit.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        Log.d(TAG, "answerView called")

        when(answer){
            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                tv_option_three.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                tv_option_four.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    private fun check(check: Boolean){
        tv_option_one.isClickable = check
        tv_option_two.isClickable = check
        tv_option_three.isClickable = check
        tv_option_four.isClickable = check
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int)
    {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    private fun onSubmit(){
        clickCheck = false
        check(clickCheck)


        val question = mQuestionsList?.get(mCurrentPosition - 1)
        if (question!!.correctAnswer != mSelectedOptionPosition) {
            answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
        } else {
            mCorrectAnswers++
        }
        answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

        if (mCurrentPosition == mQuestionsList!!.size) {
            btn_submit.text = "FINISH"
        } else {
            btn_submit.text = "GO TO NEXT QUESTION"
        }
    }

    companion object{
        fun setAlarm(context: Context, nowSeconds: Long, secondsRemaining: Long): Long{
            val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
            }
            PrefUtil.setAlarmSetTime(nowSeconds, context)
            return wakeUpTime
        }

        fun removeAlarm(context: Context) {
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
            PrefUtil.setAlarmSetTime(0, context)
        }

        val nowSeconds: Long
            get() = Calendar.getInstance().timeInMillis / 1000
    }
}