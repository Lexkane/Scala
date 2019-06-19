package com.company.lexkane

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

//**Count up how many of each word appears in a book 

object WordCount{

	def main(args:Array[String]){


		Logger.getLogger("org").setLevel(Level.Error)
		
		val sc= new SparkContext("local[*]","WordCountBetter")
		//Read each line into RDD
				val input =sc.textFile("../book.txt")
		//Split using regular expression to split words
		val words=input.flatMap(x=>x.split("\\W+"))
		//Normalize everything to lowercase
		val lowercaseWords= words.map(x=>x.toLowerCase())
		//Count the occurences of each word
		val wordCounts=lowercaseWords.map(x=>(x,1)).reduceByKey((x,y)=>x+y)
		//Flip (word,count) tuples to (count,word) and then sort by key (the counts)
		val wordCountsSorted=wordCounts.map(x=>(x._2, x._1)).sortByKey()
		//Print the results flipping the (count, word) results to word:count

		for (result <- wordCountsSorted){
			val count= result._1
			val word= result._2
			println(s"$word: $count")
		}
}






}