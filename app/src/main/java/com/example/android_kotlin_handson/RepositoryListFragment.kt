package com.example.android_kotlin_handson

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_kotlin_handson.api.RetrofitSearchRepositoryApi
import com.example.android_kotlin_handson.api.SearchRepositoryApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class RepositoryListFragment : Fragment() {

    private val language: String by lazy {
        arguments?.getString(ARG_LANGUAGE) ?: ""
    }

    private val api: SearchRepositoryApi = RetrofitSearchRepositoryApi()

    private var disposables: CompositeDisposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_repository_list, container, false)

        view.findViewById<RecyclerView>(R.id.recyclerView).run {
            layoutManager = LinearLayoutManager(activity)
            adapter = RecyclerAdapter(context, listOf())
        }
        return view
    }

    private fun Disposable.into(disposable: CompositeDisposable?) {
        disposable?.add(this)
    }

    override fun onResume() {
        super.onResume()
        disposables = CompositeDisposable()
        api.searchRepositoryByLanguage(language!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { repositoryList ->
                    view?.let {
                        val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView)
                        recyclerView.swapAdapter(RecyclerAdapter(context!!, repositoryList), false)
                    }}
                .into(disposables)
    }

    override fun onStop() {
        super.onStop()
        disposables?.dispose()
        disposables = null
    }

    companion object {
        private const val ARG_LANGUAGE = "ARG_LANGUAGE"

        fun newInstance(language: String) =
                RepositoryListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_LANGUAGE, language)
                    }
                }
    }
}
