class LearningScala2 {
if (1>3) println ("Impossible") else println("The world makes sence")


  if (1>3){
    println ("Impossible")
  } else{
    println("The world makes sence")
  }

  val number=3
  number match {
    case 1=>println("One")
    case 2=>println("Two")
    case 3=>println("Three")
    case _=>println("Something Else")
  }


  for (x <- 1 to 4){
    val squared =x*x
    println(squared)
  }

  var x=10
  while (x>=0){
    println(x)
    x-=1
  }

  var x2=0
  do{
    println(x2);x2+=1}
  while (x2<=10)



  {val x=10;x+20}
  println({val x=10; x+20})

  //Print Fibonacci Sequence

  def getFib(index: Int): Int = {
    if (index <= 0) {
      0
    } else if (index == 1) {
      1
    } else getFib(index - 1) + getFib(index - 2)
  }

  for (x<-1 to 10){
    println (getFib(x))
  }

}
