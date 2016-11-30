package com.safeboda.guests.guest;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import java.util.Set;

public class GuestContract {
  public interface View {
    void init();

    void addGuest(@NonNull String name);

    void displayAllGuest(@NonNull Set<String> guestList);

    void clearGuestList();

    void showMessage(@StringRes int messageId);
  }

  public interface Presenter {
    void start();

    void loadGuestList();

    void deleteGuestList();

    void saveGuest(@NonNull String name);
  }
}
