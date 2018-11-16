package test.support.appodeal.com.gittesttask.view.main.issues.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.network.pojo.Issue;

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.ViewHolderItem> {


    List<Issue> issues = new ArrayList<>();

    public void setIssues(List<Issue> issues) {
        this.issues.addAll(issues);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_issues, viewGroup, false);
        return new ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItem viewHolderItemUser, int i) {
        viewHolderItemUser.textViewTitle.setText(issues.get(i).getTitle());
        viewHolderItemUser.textViewState.setText(issues.get(i).getState());
        viewHolderItemUser.textViewDate.setText(issues.get(i).getDate());
        viewHolderItemUser.textViewComments.setText(String.valueOf(issues.get(i).getComments()));
        viewHolderItemUser.textViewProject.setText(String.valueOf(issues.get(i).isProject()));
    }

    @Override
    public int getItemCount() {
        return issues.size();
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView textViewTitle;
        @BindView(R.id.tv_project)
        TextView textViewProject;
        @BindView(R.id.tv_date)
        TextView textViewDate;
        @BindView(R.id.tv_comment_number)
        TextView textViewComments;
        @BindView(R.id.tv_state)
        TextView textViewState;


        ViewHolderItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
