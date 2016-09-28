package net.yslibrary.monotweety.base

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.bluelinelabs.conductor.*
import net.yslibrary.monotweety.base.di.Names
import net.yslibrary.rxeventbus.EventBus
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by yshrsmz on 2016/09/24.
 */
abstract class BaseActivity : AppCompatActivity() {

  @field:[Inject Named(Names.FOR_ACTIVITY)]
  lateinit var activityBus: EventBus

  protected abstract val layoutResId: Int
  protected lateinit var router: Router
    private set

  protected abstract val container: ChangeHandlerFrameLayout
  protected abstract val rootController: Controller

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layoutResId)

    router = Conductor.attachRouter(this, container, savedInstanceState)
    if (!router.hasRootController()) {
      router.setRoot(RouterTransaction.with(rootController))
    }
  }

  override fun onBackPressed() {
    if (!router.handleBack()) {
      super.onBackPressed()
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    router.onActivityResult(requestCode, resultCode, data)
  }

  fun showSnackBar(message: String) = Snackbar.make(container.parent as ViewGroup, message, Snackbar.LENGTH_SHORT).show()
}