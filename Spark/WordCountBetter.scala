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
		val wordCounts=lowercaseWords.countByValue()
		//Print the results
		wordCounts.foreach(println)
	}






}