

object  Main {
  def isPrime(n:Int) =n !=1 && (2 until n).forall(n%_!=0)
   def main(args: Array[String]): Unit ={
  
  for {
	i<- 1 to 100000
	if isPrime(i)

}
  print(i+" ")

}
}