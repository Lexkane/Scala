package com.company.lexkane

import org.apache.spark._

object RatingsCoutner{

	def main(args:Array[String]){

		//Set the log level to only print errors
		Loggeer.getLogger("org").setLevel(Level.ERROR)

		//Create Context
		val sc= new SparkContext("local[*]","RatingsCoutner")
		//Load up each line of the ratings data into an RDD
		val lines= sc.textFile("../ml-100k/u.data")
		//Convert each line to a string, split it out by tabs and extact the third field
		//field format is userID,movieID,rating,timestamp
		val ratings= lines.map(x=>x.toString().split("\t")(2))
        //Count up how many times each value(rating) occurs
		val results= ratings.countByValue()
		//Sort the resulting map of (rating,count)tuples
		val sortedResults=results.toSeq.sortBy(_._1)
		//Print each result in it's own line
		sortedResults.foreach(println)

	}
}