/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.jmhossler.roastd.maintask;

import com.google.firebase.auth.FirebaseAuth;

import net.jmhossler.roastd.data.user.UserDataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;

import static android.app.Activity.RESULT_OK;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the implementation of {@link MainPresenter}
 */
public class MainPresenterTest {

  @Mock
  private MainContract.View mMainView;

  @Mock
  private FirebaseAuth mAuth;

  @Mock
  private UserDataSource mUserDataSource;

  @Mock
  private Calendar mCalendar;

  private MainPresenter mMainPresenter;

  @Before
  public void setupTasksPresenter() {
    // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
    // inject the mocks in the test the initMocks method needs to be called.
    MockitoAnnotations.initMocks(this);

    // Get a reference to the class under test
    mMainPresenter = new MainPresenter(mMainView, mAuth, mUserDataSource, mCalendar);
  }

  @Test
  public void createPresenter_setsThePresenterToView() {
    // Get a reference to the class under test
    mMainPresenter = new MainPresenter(mMainView, mAuth, mUserDataSource, mCalendar);

    // Then the presenter is set to the view
    verify(mMainView).setPresenter(mMainPresenter);
  }

  @Test
  public void result_startsLogin() {
    // When adding a new task
    mMainPresenter.result(9002, RESULT_OK);

    // Then add task UI is shown
    verify(mMainView).startLogin();
  }

  @Test
  public void setGreetingLabel_Evening() {
    when(mCalendar.get(anyInt())).thenReturn(18);
    mMainPresenter.setGreetingLabel();

    verify(mMainView).displayGreetingLabel(mMainView.getEveningGreeting());
  }

  @Test
  public void setGreetingLabel_Afternoon() {
    when(mCalendar.get(anyInt())).thenReturn(15);
    mMainPresenter.setGreetingLabel();

    verify(mMainView).displayGreetingLabel(mMainView.getAfternoonGreeting());
  }

  @Test
  public void setGreetingLabel_Morning() {
    when(mCalendar.get(anyInt())).thenReturn(10);
    mMainPresenter.setGreetingLabel();

    verify(mMainView).displayGreetingLabel(mMainView.getMorningGreeting());
  }
}
