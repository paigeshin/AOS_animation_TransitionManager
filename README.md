ℹ️ **Transition**

- Transitions allow the changes made to the layout and appearance of the views in a user interface to be animated during application runtime

**ℹ️ Scene**

Represents either the entire layout of a user interface, or a subset of the layout represented by a ViewGroup 

# TransitionManager API

- Fade
- Slide
- Explode
- ChangeBounds
- TransitionSet
- AutoTransition

⇒ You can either use this API programmatically or xml(TransitionManager.inflate()).

# Create MultipleScene with Scenes, (One Activity)

 

### xml files

```xml
<!-- activity_main.xml -->
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/rootContainer"
    tools:context=".MainActivity">

</androidx.constraintlayout.widget.ConstraintLayout>

<!-- scene1.xml -->
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/buttonGoToScene2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="goToSecondScene"
        android:text="CURRENT SCENE IS 1, GO TO SCENE 2"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
<!-- scene2.xml -->
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/buttonGoToScene3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="CURRENT SCENE IS 2, GO TO SCENE 3"
        android:onClick="goToThirdScene"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
<!-- scene3.xml -->
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/buttonGoToScene1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="CURRENT SCENE IS 3, GO TO SCENE 1"
        android:onClick="goToFirstScene"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

   

### activity (handle with transition Manager)

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var scene1: Scene
    private lateinit var scene2: Scene
    private lateinit var scene3: Scene

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
```

# SharedElementTransition

- API Level above 21

❗️TransitionManager 내용이 아님, TransitionManager 관한 내용인줄 알았는데 아니였다. 

### Enable `windowContentTransitions`

```kotlin
<!-- Add this line to enable SharedElementTransition  -->
<item name="android:windowContentTransitions">true</item>
```

```xml
<resources xmlns:tools="http://schemas.android.com/tools">
    <!-- Base application theme. -->
    <style name="Theme.TransitionTutorial" parent="Theme.MaterialComponents.DayNight.DarkActionBar">
        <!-- Primary brand color. -->
        <item name="colorPrimary">@color/purple_500</item>
        <item name="colorPrimaryVariant">@color/purple_700</item>
        <item name="colorOnPrimary">@color/white</item>
        <!-- Secondary brand color. -->
        <item name="colorSecondary">@color/teal_200</item>
        <item name="colorSecondaryVariant">@color/teal_700</item>
        <item name="colorOnSecondary">@color/black</item>
        <!-- Status bar color. -->
        <item name="android:statusBarColor" tools:targetApi="l">?attr/colorPrimaryVariant</item>
        <!-- Customize your theme here. -->

        <!-- Add this line to enable SharedElementTransition  -->
        <item name="android:windowContentTransitions">true</item>

    </style>
</resources>
```

### layout.xml, provide `transition_name`

- layout_first

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".sharedelement.SharedElementActivity">
    
    <ImageView
        android:id="@+id/image_activity_first"
        android:src="@drawable/sexy_girl"
        android:scaleType="centerCrop"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        android:layout_marginBottom="100dp"
        android:layout_width="180dp"
        android:layout_height="100dp"
        android:transitionName="example_transition"/>

    <Button
        android:id="@+id/openSecondActivityButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_centerVertical="true"
        android:text="open second activity"/>

</RelativeLayout>
```

- layout_second

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="@android:color/darker_gray"
    tools:context=".sharedelement.SharedElementActivitySecond">
    
    <ImageView
        android:src="@drawable/sexy_girl"
        android:scaleType="centerCrop"
        android:layout_width="match_parent"
        android:layout_height="250dp"
        android:transitionName="example_transition"/>

    <TextView
        android:text="It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

</LinearLayout>
```

- first acitvity

```kotlin
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
```

- second activity

```kotlin
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
```