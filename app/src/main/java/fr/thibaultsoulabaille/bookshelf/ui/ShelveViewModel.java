package fr.thibaultsoulabaille.bookshelf.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.thibaultsoulabaille.bookshelf.db.Shelve;
import fr.thibaultsoulabaille.bookshelf.db.ShelveRepository;

public class ShelveViewModel extends AndroidViewModel {

    private ShelveRepository mRepository;

    private final LiveData<List<Shelve>> mAllShelves;

    public ShelveViewModel(Application application) {
        super(application);
        mRepository = new ShelveRepository(application);
        mAllShelves = mRepository.getAllShelves();
    }

    LiveData<List<Shelve>> getAllShelves() {
        return mAllShelves;
    }

    public void insert(Shelve shelve) {
        mRepository.insert(shelve);
    }

    public void delete(Shelve shelve) {
        mRepository.delete(shelve);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }
}
