package com.challenge.get.lesson

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.challenge.get.base.viewmodel.NavigationViewModel
import com.challenge.get.base.compose.ChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LessonFragment : Fragment(R.layout.fragment_lesson) {

    companion object {
        fun newInstance() = LessonFragment()
    }

    private val lessonViewModel: LessonViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    private val navigationViewModel: NavigationViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<ComposeView>(R.id.compose_view).setContent {
            ChallengeTheme() {
                LessonScreen(lessonViewModel, navigationViewModel)
            }
        }
    }
}