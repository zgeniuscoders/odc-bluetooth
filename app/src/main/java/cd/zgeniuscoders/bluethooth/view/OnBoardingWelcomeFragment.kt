package cd.zgeniuscoders.bluethooth.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cd.zgeniuscoders.bluethooth.OnBoardingProgressInterface
import cd.zgeniuscoders.bluethooth.R
import cd.zgeniuscoders.bluethooth.databinding.FragmentOnBoardingWelcomeBinding

open class OnBoardingWelcomeFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingWelcomeBinding
    private var mListener: OnBoardingProgressInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            context as OnBoardingProgressInterface
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString() + " must implement OnButtonClickListener"
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardingWelcomeBinding.inflate(layoutInflater)

        binding.nextBtn.setOnClickListener {
            mListener!!.calcProgressValue(2)

            findNavController().navigate(R.id.action_onBoardingWelcomeFragment_to_onBoardingPresentFragment)
        }

        return binding.root
    }

}