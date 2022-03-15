package com.mexcelle.everywhendemo.model

data class NowAndThenQuestionResponseData(

    val id: String,
    val title: String,
    val type: String,
    val options: ArrayList<String>,
    val publishDate : String


    )

/*


"id":"62305d5ef194bd1b5b5d1650",
"title":"How many people will watch the first UEFA champion game via streaming?",
"type":"binary",
"options":[
"Yes",
"No"
],*/
