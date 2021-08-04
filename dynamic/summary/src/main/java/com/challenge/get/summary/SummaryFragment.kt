package com.challenge.get.summary

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.challenge.get.base.compose.ChallengeTheme
import com.challenge.get.base.viewmodel.NavigationViewModel
import com.challenge.get.di.SummaryModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class SummaryFragment: Fragment(R.layout.fragment_summary) {

    @Inject
    lateinit var summaryViewModelProviderFactory: SummaryViewModelProviderFactory

    private val summaryViewModel: SummaryViewModel by viewModels { summaryViewModelProviderFactory }
    private val navigationViewModel: NavigationViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerSummaryModComponent.builder()
            .fragment(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext().applicationContext,
                    SummaryModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ComposeView>(R.id.compose_view).setContent {
            ChallengeTheme() {
                SummaryScreen(summaryViewModel, navigationViewModel)
            }
        }
    }


}