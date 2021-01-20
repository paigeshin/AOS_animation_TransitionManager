package com.paigesoftware.transitiontutorial.sharedelement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat

import com.paigesoftware.transitiontutorial.R
import kotlinx.android.synthetic.main.activity_shared_element.*

class SharedElementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_element)

        title = "activity1"

        /*
            //androidx 이전 버전만 먹힌다. 사실상 안쓰일걸로 추정
            //app bar는 animation 대상에서 제거하기
            val fadeAnim = Fade()
            val decorView: View = window.decorView
            //actionbar container은 안드로이드 default로 제공.
            fadeAnim.excludeTarget(decorView.findViewById(R.id.action_bar_container) as View, true)
            fadeAnim.excludeTarget(decorView.findViewById(android.R.id.background) as View, true)
            fadeAnim.excludeTarget(decorView.findViewById(android.R.id.navigationBarBackground) as View, true)
            window.enterTransition = fadeAnim
            window.exitTransition = fadeAnim
         */

        image_activity_first
        openSecondActivityButton.setOnClickListener {
            val intent = Intent(this, SharedElementActivitySecond::class.java)
            val transitionName = ViewCompat.getTransitionName(image_activity_first)
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, image_activity_first, transitionName!!)
            startActivity(intent, activityOptions.toBundle())
        }

    }
}