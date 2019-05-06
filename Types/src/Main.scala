

object  Main {
  
  //check if Even
  def checkEven(num:Int):Boolean={
    return num%2==0
  }
  //check if Even in 1 line
  def chkEvn(num:Int)=num%2==0
  
  
  //check if number is  Prime
  def isPrime(n:Int) =n !=1 && (2 until n).forall(n%_!=0)
  
  
  //checkList if even
  def checkList(numbers:List[Int]):Boolean={
    for(n<-numbers){
      if (n%2==0){
        return true
      }
    }
    return false
  }
 
  //check if string palindrome 
 def plC (st:String):Boolean={
    return (st==st.reverse)
  }

  //lucky 7 counted with double
  def lucky(nums:List[Int]):Int={
    var outputs=0
    for (n<-nums){
      if (n==7){
        outputs=outputs+14
      } else{
        outputs=outputs+n
      }
    }
    return outputs
  }
  
  //check if list can be balanced
  def balanceCheck(myList:List[Int]):Boolean={
    var firsthalf=0
    var secondhalf=0
    
    secondhalf= myList.sum
      
      for (i<-Range(0, myList.length)){
        
        firsthalf=firsthalf+ myList(i)
        secondhalf=secondhalf- myList(i)
        
        if (firsthalf==secondhalf){
          return true
        }
        
      }
    return false
  }
  
 
  
  
  val ballist=List(1,2,3,4,10)
  val ballist2=List(2,3,3,2)
  val unballist=List(10,20,70) 
  
  val evensample=List(1,2,3,4,5)
  val oddsample =List(1,3,5,7)
  val numbers=List(1,2,3,7)
  
  
  def main(args: Array[String]): Unit ={
    
    
  println(plC("abccba"))
  println(plC("hello"))
  println(balanceCheck(ballist))
  println(balanceCheck(unballist))
    
  for {
	i<- 1 to 1000
	if isPrime(i)

}
  print(i+" ")

}
}

