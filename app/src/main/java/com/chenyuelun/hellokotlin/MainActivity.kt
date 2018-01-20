package com.chenyuelun.hellokotlin

import android.os.Bundle
import base.BaseActiviy
import com.example.chenyuelun.hellokotlin.R

class MainActivity : BaseActiviy() {

    override fun onCreate(savedInstanceState: Bundle?) {
        isNetNecessary =false
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
