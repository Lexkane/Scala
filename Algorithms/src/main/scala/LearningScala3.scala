class LearningScala3 {

  def squareIt(x: Int): Int = {
    x * x
  }


  def cubeIt(x: Int): Int = {
    x * x * x
  }

  println(squareIt(2))
  println(cubeIt(2))

  def transformInt(x: Int, f: Int => Int): Int = {
    f(x)
  }


  var result = transformInt(2, cubeIt)
  println(result)

  transformInt(3,x=>x*x*x)
  transformInt(10,x=>x/2)
  transformInt(2,x=>{val y=x*2;y*y})


  def transfromString(x:String) :String={
    x.toUpperCase
  }

  def strtrans(x:String,f:String=>String):String ={
    f(x)
  }

  strtrans("any",x=>x.toUpperCase)

}
