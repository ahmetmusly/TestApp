package com.canliskor.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.canliskorapp.R
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */


object Extensions {

    fun Activity.startActivity(
        clazz: Class<*>,
        bundle: Bundle? = null,
        isFinish: Boolean = false,
        isAnim: Boolean = false
    ) {
        val intent = Intent(this, clazz)
//        if (isAnim) intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        if (bundle != null) intent.putExtras(bundle)
        if (isFinish) this.finish()
        startActivity(intent)
        if (isAnim) overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    fun Activity.snackBar(
        message: String = "SNACK_BAR_MESSAGE"
    ): Snackbar {
        return Snackbar.make(
            this.window.decorView.findViewById(android.R.id.content),
            message, Snackbar.LENGTH_SHORT
        )
    }

    fun Context.toast(message: String = "TOAST_MESSAGE", duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    fun myLog(message: String? = "EMPTY") {
        Log.d("TAG_LOG", message!!)
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

    fun View.gone() {
        visibility = View.GONE
    }

    /**
     * Pattern: dd.MM.yyyy HH:mm
     */

    fun String.format(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val date = inputFormat.parse(this)
        return outputFormat.format(date)
    }

    fun managerType(
        spanCount: Int = 1, //StaggeredGridLayoutManager.VERTICAL
        orientation: Int = 0//StaggeredGridLayoutManager.HORIZONTAL
    ): RecyclerView.LayoutManager = StaggeredGridLayoutManager(spanCount, orientation)

    /**  * Inflate the layout specified by [layoutRes].  */
    fun ViewGroup.inflate(layoutRes: Int, root: ViewGroup = this, atr: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, root, atr)
    }

    fun ImageView.loadImage(url: String, ph: Int? = 0) {
        Glide.with(this)
            .load(url)
            // .centerCrop()
            .error(R.drawable.ball_icon)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(ph!!)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }

    fun ImageView.loadCircleImage(url: String, ph: Int? = 0) {
        Glide.with(this)
            .load(url)
            .circleCrop()
            .error(R.drawable.ball_icon)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(ph!!)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

object CSComputation {

    fun screenWidth(): Int = Resources.getSystem().displayMetrics.widthPixels

    fun screenHeight(): Int = Resources.getSystem().displayMetrics.heightPixels

    fun Context.dpToPx(dp: Int): Int =
        (dp * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()

    fun Context.pxToDp(px: Int): Int =
        (px / (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()

    fun isNight(): Boolean {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return (currentHour <= 7 || currentHour >= 18)
    }

    fun Context.getStatusBarHeight(): Int {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else dpToPx(25)
    }
}

object CSKeyboard {
    fun View.showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun Context.showHideKeyboard(edt: EditText?, show: Boolean) {
        edt?.text?.clear()
        val keyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (show) keyboard.showSoftInput(edt!!, 0)
        else keyboard.hideSoftInputFromWindow(edt?.windowToken, 0)
    }
}

object CSFragmentManager {
    fun AppCompatActivity.addFragment(frameId: Int, fragment: Fragment) {
        supportFragmentManager.doTransaction {
//        attach(fragment).addToBackStack(fragment.tag)
            add(frameId, fragment)
        }
    }

    fun AppCompatActivity.replaceFragment(frameId: Int, fragment: Fragment) {
        supportFragmentManager.doTransaction { replace(frameId, fragment) }
    }

    fun AppCompatActivity.removeFragment(fragment: Fragment) {
        supportFragmentManager.doTransaction { remove(fragment) }
    }

    private inline fun FragmentManager.doTransaction(
        func: FragmentTransaction.() ->
        FragmentTransaction
    ) {
        beginTransaction().func().commit()
    }
}