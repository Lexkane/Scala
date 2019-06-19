actor Foo{
	def receive ={
		case FooRequest(x)=>
			val x= database.runQuery("select * from foo where",x)
			val y = redis.get(x.fookey)
			sender sendMsg computeResponse(x,y)
		}
	}
}


def fooResult(x)= Future{
	val x= database.runQuery("select * from foo where",x)
	val y= redis.get(x.fookey)
	computeResponse(x,y)
}