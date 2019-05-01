def flatten[T <: Product, L <: HList](t : T)
  (implicit hl : HListerAux[T, L], flatten : Flatten[L]) : flatten.Out =
    flatten(hl(t))

val t1 = (1, ((2, 3), 4))
val f1 = flatten(t1)     // Inferred type is Int :: Int :: Int :: Int :: HNil
val l1 = f1.toList       // Inferred type is List[Int]

val t2 = (23, ((true, 2.0, "foo"), "bar"), (13, false))
val f2 = flatten(t2)
val t2b = f2.tupled

case class Foo(i : Int, s : String)
case class Bar(b : Boolean, s : String, d : Double)

// Publish their `HListIso`'s
implicit def fooIso = Iso.hlist(Foo.apply _, Foo.unapply _)
implicit def barIso = Iso.hlist(Bar.apply _, Bar.unapply _)

// And now they're monoids ...

implicitly[Monoid[Foo]]
val f = Foo(13, "foo") |+| Foo(23, "bar")
assert(f == Foo(36, "foobar"))

implicitly[Monoid[Bar]]
val b = Bar(true, "foo", 1.0) |+| Bar(false, "bar", 3.0)
assert(b == Bar(true, "foobar", 4.0))


object size extends Poly1 {
  implicit def default[T] = at[T](t => 1)
  implicit def caseString = at[String](_.length)
  implicit def caseList[T] = at[List[T]](_.length)
}

scala> val l = 23 :: "foo" :: List('a', 'b') :: true :: HNil
l: Int :: String :: List[Char] :: Boolean :: HNil =
  23 :: foo :: List(a, b) :: true :: HNil

scala> (l map size).toList
res1: List[Int] = List(1, 3, 2, 1)

trait Fruit
case class Apple() extends Fruit
case class Pear() extends Fruit

type FFFF = Fruit :: Fruit :: Fruit :: Fruit :: HNil
type APAP = Apple :: Pear :: Apple :: Pear :: HNil

val a : Apple = Apple()
val p : Pear = Pear()

val l = List(a, p, a, p) // Inferred type is List[Fruit]

val t1 : (Any, Any) = (23, "foo") // Specific element types erased
val t2 : (Any, Any) = (true, 2.0) // Specific element types erased

// Type class instances selected on static type at runtime!
val c1 = stagedConsumeTuple(t1) // Uses intString instance
assert(c1 == "23foo")

val c2 = stagedConsumeTuple(t2) // Uses booleanDouble instance
assert(c2 == "+2.0")