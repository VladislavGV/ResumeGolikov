package ru.synergy.resumegolikov.ui.contacts

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.synergy.resumegolikov.databinding.FragmentContactsBinding
import ru.synergy.resumegolikov.databinding.FragmentHomeBinding
import ru.synergy.resumegolikov.ui.home.HomeViewModel


class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contactsViewModel =
            ViewModelProvider(this).get(ContactsViewModel::class.java)

        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.contactsPhone
        textView.movementMethod = LinkMovementMethod.getInstance()

        val whatsTextView: TextView = binding.whatsapp
        whatsTextView.movementMethod = LinkMovementMethod.getInstance()

        val tlgTextView: TextView = binding.telegram
        tlgTextView.movementMethod = LinkMovementMethod.getInstance()


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}


