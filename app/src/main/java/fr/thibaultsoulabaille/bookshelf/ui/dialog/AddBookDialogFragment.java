package fr.thibaultsoulabaille.bookshelf.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import fr.thibaultsoulabaille.bookshelf.R;

public class AddBookDialogFragment extends DialogFragment {

    private MaterialToolbar toolbar;

    private TextInputEditText editTextBookTitle;
    private TextInputEditText editTextAuthorSurname;
    private TextInputEditText editTextAuthorName;
    private TextInputEditText editTextReleaseYear;
    private TextInputEditText editTextPageNumber;

    public interface AddBookDialogListener {
        void createNewBook(String bookTitle,
                           String authorSurname,
                           String authorName,
                           int releaseYear,
                           int pageNumber);
    }

    // Use this instance of the interface to deliver action events
    AddBookDialogFragment.AddBookDialogListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.dialog_add_book, container, false);
        toolbar = view.findViewById(R.id.add_book_toolbar);

        return view;
    }*/

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Add Book");
        toolbar.inflateMenu(R.menu.add_book_menu);

        toolbar.setOnMenuItemClickListener(item -> {
            dismiss();
            return true;
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppTheme_FullScreenDialog);

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_add_book, null);

        builder.setView(view)
                .setPositiveButton(R.string.new_shelve_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String bookTitle = editTextBookTitle.getText().toString();
                        String authorSurname = editTextAuthorSurname.getText().toString();
                        String authorName = editTextAuthorName.getText().toString();
                        int releaseYear = Integer.valueOf(editTextReleaseYear.getText().toString());
                        int pageNumber = Integer.valueOf(editTextPageNumber.getText().toString());
                        listener.createNewBook(bookTitle, authorSurname, authorName, releaseYear, pageNumber);
                    }
                })
                .setNegativeButton(R.string.new_shelve_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AddBookDialogFragment.this.getDialog().cancel();
                    }
                });

        editTextBookTitle = view.findViewById(R.id.book_title_text);
        editTextAuthorSurname = view.findViewById(R.id.author_surname_text);
        editTextAuthorName = view.findViewById(R.id.author_name_text);
        editTextReleaseYear = view.findViewById(R.id.release_year_text);
        editTextPageNumber = view.findViewById(R.id.page_number_text);

        // Create the AlertDialog object and return it
        return builder.create();
    }

    // override the Fragment.onAttach() method to instantiate the AddBookDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the AddBookDialogListener so we can send events to the host
            listener = (AddBookDialogFragment.AddBookDialogListener) getParentFragment();
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(" must implement AddBookDialogListener");
        }
    }
}
