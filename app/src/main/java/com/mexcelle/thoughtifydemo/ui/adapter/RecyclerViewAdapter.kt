package com.mexcelle.thoughtifydemo.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.model.PredictThePictureOptions
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*
import kotlin.collections.ArrayList


class RecyclerViewAdapter(
    val answer: String,
    val context: Context,
    val mList: ArrayList<PredictThePictureOptions>,
    listener: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(), View.OnClickListener {

    var mContext: Context
    private val listener: OnItemClickListener
    private var image: String? = null
    private var mAnswer: String? = ""
    private var gyro: ArrayList<Float>? = ArrayList()
    private var coordinates: ArrayList<Double>?  = ArrayList()
    private var pressure: Float? = null



    var predictThePictureResponseDataList: ArrayList<PredictThePictureOptions> =
        java.util.ArrayList<PredictThePictureOptions>()


    init {

        mAnswer = answer
        mContext = context
        this.listener = listener
        this.predictThePictureResponseDataList = mList
    }


    interface OnItemClickListener {
        fun onItemClick(predictThePictureResponseDataList: PredictThePictureOptions?,gyro:ArrayList<Float>?,
                        coordinates: ArrayList<Double>?,pressure: Float?)

    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        Log.e("URL ",""+predictThePictureResponseDataList!!.get(position)!!.image);

       /* RequestOptions reqOpt :RequestOptions = RequestOptions.fitCenterTransform()
            .diskCacheStrategy(DiskCacheStrategy.ALL) // It will cache your image after loaded for first time

*/
        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(false)
            .dontAnimate()


        Glide.with(holder.itemView.getContext())
            .asBitmap()
            .load(predictThePictureResponseDataList!!.get(position)!!.image)
            .apply(requestOptions)
            .into(holder.optionsImage);
         // It will cache your image after loaded for first time


        val r = Random()
        val red: Int = r.nextInt(255 - 0 + 1) + 0
        val green: Int = r.nextInt(255 - 0 + 1) + 0
        val blue: Int = r.nextInt(255 - 0 + 1) + 0

        val draw = GradientDrawable()
        draw.shape = GradientDrawable.OVAL
        draw.setColor(Color.rgb(red, green, blue))
        holder.optionsImage.setBackground(draw)


        holder.optionsImage.setOnClickListener {
            listener.onItemClick(predictThePictureResponseDataList[position],gyro,coordinates,pressure)
        }


        holder.optionsImage.setOnTouchListener(OnTouchListener { view, motionEvent ->

            var action: Int =0;
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                Log.e("event pressure 0", "" + position);
                Log.e("event pressure 1", "" + motionEvent.pressure);
                Log.e("event pressure 2", "" + motionEvent.getAxisValue(0));
                Log.e("event pressure 3", "" + motionEvent.getAxisValue(1));
                Log.e("event pressure 4", "" + motionEvent.getAxisValue(2));
                gyro?.add(motionEvent.getAxisValue(0))
                gyro?.add(motionEvent.getAxisValue(1))
                gyro?.add(motionEvent.getAxisValue(2))

                coordinates?.add(-104.990)
                coordinates?.add(39.7392)
                pressure = motionEvent.pressure
                Log.e("event pressure 5", "" + gyro);

                Log.e("event pressure 6", "" + coordinates);

                Log.e("mAnswer ", "mAnswer " + mAnswer);
                Log.e("position ", "position " + predictThePictureResponseDataList[position].position);



                if(mAnswer.equals(predictThePictureResponseDataList[position].position)){

                    setBorder(holder.optionsImage,true)
                }else{

                    setBorder(holder.optionsImage,false)

                }

                listener.onItemClick (predictThePictureResponseDataList[position],gyro,coordinates,pressure)

            }

            true
        })


    }


    fun setBorder(circularImageView: CircleImageView, selected: Boolean) {

        // Set Border
        if (selected) {
            circularImageView.setBorderColor(context.resources.getColor(R.color.right_ans))
            circularImageView.setBorderWidth(8)
        } else {
            circularImageView.setBorderColor(context.resources.getColor(R.color.wrong_ans))
            circularImageView.setBorderWidth(8)

        }
    }
    override fun getItemCount(): Int {
        return predictThePictureResponseDataList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var optionsImage: CircleImageView

        init {
            optionsImage = itemView.findViewById<View>(R.id.image_op) as CircleImageView

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_view_item, parent, false)
        return MyViewHolder(view)
    }


    override fun onClick(v: View?) {


    }
}