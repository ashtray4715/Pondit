package com.ashtray.pondit.common.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.ashtray.pondit.R
import com.ashtray.pondit.common.app.GPApp
import com.ashtray.pondit.common.helper.GPLog.d
import com.ashtray.pondit.common.helper.GPSafeRun

import com.ashtray.pondit.common.ui.GPFragment.TransactionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GPActivity : AppCompatActivity(), GPFragment.CallBacks {

    private val mTag = "GPActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gp_activity)

        changeFragment(
            GPApp.getFactory().getSplashScreenFragment(),
            TransactionType.SINGLE_FRAGMENT
        )
    }

    override fun onBackPressed() {
        val unRef = supportFragmentManager.findFragmentById(R.id.fragment_container)
        val fragment = unRef as GPFragment?
        if (fragment == null || !fragment.handleBackButtonPressed()) {
            d(mTag, "fragment can't handle back press [CLOSING_APP]")
            super.onBackPressed()
        }
    }

    override fun changeFragment(fragment: GPFragment, transactionType: TransactionType) {
        lifecycleScope.launch {
            when (transactionType) {
                TransactionType.ADD_FRAGMENT -> changeFragmentAdd(fragment)
                TransactionType.REMOVE_FRAGMENT -> changeFragmentRemove(fragment)
                TransactionType.SINGLE_FRAGMENT -> changeFragmentSingle(fragment)
            }
        }
    }

    private suspend fun changeFragmentAdd(fragment: GPFragment) {
        d(mTag, "changeFragmentAdd: called with ${fragment.uniqueTag}")
        withContext(Dispatchers.Main) {
            GPSafeRun {
                val tran = supportFragmentManager.beginTransaction()
                //tran.setCustomAnimations(R.anim.enter_right, R.anim.exit_left)
                tran.add(R.id.fragment_container, fragment, fragment.uniqueTag)
                tran.commit()
            }
        }
    }

    private suspend fun changeFragmentRemove(fragment: GPFragment) {
        d(mTag, "changeFragmentRemove: called with ${fragment.uniqueTag}")
        withContext(Dispatchers.Main) {
            GPSafeRun {
                val tran = supportFragmentManager.beginTransaction()
                //tran.setCustomAnimations(R.anim.enter_left, R.anim.exit_right)
                tran.remove(fragment)
                tran.commit()
            }
        }
    }

    private suspend fun changeFragmentSingle(fragment: GPFragment) {
        d(mTag, "changeFragmentSingle: called with ${fragment.uniqueTag}")
        withContext(Dispatchers.Main) {
            GPSafeRun {
                for (previousFragment in supportFragmentManager.fragments) {
                    val tran = supportFragmentManager.beginTransaction()
                    tran.remove(previousFragment)
                    tran.commitNow()
                }
                val tran = supportFragmentManager.beginTransaction()
                tran.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                tran.add(R.id.fragment_container, fragment, fragment.uniqueTag)
                tran.commit()
            }
        }
    }
}