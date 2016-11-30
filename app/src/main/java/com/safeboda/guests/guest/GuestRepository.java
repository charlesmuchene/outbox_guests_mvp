package com.safeboda.guests.guest;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.HashSet;
import java.util.Set;

public class GuestRepository {

  private static final String NAMES_KEY = "guest_list";
  private final SharedPreferences preferences;

  public GuestRepository(Context context) {
    preferences = PreferenceManager.getDefaultSharedPreferences(context);
  }

  public Set<String> getAllGuest() {
    return preferences.getStringSet(NAMES_KEY, new HashSet<String>());
  }

  public void addGuest(String name) {
    Set<String> guestList = this.getAllGuest();
    guestList.add(name);
    preferences.edit().putStringSet(NAMES_KEY, guestList).apply();
  }

  public void clearGuestList() {
    preferences.edit().clear().apply();
  }
}
