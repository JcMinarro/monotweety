package net.yslibrary.monotweety.setting.domain

import net.yslibrary.monotweety.ConfiguredRobolectricTestRunner
import net.yslibrary.monotweety.data.setting.SettingDataManager
import net.yslibrary.monotweety.data.setting.SettingModule
import net.yslibrary.monotweety.spy
import net.yslibrary.monotweety.verify
import net.yslibrary.monotweety.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment
import rx.observers.TestSubscriber

/**
 * Created by yshrsmz on 2016/11/12.
 */
@RunWith(ConfiguredRobolectricTestRunner::class)
class NotificationEnabledManagerTest {

  lateinit var settingDataManager: SettingDataManager
  lateinit var notificationEnabledManager: NotificationEnabledManager

  lateinit var ts: TestSubscriber<Boolean>

  @Before
  fun setup() {
    val module = SettingModule()
    settingDataManager = spy(module.provideSettingDataManager(module.provideSettingPreferences(RuntimeEnvironment.application)))
    notificationEnabledManager = NotificationEnabledManager(settingDataManager)

    ts = TestSubscriber.create()
  }

  @Test
  fun getAndSet() {
    notificationEnabledManager.get().subscribe(ts)

    notificationEnabledManager.set(true)

    verify(settingDataManager).notificationEnabled()
    verify(settingDataManager).notificationEnabled(true)
    verifyNoMoreInteractions(settingDataManager)

    ts.assertValues(false, true)
    ts.assertNoErrors()
    ts.assertNotCompleted()
  }
}