package com.example.weatherappdpk.helper

import android.content.Context
import android.widget.Toast

fun Context.toastS(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
fun Context.toastL(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()