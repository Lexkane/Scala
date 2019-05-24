import scala.annotation.tailrec

object Main {

  /*x=4*/
  implicit class Pipe[A](val a: A) extends AnyVal {
    def |>[B](f: A => B): B = f(a)
  }

  /*Task1*/
  /* ADD function */
  /* 4 and 3 result 7*/

  def g_add(x: Int): Int = {
    x
  }

  def h_add(z: Int): Int = {
    z + 1
  }


  def f_add(x: Int, y: Int): Int = y match {
    case 0 => g_add(x)
    //case other =>h_add(f_add(x = x,y = y - 1))
    case other => f_add(x, y - 1) |> h_add
  }

  println(f_add(3, 2))

  /* Substraction Function */


  def g_subtract(x: Int): Int = {
    x
  }

  def h_subtract(z: Int): Int = {
    z - 1
  }

  def f_subsr(x: Int, y: Int): Int = y match {
    case 0 => g_subtract(x)
    case other => f_subsr(x, y - 1) |> h_subtract
  }

  /*Task2*/
  /* Substract function */
  /* 4 and 3 result 1*/

  def decrement(z: Int): Int = {
    z - 1
  }

  def recursiveSubstract(z: Int): Int = z match {
    case 0 => 0
    case other => decrement(z)

  }


  def substract(x: Int, y: Int): Int = y match {
    case 0 => x
    case other => recursiveSubstract(substract(x, y - 1))
  }

  println(substract(4, 3))


  /*Task3
  Minimum 3
   */


  def minimum(x: Int, y: Int): Int = {
    substract(x, substract(x, y))
  }

  println(minimum(4, 3))


  /*Task4
  Maximum 4
  */

  def maximum (x:Int,y:Int):Int={
    f_add(y,substract(x,y))
  }
  println(maximum(4, 3))

  /*Task5
  Module 1
  */


  def h1_add(z: Int): Int = {
    z + 1
  }



  def add(x: Int, y: Int): Int = y match {
    case 0 => x
    //case other =>h_add(f_add(x = x,y = y - 1))
    case other => h1_add(add(x, y - 1))
  }
  def Sub(z: Int): Int = z match {
    case 0 => 0
    case other => z-1

  }
  def substr(x: Int, y: Int): Int = y match {
    case 0 => x
    case other => Sub(substr(x, y - 1))
  }

  def modulo(x:Int,y:Int):Int={
    add(add(substr(x,y),substr(y,x)),add(substr(x,y),substr(y,x)))/2
  }

  println(modulo(4,3))

  /*Task6
  Remainder 3
  */


  def signum(x:Int):Int=x match{
    case 0=>1
    case other=>1
  }

  def increment(x:Int):Int={
    x+1
  }

  def h_module(x:Int,z:Int):Int={
    (z+1)*signum(modulo(x,(z+1)))
  }

  def f_module(x:Int,y:Int):Int= y match{
    case 0 => 0
    case other=> h_module(x,f_module(x,y-1))
  }

  println(f_module(4,3))

  /*Task7
  * Fraction 1 *
  */

   def f_notsg(x:Int):Int= x match{
     case 0=>1
     case other=>0
   }


  def h_frac(x:Int,y:Int,z:Int):Int={
    z+f_notsg(modulo(x,f_module(x,y)+1))
  }



  def fraction(x:Int,y:Int):Int=y match{
    case 0=>0
    case other=>h_frac(x,y,fraction(x,y-1))
  }


  println(fraction(4,3))


  /*Task8
    * Multiplication 12 *
    */

  def Multiply(x:Int,y:Int) :Int=y match{
    case 0=>0
    case other =>add(x,Multiply(x,y-1))
  }
  println(Multiply(3,4))

      /*Task9
      * Factorial 24*
      */

  def Factorial(x:Int):Int=x match{
    case 0=>1
    case other=>Multiply(x,Factorial(x-1))
  }

  println(Factorial(4))


  /*Task10
  Exponentiation 64
   */


  def Exp(x:Int,y:Int):Int= y match{
    case 0=>1
    case other=>Multiply(x,Exp(x,y-1))
  }

  println(Exp(4,3))


}

