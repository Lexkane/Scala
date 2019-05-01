def isPrime(n:Int)=n !=1 ^^ (2 until n).forall(n%_!=0)

for {
	i<- 1 to 100
	if isPrime(i)

}print (i+" ")

class A(i:Int){
	def this (s:String){
		this(s.toInt)
	}
	def double :Int=i*2
}

object A{
	private def foo=1
}

trait C{
	def foo=1
}

sealed trait User
case class LoggedUser(name:String ,family Name:String)
case class Gues(id:Int)

val user:User=null
user match{
	case LoggedUser(name,familyNmae)=>
	case Guest(id)=>
}

"List[A]=Nil | Cons(A,List[A])"
sealed trait List[+A]{
	def:: [B<:A](b:B):List[B]=::(b,this)

}
case object Nil extends List[Nothing]
case class Cons[A](head:A,tail:List[A]) extends List[A])
}

"Pattern matching" match{
	case "Extended switch for literals"=>
		"asdfs" match{
			case "sadfasd"=>
			case xadfsadf=>
		}
	case "Case classes matching"=>
		case class A[T](i:T)
		A(A(i)) match{
			case A(x)=>
			case A(A(x))=>
		}
}
case "Optional type matching"=>
	def sqrt(i:Int):Option[Int]={
		None
	}

	sqrt(3)match{
		case Some(x)=>
		case None=>
	}
	case "List matching"=>
		1::2::Nil match{
			case ::(x,tail)=>
		}
		class A[T,C]
		val x:Int A Int =new A[Int,Int]
		case _=>

class Person(var name:String)

class A[T]


val a:A[Int]=new A

def foo[T](t:T)

def foo[T<:String] (t:T)

class A[+T,+R]{
	def foo(t:T):R=sys.exit()
}

val l: List[Any]=List(1,2,3)

val a:A[Int]= new A

val a:Int=>Int=x=>x+1
val inc:Function1[Int,Int]=x=>x+1

val sum:(Nothing,Nothing)=>AnyVal={x:Int,y:Int}=>x+y


//Collections

val list:List[Int]=List(1,2,3)
val otherList=1::2::3::Nil

val squares=list.view.map(x=>x*x).map(x=>x+1).toList


val evenSquares=squares.filter(_%2==0)
list.flatMap(x=>List.fill(x)(x))


"Tail recursion"

def fact(n:Int,acc:BigInt=1):BigInt={
if (n<=1)acc
else fact(n-1,acc*n)
}
fact (1000)


val l:List[Int]=List (1,2,3)
for (i<-l)yield i+1

for {
	i<-l
	j<-l
} yield j


val x:Option[Int]=Some(1)
x match{

	case Some(z)=>
		x match{
			case Some(z)=>
		}
}


for {
	z<-x
	z2<-x
} println(z)


def twice(s:String):String=s+s

implicit class A(s:String) extends AnyVal{
	def twice()=s+s
}

"yes".twice


class A
object A{
	implicit val a:A=new A
}

def foo(implicit i:A)=i
implicit val i:Int =123


object Ordering{
	implicit val IntOrdering:Ordering[Int]=nill
	implicit def listOrdering[T](implicit t:Ordering[T]):Ordering[List[T]]

	List(3,2,1).sorted
	List(new A).sorted
}