import org.scalatest.FunSuite

class MathExTest extends FunSuite {
    test("testAdd"){
      val obj = new MathEx()
      val result= obj.add(2,3)
      assert( result==5)
    }


    test("testSub"){
      val obj = new MathEx()
      val result= obj.sub(2,3)
      assert(result == -1)
    }
}
