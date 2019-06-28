object ListComprehensions {


  val myList=List(1,2,3,4,5,6,7,8,9,10)

  val result=myList.filter(_>5).map(_+1).filter(_%2!=0)
  println(result)
}
