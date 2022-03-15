package com.mexcelle.everywhendemo.retrofit

import com.mexcelle.everywhendemo.model.*
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @Headers("Content-Type:application/json")
    @POST("/api/v1/users/sign_up")
    fun register(@Header("X-API-KEY") String: Any, @Body registerInputData: RegisterInputData)
            : Call<RegisterResponseData>


    @Headers("Content-Type:application/json")
    @POST("/api/v1/users/sign_in")
    fun login(@Header("X-API-KEY") String: Any, @Body loginInputData: LoginInputData)
            : Call<LoginResponseData>


    @Headers("Content-Type:application/json")
    @POST("/api/v1/verify_otp")
    fun submitOtp(@Header("X-API-KEY") String: Any, @Body otpInputData: OTPInputData)
            : Call<OTPResponseData>


    @Headers("Content-Type:application/json")
    @GET("api/v1/predict_pictures?")
    fun getPredictThePictureQuestionRapid(
        @Header("X-API-KEY") Strin: Any,
        @Header("X-AUTH-TOKEN") String: Any,
        @Query("mode") mode: String,

    )
    : Call<PredictThePictureResponseData>



    @Headers("Content-Type:application/json")
    @POST("/api/v1/verify_otp")
    fun getPredictThePictureQuestionCalm(
        @Header("X-API-KEY") Strin: Any,
        @Header("X-AUTH-TOKEN") String: Any/*,@Path("mode")  mode:String*/
    )
            : Call<PredictThePictureResponseData>

    @Headers("Content-Type:application/json")
    @POST("/api/v1/predict_pictures/")
    fun postPredictThePictureAnswerCalm(
        @Header("X-API-KEY") Strin: Any,
        @Header("X-AUTH-TOKEN") String: Any, @Path("") id: String,
        @Body otpInputData: PredictThePictureCalmAnswerInputData
    )
            : Call<PredictThePictureRecordAnswerResponseData>


    @Headers("Content-Type:application/json")
    @POST("/api/v1/predict_pictures/{id}/answers")
    fun postPredictThePictureAnswerRapid(
        @Header("X-API-KEY") Strin: Any,
        @Header("X-AUTH-TOKEN") String: Any, @Path("id") id: String,
        @Body predictThePictureRapidAnswerInputData: ArrayList<PredictThePictureRapidAnswerInputData?>
    )
            : Call<PredictThePictureRecordAnswerResponseData>


    @Headers("Content-Type:application/json")
    @GET("/api/v1/now_then_question")
    fun getNowAndThenQuestion(
        @Header("X-API-KEY") Strin: Any, @Header("X-AUTH-TOKEN") String: Any
    )
            : Call<NowAndThenResponseData>


    @Headers("Content-Type:application/json")
    @POST("/api/v1/now_then_question/{id}/answer")
    fun postNowAndThenQuestionAnswer(
        @Header("X-API-KEY") Strin: Any,
        @Header("X-AUTH-TOKEN") String: Any,
        @Path("id") id: String,
        @Body nowAndThenAnswerInputData: NowAndThenAnswerInputData
    )
            : Call<NowAndThenAnswerResponseData>

    /*@Headers("Content-Type:application/json")
    @POST("/api/auth/login")
    fun login(@Body loginData:LoginData) : Call<LoginResponseData>


    @Headers("Content-Type:application/json")
    @POST("/api/auth/register")
    fun signUp(@Body signupInputData:SignupInputData) : Call<SignupResponseData>


    @Headers("Content-Type:application/json")
    @GET("/api/causes")
    fun getCause(@Header("Authorization") String: Any) : Call<CauseResponseData>


    @Headers("Content-Type:application/json")
    @GET("/api/activities/upcoming")
    fun getUpcomingActivities(@Header("Authorization") String: Any) : Call<UpcomingActivitiesResponseData>

    @Headers("Content-Type:application/json")
    @GET("/api/user/activities/upcoming")
    fun getProfileUpcomingActivities(@Header("Authorization") String: Any) : Call<ProfileUpcomingActivitiesResponseData>



    @Headers("Content-Type:application/json")
    @POST("/api/user/view-profile")
    fun getProfile(@Header("Authorization") String: Any) : Call<ProfileResponseData>


    @Headers("Content-Type:application/json")
    @PUT("/api/user/edit-profile")
    fun updateProfile(@Header("Authorization") String: Any,@Body profileInputData:ProfileInputData) :
     Call<ProfileUpdateResponseData>


    @Headers("Content-Type:application/json")
    @GET("/api/user/activities/past")
    fun getCompletedActivities(@Header("Authorization") String: Any) : Call<CompletedActivitiesResponseData>


   *//* @Headers("Content-Type:application/json")
    @GET("/api/activities/upcoming")
    fun getCompletedActivities(@Header("Authorization") String: Any) : Call<UpcomingActivitiesResponseData>
*//*


    @Headers("Content-Type:application/json")
    @GET("/api/entity/{entity_id}/cause/{cause_id}/date/{activity_date}")
    fun getCalendarwiseUpcomingActivities(@Header("Authorization") String: Any,@Path("entity_id")  entityId:String,
                                          @Path("cause_id") causeId: String,
                                          @Path("activity_date") activityDate: String) : Call<CalendarwiseResponseData>


    @Headers("Content-Type:application/json")
    @GET("/api/entity/{entity_id}/activity/{activity_id}")
    fun getCharityDetails(@Header("Authorization") String: Any,@Path("entity_id")  entityId:String,@Path("activity_id") activityId: String) :
            Call<ActivityDetailsResponseData>


    @Headers("Content-Type:application/json")
    @POST("api/activity/join")
    fun joinActivity(@Header("Authorization") String: Any,@Body joinEventInputData:JoinEventInputData) : Call<JoinActivityResponseData>

    @Headers("Content-Type:application/json")
    @POST("api/activity/unjoin")
    fun unjoinActivity(@Header("Authorization") String: Any) : Call<UnjoinActivityResponseData>


    @Headers("Content-Type:application/json")
    @POST("api/user/activity/{activity_id}")
    fun startEvent(@Header("Authorization") String: Any,@Path("activity_id") id: String,@Body SsartEventInputData:StartEventInputData) : Call<StartEventResponseData>


    @Headers("Content-Type:application/json")
    fun stopEvent(@Header("Authorization") String: Any, @Path("activity_id") id: String) : Call<StopEventResponseData>

    @Multipart
    @POST("api/user/upload-profile-image")
    fun uploadImage(
        @Header("Authorization") token: String?,
        @Part file: MultipartBody.Part
    ): Call<FileUploadResponse>
*/
}