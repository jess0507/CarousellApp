package com.jess.data.test

import com.google.gson.Gson
import com.jess.data.remote.dto.News

object FakeSource {
    val news = Gson().fromJson(
        """[
              {
                "id": "121",
                "title": "Carousell is launching its own digital wallet to improve payments for its users",
                "description": "Due to launch next month in Singapore, CarouPay will allow buyers and sellers to complete transactions without leaving the Carousell app, rather than having to rely on third-party platforms or doing meet-ups to hand over cash. CarouPay will be a digital wallet within the Carousell app. \"More than half of our sellers will end up buying items as well, so maybe it makes sense to have that money in the wallet for purchases\" - Quek tells Tech in Asia.",
                "banner_url": "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg",
                "time_created": 1532853058,
                "rank": 2
              },
              {
                "id": "122",
                "title": "Southeast Asia-based mobile listings startup Carousell raises ${'$'}85M",
                "description": "Carousell, the Singapore-based mobile listing service that operates across Southeast Asia, has pulled in an ${'$'}85 million Series C fund as it seeks to strengthen its business among the region's competitive e-commerce landscape before expanding globally.",
                "banner_url": "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-hero-image_10june.png",
                "time_created": 1532939458,
                "rank": 5
              },
              {
                "id": "123",
                "title": "Tour de France: Geraint Thomas wins as Chris Froome finishes third",
                "description": "The Team Sky rider, 32, follows Sir Bradley Wiggins in 2012 and four-time Tour champion Chris Froome as Britain celebrates a sixth win in seven years. Alexander Kristoff won the final sprint finish on the Champs-Elysees as Thomas crossed the line arm-in-arm with Froome after three weeks of racing.",
                "banner_url": "https://storage.googleapis.com/carousell-interview-assets/android/images/_102749437_thomas_epa.jpg",
                "time_created": 1530322670,
                "rank": 1
              },
              {
                "id": "124",
                "title": "How to Grow Crops on Mars Before Humans Colonize the Red Planet",
                "description": "Preparations are already underway for missions that will land humans on Mars in a decade or so. But what would people eat if these missions eventually lead to the permanent colonization of the red planet?",
                "banner_url": "https://storage.googleapis.com/carousell-interview-assets/android/images/humansonmarsjpg.jpeg",
                "time_created": 1519983341,
                "rank": 1
              },
              {
                "id": "125",
                "title": "NASA's New Planet Hunter Begins Its Search for Alien Worlds",
                "description": "The Transiting Exoplanet Survey Satellite (TESS), which is designed to hunt for alien worlds around stars not too far from the sun, began gathering science data Wednesday (July 25), members of the instrument team announced yesterday (July 27).",
                "banner_url": "https://storage.googleapis.com/carousell-interview-assets/android/images/nasa_alien_hunting.jpeg",
                "time_created": 1527672941,
                "rank": 5
              },
                {
                "id": "126",
                "title": "Carousell funding",
                "description": "Carousell, the Singapore-based mobile listing service that operates across Southeast Asia, has pulled in an ${'$'}85 million Series C fund as it seeks to strengthen its business among the region’s competitive e-commerce landscape before expanding globally.",
                "banner_url": "https://storage.googleapis.com/carousell-interview-assets/android/images/nasa_alien_hunting.jpeg",
                "time_created": 1527672941,
                "rank": 7
              }
            ]
            """.trimIndent(),
        News::class.java
    )
}