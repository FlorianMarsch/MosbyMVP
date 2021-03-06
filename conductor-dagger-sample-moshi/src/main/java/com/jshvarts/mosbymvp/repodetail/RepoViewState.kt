package com.jshvarts.mosbymvp.repodetail

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState
import com.jshvarts.mosbymvp.domain.GithubRepo

class RepoViewState : ViewState<RepoDetailContract.View> {
    companion object {
        private const val STATE_DO_NOTHING = 0
        private const val STATE_SHOW_DATA = 1
        private const val STATE_SHOW_LOADING = 2
        private const val STATE_SHOW_ERROR = 3
    }

    private var state = STATE_DO_NOTHING

    private var data: GithubRepo? = null

    fun setData(data: GithubRepo) {
        state = STATE_SHOW_DATA
        this.data = data
    }

    fun setShowLoading() {
        state = STATE_SHOW_LOADING
    }

    fun setShowError() {
        state = STATE_SHOW_ERROR
    }

    override fun apply(view: RepoDetailContract.View, retained: Boolean) {
        when (state) {
            STATE_SHOW_DATA -> view.onLoadRepoDetailSuccess(data!!)
            STATE_SHOW_LOADING -> view.showLoading()
            STATE_SHOW_ERROR -> data = null
        }
    }
}