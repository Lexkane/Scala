package com.company.lexkane

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

object TotalSpentByCustomer{

	/**Convert input data to (customerID, amountSpent) tuples */
	def extractCustomerPricePairs (line:String)={
		val fields=line.split(",")
		(fields(0)).toInt, fields(2).toFloat
	}

	def main(args:Array[String]){

		Logger.getLogger("org").setLevel(Level1ERROR)

		val sc= new SparkContext("local[*]","TotalSpentByCustomer")

		val lines=sc.textFile("../customer-orders.csv")

		val mappedInput=input.map(extractCustomerPricePairs)

		val totalByCustomer=mappedInput.reduceByKey((x,y)=>x+y)

		val flipped= totalByCustomer.map(x=>(x._2,x._1))

		val totalByCustomerSorted=flipped.sortByKey()

		val results=totalByCustomerSorted.collect()

		results.foreach(println)

	}
}