package fr.thibaultsoulabaille.bookshelf.ui;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import fr.thibaultsoulabaille.bookshelf.db.*;

public class BookListAdapter extends ListAdapter<Book, BookViewHolder> {

    public BookListAdapter(@NonNull DiffUtil.ItemCallback<Book> diffCallBack) {
        super(diffCallBack);
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BookViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book current = getItem(position);

        holder.bind(current.getBookTitle(),
                current.getAuthorSurname(),
                current.getAuthorName(),
                current.getReleaseYear(),
                current.getPageNumber());
    }

    public Book getBook(int position) {
        return getItem(position);
    }

    static class BookDiff extends DiffUtil.ItemCallback<Book> {

        @Override
        public boolean areItemsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.getBookTitle().equals(newItem.getBookTitle());
        }
    }
}
