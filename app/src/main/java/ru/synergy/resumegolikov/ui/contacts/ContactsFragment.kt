package ru.synergy.resumegolikov.ui.contacts

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.synergy.resumegolikov.databinding.FragmentContactsBinding


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


        val messageEditText: EditText = binding.message

        val submit: Button = binding.submit

        fun sendMessage(message: String) {

            // Creating intent with action send
            val intent = Intent(Intent.ACTION_SEND)

            // Setting Intent type
            intent.type = "text/plain"

            // Setting whatsapp package name
            intent.setPackage("com.whatsapp")

            // Give your message here
            intent.putExtra(Intent.EXTRA_TEXT, message)

            // Checking whether whatsapp is installed or not
            fun isAppEnabled(packageName: String, context: Context): Boolean {
                return try {
                    val packageManager = context.packageManager
                    packageManager.getApplicationInfo(packageName, 0).enabled
                } catch (e: PackageManager.NameNotFoundException) {
                    false
                }
            }

//            if (intent.resolveActivity(packageManager) == null) {
//                Toast.makeText(
//                    this@ContactsFragment.requireActivity(), "Сначала установите WhatsApp",
//                    Toast.LENGTH_SHORT
//                ).show()
//                return
//            }

            // Starting Whatsapp
            startActivity(intent)
        }
            return root
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null

        }
}


