package net.yslibrary.monotweety.base

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bluelinelabs.conductor.Controller
import com.bumptech.glide.Glide


/**
 * shortcut for findViewById
 */
@Suppress("UNCHECKED_CAST")
fun <T : View> View.findById(@IdRes id: Int): T = findViewById<T>(id)

@Suppress("UNCHECKED_CAST")
fun <T : View> ViewGroup.findById(@IdRes id: Int): T = findViewById<T>(id)

@Suppress("UNCHECKED_CAST")
fun <T : View> Activity.findById(@IdRes id: Int): T = findViewById<T>(id)

@Suppress("UNCHECKED_CAST")
fun <T : View> Controller.findById(@IdRes id: Int): T? = view?.findViewById<T>(id)

fun ImageView.load(url: String) {
  Glide.with(this.context)
      .load(url)
      .into(this)
}

fun ViewGroup.inflate(@LayoutRes resource: Int, root: ViewGroup = this, attachToRoot: Boolean = false): View {
  return LayoutInflater.from(this.context).inflate(resource, root, attachToRoot)
}

fun Context.inflate(@LayoutRes resource: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): View {
  return LayoutInflater.from(this).inflate(resource, root, attachToRoot)
}