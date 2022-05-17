package fr.thibaultsoulabaille.bookshelf.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book_table")
public class Book {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "book_title")
    private String bookTitle;

    @ColumnInfo(name = "author_surname")
    private String authorSurname;

    @ColumnInfo(name = "author_name")
    private String authorName;

    @ColumnInfo(name = "release_year")
    private int releaseYear;

    @ColumnInfo(name = "page_number")
    private int pageNumber;

    @ColumnInfo(name = "shelve_id")
    private int shelveId;

    public Book() {
        this.bookTitle = "new book";
    }

    public Book(@NonNull String title) {
        this.bookTitle = title;
    }

    public Book(@NonNull String title, String surname) {
        this.bookTitle = title;
        this.authorSurname = surname;
    }

    public Book(@NonNull String title, String surname, String name) {
        this.bookTitle = title;
        this.authorSurname = surname;
        this.authorName = name;
    }

    public Book(@NonNull String title, String surname, String name, int year) {
        this.bookTitle = title;
        this.authorSurname = surname;
        this.authorName = name;
        this.releaseYear = year;
    }

    public Book(@NonNull String title, String surname, String name, int year, int pages) {
        this.bookTitle = title;
        this.authorSurname = surname;
        this.authorName = name;
        this.releaseYear = year;
        this.pageNumber = pages;
    }

    public Book(@NonNull String title, String surname, String name, int year, int pages, int id) {
        this.bookTitle = title;
        this.authorSurname = surname;
        this.authorName = name;
        this.releaseYear = year;
        this.pageNumber = pages;
        this.shelveId = id;
    }

    @NonNull
    public String getBookTitle() {
        return this.bookTitle;
    }

    public String getAuthorSurname() {
        return this.authorSurname;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public int getShelveId() {
        return this.shelveId;
    }

    public void setBookTitle(@NonNull String title) {
        this.bookTitle = title;
    }

    public void setAuthorSurname(String surname) {
        this.authorSurname = surname;
    }

    public void setAuthorName(String name) {
        this.authorName = name;
    }

    public void setReleaseYear(int year) {
        this.releaseYear = year;
    }

    public void setPageNumber(int pages) {
        this.pageNumber = pages;
    }

    public void setShelveId(int id) {
        this.shelveId = id;
    }
}
