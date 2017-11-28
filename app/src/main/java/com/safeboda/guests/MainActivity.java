package com.safeboda.guests;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
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
import com.safeboda.guests.guest.GuestContract;
import com.safeboda.guests.guest.GuestPresenter;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements GuestContract.View {

  private TextInputEditText nameEditText;
  private ArrayAdapter<String> namesAdapter;
  private GuestContract.Presenter guestPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);
    guestPresenter = Injection.provideGuestPresenter(this, Injection.provideGuestRepository(this));
    guestPresenter.start();
    guestPresenter.loadGuestList();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.menu_home, menu);

    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {

      case R.id.clear_menu:
        guestPresenter.deleteGuestList();

        break;
    }

    return true;
  }

  @Override public void init() {
    setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

    ListView listView = (ListView) findViewById(R.id.name_list_view);

    Button submitButton = (Button) findViewById(R.id.submit_button);

    nameEditText = (TextInputEditText) findViewById(R.id.name_edit_text);

    namesAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);

    listView.setAdapter(namesAdapter);

    submitButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        String name = nameEditText.getText().toString().trim();
        guestPresenter.saveGuest(name);
      }
    });
  }

  @Override public void addGuest(@NonNull String name) {
    namesAdapter.add(name);
    nameEditText.setText("");
    Toast.makeText(this, String.format("%s added to the list", name), Toast.LENGTH_SHORT).show();
  }

  @Override public void displayAllGuest(@NonNull Set<String> guestList) {
    namesAdapter.addAll(guestList);
  }

  @Override public void clearGuestList() {
    namesAdapter.clear();
    Toast.makeText(this, "All names cleared!", Toast.LENGTH_SHORT).show();
  }

  @Override public void showMessage(@StringRes int messageId) {
    Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
  }
}
