package fr.thibaultsoulabaille.bookshelf.ui;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import fr.thibaultsoulabaille.bookshelf.R;

public class BookViewHolder extends RecyclerView.ViewHolder
        implements View.OnCreateContextMenuListener {

    private final TextView bookTitleView;
    private final TextView authorSurnameView;
    private final TextView authorNameView;
    private final TextView releaseYearView;
    private final TextView pageNumberView;

    private BookViewHolder(View itemView) {
        super(itemView);

        bookTitleView = itemView.findViewById(R.id.bookTitle);
        authorSurnameView = itemView.findViewById(R.id.authorSurname);
        authorNameView = itemView.findViewById(R.id.authorName);
        releaseYearView = itemView.findViewById(R.id.releaseYear);
        pageNumberView = itemView.findViewById(R.id.pageNumber);

        itemView.setOnCreateContextMenuListener(this);
    }

    public void bind(String title, String authorSurname, String authorName, int releaseYear, int pageNumber) {
        bookTitleView.setText(title);
        authorSurnameView.setText(authorSurname);
        authorNameView.setText(authorName);
        releaseYearView.setText(Integer.toString(releaseYear));
        pageNumberView.setText(Integer.toString(pageNumber) + " pages");
    }

    static BookViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(this.getAdapterPosition(), v.getId(), 0, "Rename book");
        menu.add(this.getAdapterPosition(), v.getId(), 0, "Delete book");
    }
}
