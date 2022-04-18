package com.mexcelle.thoughtifydemo.ui.adapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.model.LeaderBoardResponseDataDetails
import com.mexcelle.thoughtifydemo.util.Constants

class LeaderBoardAdapter(
    val context: Context,
    val mList: ArrayList<LeaderBoardResponseDataDetails>,
    listener: OnItemClickListener
) :
    RecyclerView.Adapter<LeaderBoardAdapter.MyViewHolder>(), View.OnClickListener {

    var mContext: Context
    private val listener: OnItemClickListener
    private var image: String? = null
    private var mAnswer: String? = ""

    var leaderBoardResponseDataDetails: ArrayList<LeaderBoardResponseDataDetails> =
        java.util.ArrayList<LeaderBoardResponseDataDetails>()


    init {

        mContext = context
        this.listener = listener
        this.leaderBoardResponseDataDetails = mList
    }


    interface OnItemClickListener {
        fun onItemClick(answer: String?)

    }

    override fun getItemCount(): Int {
        return leaderBoardResponseDataDetails.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nameTxt: TextView
        var profileImg: ImageView
        var scoreTxt: TextView




        init {
            nameTxt = itemView.findViewById<View>(R.id.name11) as TextView
            profileImg = itemView.findViewById<View>(R.id.user_image) as ImageView
            scoreTxt = itemView.findViewById<View>(R.id.score) as TextView

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.purchase_flair_adapter, parent, false)
        return MyViewHolder(view)
    }

    override fun onClick(v: View?) {


    }

    override fun onBindViewHolder(holder: LeaderBoardAdapter.MyViewHolder, position: Int) {


        Log.e("URL ",""+Constants.BASE_URL+"/"+leaderBoardResponseDataDetails!!.get(position)!!.avatar);
        holder.nameTxt.text = leaderBoardResponseDataDetails!!.get(position).username
        holder.scoreTxt.text = leaderBoardResponseDataDetails!!.get(position).points


        holder.nameTxt.setOnClickListener {
            listener.onItemClick(leaderBoardResponseDataDetails[position].leagueId)
        }


        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(false)
            .placeholder(R.drawable.logo1)
            .error(R.drawable.logo1)
            .dontAnimate()


        Glide.with(holder.profileImg.getContext())
            .asBitmap()
            .load(Constants.BASE_URL+"/"+leaderBoardResponseDataDetails!!.get(position).points)
            .apply(requestOptions)
            .into(holder.profileImg);

    }
}