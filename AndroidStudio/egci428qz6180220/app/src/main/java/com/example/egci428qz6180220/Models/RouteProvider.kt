package com.example.egci428qz6180220.Models

/*
 * Created by lalita on 28/11/2021 AD.
 */

object RouteProvider {

    private val data = ArrayList<ARoute>()
    fun getData(): ArrayList<ARoute> {
        return data
    }

    init {

        data.add(
            ARoute(
                "route1",
                arrayOf(APoint(13.7946, 100.3234), APoint(18.7061, 98.9817))
            )
        )
        data.add(
            ARoute(
                "route2",
                arrayOf(APoint(13.7946, 100.3234), APoint(7.9519, 98.3381))
            )
        )
    }
}