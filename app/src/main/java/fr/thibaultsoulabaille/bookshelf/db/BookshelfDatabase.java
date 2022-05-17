package fr.thibaultsoulabaille.bookshelf.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Shelve.class, Book.class}, version=8, exportSchema = false)
public abstract class BookshelfDatabase extends RoomDatabase {

    public abstract ShelveDao shelveDao();
    public abstract BookDao bookDao();

    private static volatile BookshelfDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static BookshelfDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BookshelfDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BookshelfDatabase.class, "bookshelve_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                ShelveDao daoShelve = INSTANCE.shelveDao();;
                daoShelve.deleteAll();

                BookDao daoBook = INSTANCE.bookDao();;
                daoBook.deleteAll();
            });
        }
    };
}
