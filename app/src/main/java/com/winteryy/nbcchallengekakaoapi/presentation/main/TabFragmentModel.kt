package com.winteryy.nbcchallengekakaoapi.presentation.main

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

data class TabFragmentModel(
    val fragment: Fragment,
    @StringRes val title: Int
)
