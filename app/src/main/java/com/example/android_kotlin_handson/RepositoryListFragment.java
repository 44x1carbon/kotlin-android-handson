package com.example.android_kotlin_handson;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link RepositoryListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RepositoryListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LANGUAGE = "ARG_LANGUAGE";


    private String mLanguage;

    public RepositoryListFragment() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repository_list, container, false);
    }
}
