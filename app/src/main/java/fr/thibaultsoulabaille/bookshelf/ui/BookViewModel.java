package fr.thibaultsoulabaille.bookshelf.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.thibaultsoulabaille.bookshelf.db.*;

public class BookViewModel extends AndroidViewModel {

    private BookRepository mRepository;

    private final LiveData<List<Book>> mAllBooks;

    public BookViewModel(Application application) {
        super(application);
        mRepository = new BookRepository(application);
        mAllBooks = mRepository.getAllBooks();
    }

    LiveData<List<Book>> getAllBooks() {
        return mAllBooks;
    }

    public void insert(Book book) {
        mRepository.insert(book);
    }

    public void delete(Book book) {
        mRepository.delete(book);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }
}
