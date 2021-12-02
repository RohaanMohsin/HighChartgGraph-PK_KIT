package it.app.android.progressindicator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.hichartsclasses.*
import com.highsoft.highcharts.core.HIChartView
import java.util.*


var titlelist =  arrayListOf<String>("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep")
var lowlist =  arrayListOf(
    arrayListOf(55,65),
    arrayListOf(60,70),
    arrayListOf(70,79),
    arrayListOf(75,89),
    arrayListOf(82,99),
    arrayListOf(54,61),
    arrayListOf(56,70),
    arrayListOf(64,79),
    arrayListOf(75,89)
)

var lowerPoint =  arrayListOf(
    arrayListOf("Jan",55),
    arrayListOf("Feb",60),
    arrayListOf("Mar",70),
    arrayListOf("Apr",75),
    arrayListOf("May",82),
    arrayListOf("Jun",54),
    arrayListOf("Jul",56),
    arrayListOf("Aug",64),
    arrayListOf("Sep",75)
)

var upperPoint =  arrayListOf(
    arrayListOf("Jan",65),
    arrayListOf("Feb",70),
    arrayListOf("Mar",79),
    arrayListOf("Apr",89),
    arrayListOf("May",99),
    arrayListOf("Jun",61),
    arrayListOf("Jul",70),
    arrayListOf("Aug",79),
    arrayListOf("Sep",89)
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val chartView = findViewById<View>(R.id.hc) as HIChartView

                                // chart option
        val options = HIOptions()

        val chart = HIChart()
        chart.inverted= false
        chart.zoomType = "xy"
        chart.pinchType = "xy"

        options.chart = chart

                            // Disable chart Legend (bottom names in bottom)
        val legend = HILegend()
        legend.enabled = false
        options.legend = legend

                            // set Title
        val title = HITitle()
        title.text = "Change in Life Expectancy"
        options.title = title

                                // set SubTitle
        val subTitle = HISubtitle()
        subTitle.text = "1960 vs 2018"
        options.subtitle = subTitle

                            // populate x-axis value in chart
        val xAxis = HIXAxis()
        xAxis.categories = titlelist

                             // populate y-axis value and set Title also in chart
        val yAxis = HIYAxis()
        val yTitle = HITitle()
        yTitle.text = "Temperature ( °C )"
        yAxis.title = yTitle

                                            // Add x-axis and y-axis in chart option
        options.xAxis = ArrayList(listOf(xAxis))
        options.yAxis = ArrayList(listOf(yAxis))

                            // Add plot options
        val plotOption = HIPlotOptions()

                            // set padding (thickness) of bars
        val colRange = HIColumnrange()
        colRange.pointPadding = 0.43
        plotOption.columnrange = colRange

                             // add plot-options in main Option
        options?.plotOptions = plotOption

                    // set ToolTip
        val tooltip = HITooltip()
        tooltip.valueSuffix = "°C"


                            // Add middle bar series to show range
        val middleSeries = HISeries()
        middleSeries.name = "Temperatures"
        middleSeries.data = lowlist
        middleSeries.type = "columnrange"



                           // Add upper SCATTER type series to show Marker on the top of bar
        val upperSeries = HISeries()
        upperSeries.data = upperPoint
        upperSeries.type = "scatter"
        upperSeries.enableMouseTracking = false    // this line to disable click event of this series


                            // Add upper SCATTER type series to show Marker on the top of bar
        val lowerSeries = HISeries()
        lowerSeries.data = lowerPoint
        lowerSeries.type = "scatter"

                        // To change the shape of Marker to Circle of this series
        val marker = HIMarker()
        marker.symbol = "circle"
        marker.fillColor = HIColor.initWithName("black")
        lowerSeries.marker = marker
        lowerSeries.enableMouseTracking = false     // this line to disable click event of this series



        options.series = ArrayList(listOf(middleSeries,upperSeries,lowerSeries))


        chartView.options = options
    }
}

