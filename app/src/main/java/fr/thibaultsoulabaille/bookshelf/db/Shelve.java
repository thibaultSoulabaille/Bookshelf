package fr.thibaultsoulabaille.bookshelf.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shelve_table")
public class Shelve {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "shelve_id")
    private int shelveId;

    @NonNull
    @ColumnInfo(name = "shelve_name")
    private String shelveName;

    public Shelve() {
        this.shelveName = "new shelve";
    }

    public Shelve(@NonNull String name) {
        this.shelveName = name;
    }

    public int getShelveId() {
        return shelveId;
    }

    @NonNull
    public String getShelveName() {
        return this.shelveName;
    }

    public void setShelveName(@NonNull String name) {
        this.shelveName = name;
    }

    public void setShelveId(int id) {
        this.shelveId = id;
    }
}
