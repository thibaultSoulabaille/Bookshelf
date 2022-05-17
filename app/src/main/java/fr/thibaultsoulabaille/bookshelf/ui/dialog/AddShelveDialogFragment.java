package fr.thibaultsoulabaille.bookshelf.ui.dialog;

import androidx.appcompat.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

import fr.thibaultsoulabaille.bookshelf.R;

public class AddShelveDialogFragment extends DialogFragment {

    // input text : name of the new shelve
    private TextInputEditText editTextShelveName;

    public interface AddShelveDialogListener {
        void createNewShelve(String shelveName);
    }

    // Use this instance of the interface to deliver action events
    AddShelveDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_add_shelve, null);

        builder.setView(view)
                .setTitle(R.string.new_shelve)
                .setPositiveButton(R.string.new_shelve_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String shelveName = editTextShelveName.getText().toString();
                        listener.createNewShelve(shelveName);
                    }
                })
                .setNegativeButton(R.string.new_shelve_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AddShelveDialogFragment.this.getDialog().cancel();
                    }
                });

        editTextShelveName = view.findViewById(R.id.new_shelve_name_text);

        // Create the AlertDialog object and return it
        return builder.create();
    }

    // override the Fragment.onAttach() method to instantiate the AddShelveDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the AddShelveDialogListener so we can send events to the host
            listener = (AddShelveDialogListener) getParentFragment();
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(" must implement AddShelveDialogListener");
        }
    }
}