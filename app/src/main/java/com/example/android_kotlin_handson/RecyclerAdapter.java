package com.example.android_kotlin_handson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_kotlin_handson.model.Repository;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<Repository> mRepositoryList;

    RecyclerAdapter(Context context, List<Repository> repositoryList) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mRepositoryList = repositoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mRepositoryList != null && mRepositoryList.size() > position && mRepositoryList.get(position) != null) {
            Repository repository = mRepositoryList.get(position);
            String text = String.format("[☆%s] %s", repository.stargazersCount, repository.fullName);
            holder.textView.setText(text);
        }
    }

    @Override
    public int getItemCount() {
        if(mRepositoryList != null) {
            return mRepositoryList.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.repositoryName);
        }
    }
}
