sealed trait Option [A] {
	def map[B](f:A=>B):Option[B]
	def flatMap[B](f:A=>Option[B]):Option[B]
}

case class Some[A](a:A) extends Option[A]{
	def map[B](f:A=>B):Option[B]= new Some(f(a))
	def flatMap[B](f:A=>Option[B]):Option[B]=f(a)
}

case class None[A] extends Option[A]{
	def map[B](f:A=>B):Option[B]= new None
	def flatMap[B](f:A=>Option[B]):Option[B]= new None

}


	class Foo{def bar: Option [Bar]}
	class Bar{def baz:Option[Baz]}
	class Baz{def compute:Int}


def computeBaz(baz:Baz):Int=
	baz.compute

def computeBar(bar:Bar):Option[Int]=
	bar.baz.map{computeBaz}

def computeFoo(foo:Foo):Option[Int]=
	foo.bar.flatMap{computeBar}

def compute(maybeFoo:Option[Foo]):Option[Int]=
	maybeFoo.flatMap{computeFoo}

def compute(maybeFoo:Option[Foo]):Option[Int]=
	maybeFoo.flatMap{
		foo=>
		foo.bar.flatMap{
			bar=>
			bar.baz.map{
				baz=>
				baz.compute
			}
		}
	}

sealed trait Validation[E,A]{
	def map[B](f:A=>B):Validation[E,B]
	def flatMap[B](f:A=>Validation[E,B]):Validation[E,B]
	def liftFail[F](f:E=>F):Validation[F,A]
}

case class Success[E,A](a:A) extends Validation[E,A]{
	def map[B](f:A=>B):Validationn[E,B]= new Success(f(a))
	def flatMap[B](f:A=>Validation[E,B]):Validation[E,B]=f(a)
	def liftFail[F](f:E=>F):Validation[F,A]= new Success(a)
}


case class Failure[E,A](e:E) extends Validation[E,A]{
	def map[B](f:A=>B):Validation[E,B]= new Failure(e)
	def flatMap[B](f:A=>Validation[E,B]):Validation[E,B]= new Failure(e)
	def liftFail[F](f:E=>F):Validation[F,A]= new Failure(f(e))

}


	class Foo{def bar: Validation[BarException,Bar]}
	class Bar{def baz:Validation[BazException,Baz]}
	class Baz{def compute:Validation[ComputeException,Int]}

	def compute(foo:Foo):Validation[ComputeException,Int]=
		foo.bar.liftFail{new ComputeExcception(_)}.flatMap{ bar=>
			bar.baz.liftFail{new ComputeExcetpion(_).flatMap{baz=>
				baz.compute
			}}
		}
 

 //Loop 

 class Foo{ def bars:List[Bar]}
 class Bar{ def bazs:List[Baz]}
 class Baz{def computeAll:List[Int]}

 def computeAll(foos:List[Foo]):List[Int]=
 	for {
 		foo<-foos
 		bar<-foo.bars
 		baz<-bar.bazs
 		results<-baz.computeAll
 	} yield results

def computeFull (foos:List[Foo]):List[Int]=
foos.flatMap {foo=>
foo.bars.flatMap{
	bar=>
	bar.bazs.flatMap{
		baz=>
		baz.computeAll.map{results=>results}
		}
	}
} 	

def computeMonad(maybeFoo:Option[Foo]):Option[Int]=
for{
	foo<-maybeFoo
	bar <-foo.bar
	baz<-bar.baz
} yield baz.compute


def compute(foo:Foo) Validation [ComputeException,int]=
for{
	bar<-foo.bar.liftFail{new ComputeException(_)}
	baz<-bar.baz.liftFail{new ComputeException(_)}
	result<-baz.compute
} yield result

/*Monads
	Option
	Validation
	List
	State
	Undo
	Promise
	Transaction
*/