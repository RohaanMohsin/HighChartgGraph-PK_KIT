package it.app.android.progressindicator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.highsoft.highcharts.common.hichartsclasses.*
import com.highsoft.highcharts.core.HIChartView
import java.util.ArrayList

class BarChart : AppCompatActivity() {

    var dataListing = ArrayList<Model>()
    var dataChunk = Model()
    var titlelist =  arrayListOf<String>("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep")
    var lowlist =  arrayListOf(54,61,66,70,74,79,85,89,92)
    var highlist = arrayListOf(59,66,72,76,79,83,89,94,99)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart)

        for (x in 0 until titlelist.size){

            dataChunk.name= titlelist[x]
            dataChunk.low= lowlist[x]
            dataChunk.high= highlist[x]
        }



        val chartView = findViewById<View>(R.id.hc) as HIChartView

        val options = HIOptions()

        val chart = HIChart()
//        chart.type = "dumbbell"
//        chart.inverted= true

        options.chart = chart

        val legend = HILegend()
        legend.enabled = false

//        val credits = HICredits()
//        credits.enabled = false

        val title = HITitle()
        title.text = "Change in Life Expectancy"
        options.title = title

        val subTitle = HISubtitle()
        subTitle.text = "1960 vs 2018"
        options.subtitle = subTitle

        val xAxis = HIXAxis()
        xAxis.type = "category"


        val yAxis = HIYAxis()
        val yTitle = HITitle()
        yTitle.text = "Life Expectancy (years)"
        yAxis.title = yTitle

        val series = HISeries()
        val dumbbell = HIDumbbell()

        series.name = "Life expectancy change"
        series.data = dataListing
        series.type = "columnrange"

//        dumbbell.name = "Resting Heart Rate"
//        dumbbell.data = dataListing


        options.series = ArrayList(listOf(series))
//        options.series = ArrayList(listOf(dumbbell))
        series.type =  "dumbbell"

        chartView.options = options
    }
}
