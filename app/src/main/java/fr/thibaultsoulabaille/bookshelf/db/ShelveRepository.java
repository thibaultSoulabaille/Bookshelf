package fr.thibaultsoulabaille.bookshelf.db;

import android.app.Application;

import androidx.lifecycle.LiveData;
import java.util.List;

public class ShelveRepository {

    private ShelveDao mShelveDao;
    private LiveData<List<Shelve>> mAllShelves;

    // Note that in order to unit test the ShelveRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public ShelveRepository(Application application) {
        BookshelfDatabase db = BookshelfDatabase.getDatabase(application);
        mShelveDao = db.shelveDao();
        mAllShelves = mShelveDao.getAlphabetizedShelves();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Shelve>> getAllShelves() {
        return mAllShelves;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Shelve shelve) {
        BookshelfDatabase.databaseWriteExecutor.execute(() -> {
            mShelveDao.insert(shelve);
        });
    }

    public void delete(Shelve shelve) {
        BookshelfDatabase.databaseWriteExecutor.execute(() -> {
            mShelveDao.delete(shelve);
        });
    }

    public void deleteAll() {
        BookshelfDatabase.databaseWriteExecutor.execute(() -> {
            mShelveDao.deleteAll();
        });
    }
}
