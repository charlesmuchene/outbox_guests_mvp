package com.safeboda.guests;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String NAMES_KEY = "guest_list";
    private Set<String> namesSet;
    private SharedPreferences preferences;
    private TextInputEditText nameEditText;
    private ArrayAdapter<String> namesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        namesSet = new HashSet<>();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        /* UI work */

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        ListView listView = (ListView) findViewById(R.id.name_list_view);

        Button submitButton = (Button) findViewById(R.id.submit_button);

        nameEditText = (TextInputEditText) findViewById(R.id.name_edit_text);

        namesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);

        /* Logic */

        listView.setAdapter(namesAdapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addNameToListAndPersist();

            }
        });

        if (preferences.contains(NAMES_KEY)) {

            namesSet = preferences.getStringSet(NAMES_KEY, new HashSet<String>());

            if (!namesSet.isEmpty()) namesAdapter.addAll(namesSet);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.clear_menu:

                nameEditText.setText("");

                namesSet.clear();

                namesAdapter.clear();

                preferences.edit().clear().apply();

                Toast.makeText(this, "All names cleared!", Toast.LENGTH_SHORT).show();

                break;
        }

        return true;
    }

    /**
     * Add names to the list and persist it
     */
    private void addNameToListAndPersist() {

        String name = nameEditText.getText().toString();

        namesSet.add(name);

        namesAdapter.add(name);

        nameEditText.setText("");

        preferences.edit().putStringSet(NAMES_KEY, namesSet).apply();

        Toast.makeText(this, String.format("%s added to the list", name), Toast.LENGTH_SHORT).show();

    } // addNameToListAndPersist

}
