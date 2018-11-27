package com.example.android_kotlin_handson;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_kotlin_handson.api.RetrofitSearchRepositoryApi;
import com.example.android_kotlin_handson.api.SearchRepositoryApi;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class RepositoryListFragment extends Fragment {
    private static final String ARG_LANGUAGE = "ARG_LANGUAGE";

    private String mLanguage;
    private SearchRepositoryApi api;
    private CompositeDisposable disposables;

    public RepositoryListFragment() {
        this.api = new RetrofitSearchRepositoryApi();
    }

    public static RepositoryListFragment newInstance(String language) {
        RepositoryListFragment fragment = new RepositoryListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LANGUAGE, language);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLanguage = getArguments().getString(ARG_LANGUAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repository_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerAdapter(getContext(), new ArrayList<>()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        disposables = new CompositeDisposable();

        disposables.add(api.searchRepositoryByLanguage(mLanguage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repositoryList -> {
                    View v = getView();
                    if (v != null) {
                        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
                        recyclerView.swapAdapter(new RecyclerAdapter(getContext(), repositoryList), false);
                    }
                }));
    }

    @Override
    public void onStop() {
        super.onStop();
        if (disposables != null) {
            disposables.dispose();
        }
        disposables = null;
    }
}
