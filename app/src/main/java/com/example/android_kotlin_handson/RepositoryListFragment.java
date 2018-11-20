package com.example.android_kotlin_handson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_kotlin_handson.api.HashMapSearchRepositoryApi;
import com.example.android_kotlin_handson.api.RetrofitSearchRepositoryApi;
import com.example.android_kotlin_handson.api.SearchRepositoryApi;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link RepositoryListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RepositoryListFragment extends Fragment {
    private static final String ARG_LANGUAGE = "ARG_LANGUAGE";


    private String mLanguage;
    private SearchRepositoryApi api;

    public RepositoryListFragment() {
        this.api = new RetrofitSearchRepositoryApi();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param language
     * @return A new instance of fragment RepositoryListFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        api.searchRepositoryByLanguage(mLanguage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repositoryList -> {
                    RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
                    recyclerView.setAdapter(new RecyclerAdapter(getContext(), repositoryList));

                });
    }
}
