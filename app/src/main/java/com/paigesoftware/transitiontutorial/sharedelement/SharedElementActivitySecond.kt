package com.paigesoftware.transitiontutorial.sharedelement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.paigesoftware.transitiontutorial.R

class SharedElementActivitySecond : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_element_second)

        title = "activity2"

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

    }
}