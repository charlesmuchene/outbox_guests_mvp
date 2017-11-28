package com.safeboda.guests.guest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GuestPresenterTest {

    @Mock
    GuestContract.View guestView;

    @Mock
    GuestRepository guestRepository;

    private GuestPresenter guestPresenter;

    @Before
    public void setup() {

        guestPresenter = new GuestPresenter(guestView, guestRepository);

    }

    @Test
    public void start() throws Exception {

        guestPresenter.start();

        verify(guestView).init();

    }

    @Test
    public void saveGuest() {

        guestPresenter.saveGuest("name");

        /*when(TextUtils.isEmpty(anyString())).thenReturn(true);*/

        verify(guestView).showMessage(anyInt());

    }

}