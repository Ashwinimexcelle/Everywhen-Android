package com.mexcelle.thoughtifydemo.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mexcelle.thoughtifydemo.R

class NowAndThenOptionsRecyclerView(
    val context: Context,
    val mList: ArrayList<String>,
    listener: OnItemClickListener
) :
    RecyclerView.Adapter<NowAndThenOptionsRecyclerView.MyViewHolder>(), View.OnClickListener {

    var mContext: Context
    private val listener: OnItemClickListener
    private var image: String? = null
    private var mAnswer: String? = ""

    var nowAndThenOptions: ArrayList<String> =
        java.util.ArrayList<String>()


    init {

        mContext = context
        this.listener = listener
        this.nowAndThenOptions = mList
    }


    interface OnItemClickListener {
        fun onItemClick(answer: String?)

    }

    override fun getItemCount(): Int {
        return nowAndThenOptions.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var optionsTxt: TextView

        init {
            optionsTxt = itemView.findViewById<View>(R.id.opt1) as TextView

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.now_and_then_options, parent, false)
        return MyViewHolder(view)
    }

    override fun onClick(v: View?) {


    }

    override fun onBindViewHolder(holder: NowAndThenOptionsRecyclerView.MyViewHolder, position: Int) {


        Log.e("URL ",""+nowAndThenOptions!!.get(position)!!);
        holder.optionsTxt.text = nowAndThenOptions!!.get(position)

        holder.optionsTxt.setOnClickListener {
            listener.onItemClick(nowAndThenOptions[position])
        }


    }
}