package fr.thibaultsoulabaille.bookshelf.ui;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import fr.thibaultsoulabaille.bookshelf.R;

public class ShelveViewHolder extends RecyclerView.ViewHolder
        implements View.OnCreateContextMenuListener {

    private final TextView shelveItemView;

    private ShelveViewHolder(View itemView) {
        super(itemView);
        shelveItemView = itemView.findViewById(R.id.shelveName);
        itemView.setOnCreateContextMenuListener(this);
    }

    public void bind(String text) {
        shelveItemView.setText(text);
    }

    static ShelveViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_shelve, parent, false);
        return new ShelveViewHolder(view);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(this.getAdapterPosition(), v.getId(), 0, "Rename shelve");
        menu.add(this.getAdapterPosition(), v.getId(), 0, "Delete shelve");
    }
}
