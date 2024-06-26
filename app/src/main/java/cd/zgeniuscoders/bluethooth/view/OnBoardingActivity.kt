package cd.zgeniuscoders.bluethooth.view

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cd.zgeniuscoders.bluethooth.OnBoardingProgressInterface
import cd.zgeniuscoders.bluethooth.R
import cd.zgeniuscoders.bluethooth.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity(), OnBoardingProgressInterface {

    private val TOTALFRAGMENTS = 2
    private val ANIMATIONDURATION = 1000

    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun calcProgressValue(currentFragment: Int) {
        val progress = (currentFragment * 100) / TOTALFRAGMENTS
        animateProgress(progress)
        binding.progressBar.max = 100
        binding.progressBar.progress = progress
    }

    override fun animateProgress(progress: Int) {
        val animation = ObjectAnimator.ofInt(binding.progressBar, "progress", progress)
        animation.duration = ANIMATIONDURATION.toLong()
        animation.interpolator = DecelerateInterpolator()
        animation.start()
    }

    override fun endAnimation() {
        TODO("Not yet implemented")
    }
}