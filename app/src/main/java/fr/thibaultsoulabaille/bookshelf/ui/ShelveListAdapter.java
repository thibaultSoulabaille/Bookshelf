package fr.thibaultsoulabaille.bookshelf.ui;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import fr.thibaultsoulabaille.bookshelf.db.*;

public class ShelveListAdapter extends ListAdapter<Shelve, ShelveViewHolder> {

    public ShelveListAdapter(@NonNull DiffUtil.ItemCallback<Shelve> diffCallBack) {
        super(diffCallBack);
    }

    @Override
    public ShelveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ShelveViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ShelveViewHolder holder, int position) {
        Shelve current = getItem(position);
        holder.bind(current.getShelveName());
    }

    public Shelve getShelve(int position) {
        return getItem(position);
    }

    static class ShelveDiff extends DiffUtil.ItemCallback<Shelve> {

        @Override
        public boolean areItemsTheSame(@NonNull Shelve oldItem, @NonNull Shelve newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Shelve oldItem, @NonNull Shelve newItem) {
            return oldItem.getShelveName().equals(newItem.getShelveName());
        }
    }
}
