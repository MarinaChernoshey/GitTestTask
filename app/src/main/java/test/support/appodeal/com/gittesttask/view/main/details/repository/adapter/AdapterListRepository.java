package test.support.appodeal.com.gittesttask.view.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.model.Repository;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.util.ItemClickListener;

public class AdapterListRepository extends RecyclerView.Adapter<AdapterListRepository.ViewHolderItem> {

    private List<Repository> repositories = new ArrayList<>();

    private ItemClickListener itemClickListener;

    public AdapterListRepository(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void updateData(List<Repository> repositoriesNew) {
        this.repositories.addAll(repositoriesNew);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_repository, viewGroup, false);
        return new ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItem viewHolder, int i) {
        viewHolder.textViewName.setText(repositories.get(i).getName());
        viewHolder.textViewType.setText(repositories.get(i).getType());
        viewHolder.textViewForks.setText(String.valueOf(repositories.get(i).getForksCount()));
        viewHolder.textViewWatchers.setText(String.valueOf(repositories.get(i).getWatchersCount()));
        viewHolder.textViewStargazers.setText(String.valueOf(repositories.get(i).getStargazersCount()));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {

        @BindView(R.id.constraint_layout_item_rep)
        ConstraintLayout constraintLayout;
        @BindView(R.id.tv_rep_name)
        TextView textViewName;
        @BindView(R.id.tv_rep_type)
        TextView textViewType;
        @BindView(R.id.tv_rep_forks_count)
        TextView textViewForks;
        @BindView(R.id.tv_rep_watchers_count)
        TextView textViewWatchers;
        @BindView(R.id.tv_rep_stargazers_count)
        TextView textViewStargazers;

        ViewHolderItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> itemClickListener.onClick(v, getAdapterPosition()));
        }
    }
}
