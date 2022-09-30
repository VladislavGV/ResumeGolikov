package ru.synergy.resumegolikov.ui.resume

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.synergy.resumegolikov.R

import ru.synergy.resumegolikov.databinding.FragmentResumeBinding


class ResumeFragment : Fragment() {



    companion object {
        const val KEY_URL = "1YBH7Wa_yYzTCIsRukpQmoHdI2RZhqj4U"

        private const val PDF_VIEWER_URL = "https://drive.google.com/uc?export=download&id="
        private const val RELOAD_COUNT = "3"
        private const val RELOAD_ALLOW = 6

        fun newInstance(url: String): ResumeFragment {
            val fragment = ResumeFragment()
            val args = Bundle()
            args.putString(KEY_URL, url)
            fragment.arguments = args
            return fragment
        }

    }

    private var url: String? = null
    private var reloadCount = 0

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_resume, container, false)

        url = arguments?.getString(KEY_URL)

        if (TextUtils.isEmpty(url)) {
            throw IllegalArgumentException("Empty URL passed to WebViewFragment!")
        }

        return view
    }


    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        savedInstanceState?.let {
            reloadCount = it.getInt(RELOAD_COUNT)
        }
        lateinit var webView : WebView
        // Get the web view settings instance.

        val settings = webView.settings

        // Enable java script in web view.
        settings.javaScriptEnabled = true

        // Enable DOM storage API.
        settings.domStorageEnabled = true

        // Enable zooming in web view.
        settings.setSupportZoom(true)

        // Allow pinch to zoom.
        settings.builtInZoomControls = true

        // Disable the default zoom controls on the page.
        settings.displayZoomControls = false

        // Enable responsive layout.
        settings.useWideViewPort = false

        // Zoom out if the content width is greater than the width of the viewport.
        settings.loadWithOverviewMode = false


        // Set web view client.
        webView.webChromeClient = object : DefaultWebChromeClient() {

            lateinit var progressBar : ProgressBar


            override fun onProgressChanged(webView: WebView?, newProgress: Int) {
                if (newProgress < 100) {


                    progressBar.visibility = View.VISIBLE
                }
                if (newProgress == 100) {

                    // There is a bug with Google Docs that sometimes you get blank screen
                    // instead of a PDF file. To avoid just reload when you get it.
                    if (webView?.contentHeight == 0 && reloadCount < RELOAD_ALLOW) {
                        Log.w("LOG_TAG", "PDF loading error. Reloading $reloadCount.")
                        Toast.makeText(activity, "Error. Reloading...", Toast.LENGTH_SHORT).show()
                        reloadCount++
                        webView.reload()
                    }

                    progressBar.visibility = View.GONE
                }
            }

        }


        val urlString: String = PDF_VIEWER_URL + url

        webView.loadUrl(urlString)

        Log.d("LOG_TAG", "Loading URL: $url")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(RELOAD_COUNT, reloadCount)
        super.onSaveInstanceState(outState)
    }
    lateinit var webView : WebView

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Destroy the WebView completely.
        if (webView != null) {
            // The WebView must be removed from the view hierarchy before calling destroy to prevent a memory leak.
            (webView.parent as ViewGroup).removeView(webView)
            webView.removeAllViews()
            webView.destroy()
        }
    }


    fun onBackPressed(): Boolean {
        if (webView.canGoBack()) {
            // If web view have back history, then go to the web view back history.
            webView.goBack()
            return true
        }
        return false
    }


    internal open class DefaultWebViewClient : WebViewClient() {

        // Decide how a new url will be loaded. If this method returns false, it means current
        // webView will handle the url. If this method returns true, it means host application
        // will handle the url. By default, redirects cause jump from WebView to default
        // system browser.
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            return true
        }

    }

    internal open class DefaultWebChromeClient : WebChromeClient() {

    }
}


//    private var _binding: FragmentResumeBinding? = null
//
//    private val binding get() = _binding!!
//
//    @SuppressLint("SetJavaScriptEnabled")
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        val resumeViewModel =
//            ViewModelProvider(this).get(ResumeViewModel::class.java)
//
//        _binding = FragmentResumeBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.textResumeTitle
//        resumeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
//                return root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//
//    }
//
//}








