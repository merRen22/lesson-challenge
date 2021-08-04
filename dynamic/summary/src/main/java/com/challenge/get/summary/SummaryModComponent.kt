package com.challenge.get.summary

import androidx.fragment.app.Fragment
import com.challenge.get.di.SummaryModuleDependencies
import dagger.BindsInstance
import dagger.Component

/**
 * Hilt does not support injection for Dynamic Feature Module.
 * Therefore, this is a alternative way to archive injection for now.
 * https://developer.android.com/training/dependency-injection/hilt-multi-module#dfm
 */
@Component(dependencies = [SummaryModuleDependencies::class])
interface SummaryModComponent {

    fun inject(fragment: SummaryFragment)

    @Component.Builder
    interface Builder {

        fun fragment(@BindsInstance fragment: Fragment): Builder

        fun appDependencies(dependencies: SummaryModuleDependencies): Builder

        fun build(): SummaryModComponent
    }
}