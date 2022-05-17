package fr.thibaultsoulabaille.bookshelf.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {

    // allowing the insert of books with the same title by
    // passing a conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Book book);

    @Delete
    void delete(Book book);

    @Query("DELETE FROM book_table")
    void deleteAll();

    @Query("SELECT * FROM book_table WHERE book_title = :title")
    LiveData<Book> selectByTitle(String title);

    @Query("SELECT * FROM book_table WHERE author_surname = :authorSurname ORDER BY book_title")
    LiveData<List<Book>> selectByAuthorSurname(String authorSurname);

    @Query("SELECT * FROM book_table WHERE release_year = :year ORDER BY book_title")
    LiveData<List<Book>> selectByReleaseYear(int year);

    @Query("SELECT * FROM book_table ORDER BY book_title ASC")
    LiveData<List<Book>> getBooksOrderedByTitle();

    @Query("SELECT * FROM book_table ORDER BY author_surname ASC")
    LiveData<List<Book>> getBooksOrderedByAuthorSurname();

    @Query("SELECT * FROM book_table ORDER BY release_year")
    LiveData<List<Book>> getBooksOrderedByReleaseYear();
}
