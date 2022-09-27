package ru.synergy.resumegolikov.ui.resume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.synergy.resumegolikov.databinding.FragmentResumeBinding

class ResumeFragment : Fragment() {

    private var _binding: FragmentResumeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val resumeViewModel =
            ViewModelProvider(this).get(ResumeViewModel::class.java)

        _binding = FragmentResumeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textResume
        resumeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}