package com.example.presentation.base

import androidx.fragment.app.Fragment
import com.example.domain.entity.ErrorEntity
import com.example.presentation.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {
    open fun processError(errorEntity: ErrorEntity) {
        val message = when (errorEntity) {
            ErrorEntity.Network -> R.string.error_network
            ErrorEntity.IncorrectInfo -> R.string.error_incorrect_info
            ErrorEntity.NotFound -> R.string.error_not_found
            ErrorEntity.AccessDenied -> R.string.error_access_denied
            ErrorEntity.ServiceUnavailable -> R.string.error_service_unavailable
            ErrorEntity.Unknown -> R.string.error_unknown
        }
        Snackbar.make(
            requireView(),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }
}