package jp.mamori_i.app.screen.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import jp.mamori_i.app.R
import jp.mamori_i.app.screen.home.HomeStatus.HomeStatusType.*
import kotlinx.android.synthetic.main.view_home_card.view.*

class HomeCardView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RelativeLayout(context, attrs, defStyle) {

    interface HomeCardViewEventListener {
        fun onClickDeepContactCountArea()
    }

    var listener: HomeCardViewEventListener? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_home_card, this, true)
        deepContactCountAreaView.setOnClickListener {
            listener?.onClickDeepContactCountArea()
        }
    }

    fun updateContent(status: HomeStatus) {
        // TODO メッセージ
        when (status.statusType) {
            Usual -> {
                mainMessageTextView.text = "一緒に日本を守ってくれて\nありがとうございます"
                subMessageTextView.text = "引き続き接触を控えましょう"
                subMessageTextView.visibility = View.VISIBLE
            }
            SemiUsual -> {
                mainMessageTextView.text = "接触を減らすために\n周りに協力してもらえることは\nありませんか"
                subMessageTextView.text = ""
                subMessageTextView.visibility = View.GONE
            }
        }
        // 共通
        lastUpdateTextView.text = "最終更新 : ${status.updateDatetimeString}"
        contactCountTextView.text = (status.deepContactCount).toString()
    }
}
