package com.paigesoftware.transitiontutorial.muletiplesceneinoneactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.transition.*
import com.paigesoftware.transitiontutorial.R
import kotlinx.android.synthetic.main.activity_multiple_scenes.*

class MultipleScenesActivity : AppCompatActivity() {

    private lateinit var scene1: Scene
    private lateinit var scene2: Scene
    private lateinit var scene3: Scene

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_scenes)

        scene1 = Scene.getSceneForLayout(rootContainer, R.layout.scene1, this)
        scene2 = Scene.getSceneForLayout(rootContainer, R.layout.scene2, this)
        scene3 = Scene.getSceneForLayout(rootContainer, R.layout.scene3, this)
        scene1.enter()

    }

    //layout이 inflate가 안된 상태에서 viewBinding이 안되므로 xml onClick으로 접근하는 방식이 좋다.
    fun goToSecondScene(view: View) {
        TransitionManager.go(scene2)
    }

    //layout이 inflate가 안된 상태에서 viewBinding이 안되므로 xml onClick으로 접근하는 방식이 좋다.
    fun goToThirdScene(view: View) {
        val explode: Transition = Explode()
        TransitionManager.go(scene3, explode)
    }

    //layout이 inflate가 안된 상태에서 viewBinding이 안되므로 xml onClick으로 접근하는 방식이 좋다.
    fun goToFirstScene(view: View) {
        val slide: Transition = Slide(Gravity.END)
        TransitionManager.go(scene1, slide)
    }

}