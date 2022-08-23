package com.ashtray.pondit.common.ui

import android.content.Context
import androidx.fragment.app.Fragment

abstract class GPFragment : Fragment() {

    interface CallBacks {
        fun changeFragment(fragment: GPFragment, transactionType: TransactionType)
    }

    enum class TransactionType {
        ADD_FRAGMENT, REMOVE_FRAGMENT, SINGLE_FRAGMENT
    }

    private var callBacks: CallBacks? = null

    /**
     * This function will be used, only to initialize the callback, and
     * The callback is responsible for providing ads from the activity.
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBacks = when (context is CallBacks) {
            true -> context
            else -> throw RuntimeException("Must implement GPFragment.CallBacks")
        }
    }

    /**
     * This function will be only to de-initialize the callback, cause
     * once the fragment is detached then the callback is no longer needed
     */
    override fun onDetach() {
        callBacks = null
        super.onDetach()
    }

    abstract val uniqueTag: String

    open fun handleBackButtonPressed(): Boolean {
        return false
    }

    protected fun changeFragment(fragment: GPFragment, transactionType: TransactionType) {
        callBacks?.changeFragment(fragment, transactionType)
    }
}