package fr.thibaultsoulabaille.bookshelf.db;

import android.app.Application;

import androidx.lifecycle.LiveData;
import java.util.List;

public class BookRepository {

    private BookDao mBookDao;
    private LiveData<List<Book>> mAllBooks;

    public BookRepository(Application application) {
        BookshelfDatabase db = BookshelfDatabase.getDatabase(application);
        mBookDao = db.bookDao();
        mAllBooks = mBookDao.getBooksOrderedByTitle();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Book>> getAllBooks() {
        return mAllBooks;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Book book) {
        BookshelfDatabase.databaseWriteExecutor.execute(() -> {
            mBookDao.insert(book);
        });
    }

    public void delete(Book book) {
        BookshelfDatabase.databaseWriteExecutor.execute(() -> {
            mBookDao.delete(book);
        });
    }

    public void deleteAll() {
        BookshelfDatabase.databaseWriteExecutor.execute(() -> {
            mBookDao.deleteAll();
        });
    }
}
