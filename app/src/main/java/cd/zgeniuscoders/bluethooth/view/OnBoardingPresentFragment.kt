package cd.zgeniuscoders.bluethooth.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cd.zgeniuscoders.bluethooth.OnBoardingProgressInterface
import cd.zgeniuscoders.bluethooth.R
import cd.zgeniuscoders.bluethooth.databinding.FragmentOnBoardingPresentBinding

class OnBoardingPresentFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingPresentBinding
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
        binding = FragmentOnBoardingPresentBinding.inflate(layoutInflater)

        binding.nextBtn.setOnClickListener {

            mListener!!.calcProgressValue(2)

            findNavController().navigate(R.id.action_onBoardingWelcomeFragment_to_onBoardingPresentFragment)

        }

        return binding.root
    }

}