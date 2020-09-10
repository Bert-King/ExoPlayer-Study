package com.bert.studyexoplayer

import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_cover.*

class CoverActivity : BaseActivity(R.layout.activity_cover) {

    override fun init() {
    }

    override fun initViews() {
        Glide.with(context).load(URLs.gameCoverUrl).into(iv_cover)

    }
}