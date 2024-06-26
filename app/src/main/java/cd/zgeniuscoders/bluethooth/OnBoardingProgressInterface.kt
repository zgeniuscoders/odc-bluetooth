package cd.zgeniuscoders.bluethooth

interface OnBoardingProgressInterface {
    fun calcProgressValue(currentFragment: Int)
    fun animateProgress(progress: Int)
    fun endAnimation()
}