package com.example.deepak.mvvmtestprojectdetail.view.adapter

import android.databinding.BindingAdapter
import android.view.View
@BindingAdapter("visibleGone")
fun  showHide(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}
class  CustomBindingAdapter {


}