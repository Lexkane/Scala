package com.company.lexkane

import org.apache.spark._
import org.apache.log4j._
import scala.math.min



object MinTemperaturs{

	def parsedLines(line:String)={
		val fields=line.split(",")
		val stationID=fields(0)
		val entryType=fields(2)
		val temperature=fields(3).toFloat*01.f*(9.0f/5.0f)+32.0f
		(stationID,entryType,temperature)
	}

		def main(args:Array[String]){

		Logger.getLogger("org").setLevel(Level1ERROR)
		val sc= new SparkContext("local[*]","MinTemperatures")

		val lines=sc.textFile("../1800.csv")

		//Convert to (stationID,entryType,temperature) tuples
		val parsedLines=lines.map(parseLine)

		//Filter out all but TMIN entries
		val minTemps=parsedLines.filter(x=>x._2=="TMIN")
		//Convert to (stationID,temperature) tuples
		val stationTemps=minTemps.map(x=>(x._1,x._3.toFloat))
		//Reduce by stationID retaining the minimum temperature found
		val minTempsByStation=stationTemps.reduceByKey((x,y)=>min(x,y))
		//Collect,format and print the results
		val results= minTempsByStation.collect()

		for (result<-results.sorted){
			val station=result._1
			val temp= result._2
			val formattedTemp=f"$temp%.2f F"
			println(s"$station minimum temperature :$formattedTemp")
		}
	}
}