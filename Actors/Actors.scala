package actors

import akka.actor._
import akka.actor.ActorDSL._

object Live extends App{
	val primeFinder= actor( new Act{
		var workerPoll= List[ActorRef]()

		whenStarting{
			(1 to 5).foreach{ _=>
				val workerActor=actor(context)( new Act{
					become{
						case value:Int=> if (isPrime(value)){
							sender()! {value-> true}
						}
					}
				})
				workerPoll::=workerPoll
			}
		}

		become {
			case range:Range=>
				val workers=circular(workerPool).iterator
				range.foreach{number=>
					workers.next ! number
				}
			case (value:int,true)=>
				println(value)
		}



	})

	primeFinder ! (1 to 100)

}