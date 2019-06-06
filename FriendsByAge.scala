package com.company.lexkane

import org.apache.spark._
import org.apache.spark.SparkConttext._
import org.apache.log4j._


object FreindsByAge{


def parseLine(line:String)={
	val fields=line.split(",")
	val age=fields(2).toInt
	val numFriends=fields(3).toInt
	//Create a tuple of result
	(age,numFriends)
}

	def main(args:Array[String]){

		Logger.getLogger("org").setLevel(Level.ERROR)

		//Create SparkContext
		val sc= new SparkContext("local[*]","FreindsByAge")

		val lines=sc.textFile("../fakefriends/csv")
		
		val rdd=lines.map(parseLine)


		//age is Key and numFriends are the mapValue
		//We use mapValues to convert each numFriends value to a tuple of (numFriends,1)
		//Then we use reduceByKey to sum up the total numFriends and total instances for each age, by
		//adding together all the numFriends values and 1's respectively
		val totalsByAge=rdd.mapValues(x=>(x,1)).reduceByKey((x,y)=>(x._1+y._1,x._2+y._2))

		//rdd.mapValues(x=>(x,1))

		//reduceByKey((x,y)=>(x._1+y._1,x._2+y._2))

		//Now we have tuples of (age,(totalFriends,totalInstances))
		//To compute the average we divide totalFriends/totalInstances for each age
		val averagesByAge=totalsByAge.mapValues(x=>x._1/x._2)
		//Collect the results from the RDD
		val results=averagesByAge.collect()
		//Sort and print the final results
		results.sorted.foreach(println)

	}
}
