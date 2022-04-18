package com.mexcelle.thoughtifydemo.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.addisonelliott.segmentedbutton.SegmentedButtonGroup.OnPositionChangedListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.databinding.PurchaseFlairFragmentBinding
import com.mexcelle.thoughtifydemo.model.LoginResponseData
import com.mexcelle.thoughtifydemo.repository.ProfileFragmentRepository
import com.mexcelle.thoughtifydemo.repository.ProfileScreenFragmentRepository
import com.mexcelle.thoughtifydemo.retrofit.RetrofitClient
import com.mexcelle.thoughtifydemo.ui.adapter.LeaderBoardAdapter
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import com.mexcelle.thoughtifydemo.viewModel.PurchaseFlairViewModel
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.android.synthetic.main.fragment_now_and_then_ques.*
import kotlinx.android.synthetic.main.fragment_purchase_flair.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PurchaseFlairFragment : Fragment() {

    private lateinit var purchaseFlairViewModel: PurchaseFlairViewModel
    private var _binding: PurchaseFlairFragmentBinding? = null
    val profileResponseData = MutableLiveData<LoginResponseData?>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var mActivity: Activity
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        purchaseFlairViewModel = ViewModelProvider(this).get(PurchaseFlairViewModel::class.java)
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_purchase_flair, container, false
        )
        binding.setLifecycleOwner(this)
        binding.purchaseFlairViewModel = purchaseFlairViewModel
        return binding.getRoot()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (isAdded && requireActivity() != null) {

            if (activity != null) {
                mActivity = requireActivity()
                Log.e("", "here in is added");
                init()

            }

        } else {

            Log.e("", "here in is not added");

        }
        Log.e("in INIT", "in INIT activity created");

    }

    private fun init() {

        val window = mActivity?.window
        navController = findNavController()
        setTextFont()
        getUserStats()


        buttonGroup_fame_and_leagues.setOnPositionChangedListener(OnPositionChangedListener {
            // Handle stuff here
            if (buttonGroup_fame_and_leagues.getPosition() == 0) {

                getLeaderBoardData()

            } else {


            }
        })
    }

    private fun getUserStats() {

        val call = RetrofitClient.apiInterface.getUserProfile(
            Constants.AUTH_TOKEN,
            Constants.USER_AUTHTOKEN,

            )
        call.enqueue(object : Callback<LoginResponseData?> {
            override fun onFailure(call: Call<LoginResponseData?>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())



            }

            override fun onResponse(
                call: Call<LoginResponseData?>,
                response: Response<LoginResponseData?>
            ) {

                if (response.body() != null) {

                    if (response.body().toString() != null) {

                        val data = response.body()
                        Log.e(
                            "loginResponseData !! !!",
                            "loginResponseData !! !!" + ProfileScreenFragmentRepository.profileResponseData
                        );
                        profileResponseData.value = data!!


                        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.logo1)
                            .error(R.drawable.logo1)
                            .skipMemoryCache(false)
                            .dontAnimate()


                        Glide.with(logged_in_user_img.getContext())
                            .asBitmap()
                            .load(Constants.BASE_URL+"/"+profileResponseData.value!!.avatar)
                            .apply(requestOptions)
                            .into(logged_in_user_img);

                    } else {

                        Utility.hideProgressDialog(mActivity)
                        Utility.showDialog(mActivity, "Error !!", "Server Error.", "Ok", "Cancel")
                    }
                } else {

                    val jsonObject = JSONObject(response.errorBody()?.string())
                    var msg: String = ""

                    if (jsonObject.has("message")) {

                        msg = (jsonObject.getString("message"))
                        Utility.hideProgressDialog(mActivity)
                        Utility.showDialog(mActivity, "Error !!", "" + msg, "Ok", "Cancel")
                    } else {

                        Utility.hideProgressDialog(mActivity)
                        Utility.showDialog(mActivity, "Error !!", "Server Error.", "Ok", "Cancel")
                    }

                }

            }
        })

    }

    private fun setTextFont() {

        Utility.setRegular(mActivity, txt11)
        Utility.setRegular(mActivity, txt33)

        getLeaderBoardData()

    }

    private fun getLeaderBoardData() {

        if (Utility.isOnline(mActivity)) {

            Utility.showProgressDialog(mActivity)
            purchaseFlairViewModel.getLeaderBoard(mActivity)!!
                .observe(requireActivity(), Observer { leaderBoardResponseData ->
                    Utility.hideProgressDialog(mActivity)
                    Log.e(
                        "leaderBoardResponseData ",
                        "leaderBoardResponseData " + leaderBoardResponseData
                    );

                    val llm = LinearLayoutManager(mActivity)
                    llm.orientation = LinearLayoutManager.VERTICAL



                    if (leader_board_recyclerView != null) {

                        leader_board_recyclerView.visibility = View.VISIBLE
                        leagues_recyclerView.visibility = View.GONE

                        leader_board_recyclerView.setAdapter(null)
                        leader_board_recyclerView.setLayoutManager(llm);
                        leader_board_recyclerView!!.setAdapter(
                            LeaderBoardAdapter(
                                mActivity,
                                leaderBoardResponseData!!.all,
                                object : LeaderBoardAdapter.OnItemClickListener {
                                    override fun onItemClick(answer: String?) {

                                        buttonGroup_fame_and_leagues.setPosition(1,true)
                                        getLeaguesData(answer!!)
                                    }

                                })
                        )

                        var isFound: Boolean = false

                        for (i in 0 until leaderBoardResponseData!!.all.size) {

                            if(Constants.USER_ID == leaderBoardResponseData!!.all[i].userId){

                                val requestOptions =
                                    RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .skipMemoryCache(false)
                                        .dontAnimate()


                                Glide.with(logged_in_user_img.getContext())
                                    .asBitmap()
                                    .load(Constants.BASE_URL + "/" + leaderBoardResponseData.all!!.get(i).avatar)
                                    .apply(requestOptions)
                                    .into(logged_in_user_img);


                                logged_in_user_name.text = leaderBoardResponseData!!.all[i].username
                                logged_in_user_score.text = leaderBoardResponseData!!.all[i].points
                                isFound = true
                                break
                            }
                        }


                        if(!isFound){

                            val requestOptions =
                                RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .skipMemoryCache(false)
                                    .dontAnimate()


                            Glide.with(logged_in_user_img.getContext())
                                .asBitmap()
                                .load(Constants.BASE_URL + "/" + Constants.USER_IMAGE)
                                .apply(requestOptions)
                                .into(logged_in_user_img);


                            logged_in_user_name.text = Constants.USER_NAME
                            logged_in_user_score.text = "0"
                        }

                        val requestOptions =
                            RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                                .skipMemoryCache(false)
                                .dontAnimate()


                        Glide.with(image1.getContext())
                            .asBitmap()
                            .load(Constants.BASE_URL + "/" + leaderBoardResponseData.all!!.get(0).avatar)
                            .apply(requestOptions)
                            .into(image1);


                        Glide.with(image2.getContext())
                            .asBitmap()
                            .load(Constants.BASE_URL + "/" + leaderBoardResponseData.all!!.get(1).avatar)
                            .apply(requestOptions)
                            .into(image2);

                        Glide.with(image3.getContext())
                            .asBitmap()
                            .load(Constants.BASE_URL + "/" + leaderBoardResponseData.all!!.get(3).avatar)
                            .apply(requestOptions)
                            .into(image3);
                    }


                })

        } else {

            Utility.showDialog(
                mActivity,
                "Network Error !!",
                "Please check your network connection.",
                "Ok",
                "Cancel"
            )

        }
    }

    private fun getLeaguesData(id: String) {

        if (Utility.isOnline(mActivity)) {

            Utility.showProgressDialog(mActivity)
            purchaseFlairViewModel.getLeaguesData(mActivity, id)!!
                .observe(requireActivity(), Observer { leaguesResponseData ->
                    Utility.hideProgressDialog(mActivity)
                    Log.e(
                        "leaguesResponseData ",
                        "leaguesResponseData " + leaguesResponseData
                    );


                    val llm = LinearLayoutManager(mActivity)
                    llm.orientation = LinearLayoutManager.VERTICAL



                    if (leagues_recyclerView != null) {

                        leader_board_recyclerView.visibility = View.GONE
                        leagues_recyclerView.visibility = View.VISIBLE


                        leagues_recyclerView.setAdapter(null)
                        leagues_recyclerView.setLayoutManager(llm);
                        leagues_recyclerView!!.setAdapter(
                            LeaderBoardAdapter(
                                mActivity,
                                leaguesResponseData!!.all,
                                object : LeaderBoardAdapter.OnItemClickListener {
                                    override fun onItemClick(answer: String?) {


                                    }

                                })
                        )

                        val requestOptions =
                            RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                                .skipMemoryCache(false)
                                .dontAnimate()



                        Glide.with(image1.getContext())
                            .asBitmap()
                            .load(Constants.BASE_URL + "/" + leaguesResponseData.all!!.get(0).avatar)
                            .apply(requestOptions)
                            .into(image1);


                        Glide.with(image2.getContext())
                            .asBitmap()
                            .load(Constants.BASE_URL + "/" + leaguesResponseData.all!!.get(1).avatar)
                            .apply(requestOptions)
                            .into(image2);

                        Glide.with(image3.getContext())
                            .asBitmap()
                            .load(Constants.BASE_URL + "/" + leaguesResponseData.all!!.get(3).avatar)
                            .apply(requestOptions)
                            .into(image3);

                    }
                })

        } else {

            Utility.showDialog(
                mActivity,
                "Network Error !!",
                "Please check your network connection.",
                "Ok",
                "Cancel"
            )

        }
    }

}