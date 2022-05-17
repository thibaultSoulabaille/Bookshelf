package fr.thibaultsoulabaille.bookshelf.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import fr.thibaultsoulabaille.bookshelf.R;
import fr.thibaultsoulabaille.bookshelf.db.Shelve;
import fr.thibaultsoulabaille.bookshelf.ui.dialog.AddShelveDialogFragment;
import fr.thibaultsoulabaille.bookshelf.ui.dialog.EditShelveDialogFragment;

public class ShelvesFragment extends Fragment
        implements AddShelveDialogFragment.AddShelveDialogListener, EditShelveDialogFragment.EditShelveDialogListener {

    private ShelveViewModel mShelveViewModel;

    // String used when a shelve is renamed
    private String rename;

    RecyclerView recyclerView;

    public ShelvesFragment() {
        super(R.layout.fragment_shelves);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.shelves_recyclerview);
        final ShelveListAdapter adapter = new ShelveListAdapter(new ShelveListAdapter.ShelveDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mShelveViewModel = new ViewModelProvider(this).get(ShelveViewModel.class);

        mShelveViewModel.getAllShelves().observe(getViewLifecycleOwner(), shelves -> {
            adapter.submitList(shelves);
        });

        ExtendedFloatingActionButton fab = view.findViewById(R.id.add_shelve_fab);
        fab.setOnClickListener(view1 -> {
            openAddShelveDialog(view1);
        });
    }

    public void openAddShelveDialog(View view) {
        AddShelveDialogFragment addShelveDialogFragment = new AddShelveDialogFragment();
        addShelveDialogFragment.show(getChildFragmentManager(), "add shelve");
    }

    public void openEditShelveDialog(View view) {
        EditShelveDialogFragment editShelveDialogFragment = new EditShelveDialogFragment();
        editShelveDialogFragment.show(getChildFragmentManager(), "edit shelve");
    }

    @Override
    public void createNewShelve(String shelveName) {
        Shelve shelve = new Shelve(shelveName);
        mShelveViewModel.insert(shelve);
    }

    @Override
    public void editShelve(String newName) {
        rename = newName;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = item.getGroupId();

        ShelveListAdapter adapter = (ShelveListAdapter) recyclerView.getAdapter();
        Shelve selectedShelve = adapter.getShelve(position);

        switch (item.getTitle().toString()) {
            case "Rename shelve":
                openEditShelveDialog(item.getActionView());

                return true;

            case "Delete shelve":
                mShelveViewModel.delete(selectedShelve);

                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}
