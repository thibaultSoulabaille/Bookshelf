package fr.thibaultsoulabaille.bookshelf.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShelveDao {

    // allowing the insert of shelves with the same name by
    // passing a conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Shelve shelve);

    @Delete
    void delete(Shelve shelve);

    @Query("DELETE FROM shelve_table")
    void deleteAll();

    @Update
    void updateShelve(Shelve shelve);

    @Query("SELECT * FROM shelve_table WHERE shelve_name = :name")
    LiveData<Shelve> select(String name);

    @Query("SELECT * FROM shelve_table ORDER BY shelve_name ASC")
    LiveData<List<Shelve>> getAlphabetizedShelves();
}
