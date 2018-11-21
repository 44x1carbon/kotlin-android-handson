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

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the [RepositoryListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RepositoryListFragment : Fragment() {


    private val language: String by lazy {
        arguments?.getString(ARG_LANGUAGE) ?: ""
    }
    private val api: SearchRepositoryApi

    init {
        this.api = RetrofitSearchRepositoryApi()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_repository_list, container, false)

        view.findViewById<RecyclerView>(R.id.recyclerView).run {
            layoutManager = LinearLayoutManager(activity)
            adapter = RecyclerAdapter(context, listOf())
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        api.searchRepositoryByLanguage(language!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { repositoryList ->
                    view?.let {
                        val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView)
                        recyclerView.swapAdapter(RecyclerAdapter(context!!, repositoryList), false)
                    }
                }
    }

    companion object {
        private const val ARG_LANGUAGE = "ARG_LANGUAGE"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param language
         * @return A new instance of fragment RepositoryListFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(language: String): RepositoryListFragment {
            val fragment = RepositoryListFragment()
            val args = Bundle().apply {
                putString(ARG_LANGUAGE, language)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
