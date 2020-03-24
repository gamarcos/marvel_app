package br.com.gabrielmarcos.mavel_app.feature.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.gabrielmarcos.mavel_app.R
import br.com.gabrielmarcos.mavel_app.utils.extensions.navigate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DELAY_MILLIS = 2500L

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToSearch()
    }

    private fun navigateToSearch() {
        GlobalScope.launch {
            delay(DELAY_MILLIS)
            navigate(R.id.action_to_search)
        }
    }
}