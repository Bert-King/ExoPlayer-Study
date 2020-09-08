package com.bert.studyexoplayer

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * @Author: bertking
 * @ProjectName: StudyExoPlayer
 * @CreateAt: 2020/9/8 7:53 PM
 * @UpdateAt: 2020/9/8 7:53 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Description:
 */

abstract class BaseActivity(var contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {

    val TAG = this::class.java.simpleName

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentLayoutId)
        context = this
        initViews()
        init()
    }

    abstract fun init()

    abstract fun initViews()
}