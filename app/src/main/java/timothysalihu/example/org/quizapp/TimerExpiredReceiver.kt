package timothysalihu.example.org.quizapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timothysalihu.example.org.quizapp.util.PrefUtil


class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        //TODO: show notification

        PrefUtil.setTimerState(QuizQuestionsActivity.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}