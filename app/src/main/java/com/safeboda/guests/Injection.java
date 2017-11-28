package com.safeboda.guests;

import android.content.Context;
import android.support.annotation.NonNull;

import com.safeboda.guests.guest.GuestContract;
import com.safeboda.guests.guest.GuestPresenter;
import com.safeboda.guests.guest.GuestRepository;

class Injection {
    static GuestPresenter provideGuestPresenter(@NonNull GuestContract.View view,
                                                @NonNull GuestRepository guestRepository) {
        return new GuestPresenter(view, guestRepository);
    }

    static GuestRepository provideGuestRepository(@NonNull Context context) {
        return new GuestRepository(context);
    }
}
