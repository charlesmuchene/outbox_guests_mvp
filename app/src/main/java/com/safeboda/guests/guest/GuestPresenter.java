package com.safeboda.guests.guest;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.safeboda.guests.R;
import java.lang.ref.WeakReference;
import java.util.Set;

public class GuestPresenter implements GuestContract.Presenter {
  private WeakReference<GuestContract.View> view;
  @NonNull private final GuestRepository guestRepository;

  public GuestPresenter(@NonNull GuestContract.View view,
      @NonNull GuestRepository guestRepository) {

    this.view = new WeakReference<>(view);
    this.guestRepository = guestRepository;
  }

  @Override public void start() {
    if (view.get() != null) {
      view.get().init();
    }
  }

  @Override public void loadGuestList() {
    if (view.get() != null) {
      Set<String> guestList = guestRepository.getAllGuest();
      if (!guestList.isEmpty()) {
        view.get().displayAllGuest(guestRepository.getAllGuest());
      }
    }
  }

  @Override public void deleteGuestList() {
    guestRepository.clearGuestList();
    if (view.get() != null) {
      view.get().clearGuestList();
    }
  }

  @Override public void saveGuest(@NonNull String name) {

    if (view.get() != null) {
      if (TextUtils.isEmpty(name)) {
        view.get().showMessage(R.string.error_empty_name);
      } else {
        guestRepository.addGuest(name);
        view.get().addGuest(name);
      }
    }
  }
}
