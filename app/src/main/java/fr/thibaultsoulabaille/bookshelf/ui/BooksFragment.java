package fr.thibaultsoulabaille.bookshelf.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import fr.thibaultsoulabaille.bookshelf.R;
import fr.thibaultsoulabaille.bookshelf.db.Book;
import fr.thibaultsoulabaille.bookshelf.db.Shelve;
import fr.thibaultsoulabaille.bookshelf.ui.dialog.AddBookDialogFragment;

public class BooksFragment extends Fragment
        implements AddBookDialogFragment.AddBookDialogListener {

    private BookViewModel mBookViewModel;

    RecyclerView recyclerView;

    public BooksFragment() {
        super(R.layout.fragment_books);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.books_recyclerview);
        final BookListAdapter adapter = new BookListAdapter(new BookListAdapter.BookDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mBookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        mBookViewModel.getAllBooks().observe(getViewLifecycleOwner(), books -> {
            adapter.submitList(books);
        });

        ExtendedFloatingActionButton fab = view.findViewById(R.id.add_book_fab);
        fab.setOnClickListener(view1 -> {
            openAddBookDialog(view1);
        });
    }

    public void openAddBookDialog(View view) {
        AddBookDialogFragment addBookDialogFragment = new AddBookDialogFragment();
        addBookDialogFragment.show(getChildFragmentManager(), "add book");
    }

    @Override
    public void createNewBook(String bookTitle,
                              String authorSurname,
                              String authorName,
                              int releaseYear,
                              int pageNumber) {

        Book book = new Book(bookTitle, authorSurname, authorName, releaseYear, pageNumber);
        mBookViewModel.insert(book);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = item.getGroupId();

        BookListAdapter adapter = (BookListAdapter) recyclerView.getAdapter();
        Book selectedBook = adapter.getBook(position);

        switch (item.getTitle().toString()) {
            case "Rename book":
                return true;

            case "Delete book":
                mBookViewModel.delete(selectedBook);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}

