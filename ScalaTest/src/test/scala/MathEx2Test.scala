import org.scalatest.FlatSpec
import org.scalatest.Matchers._
import org.scalamock.scalatest.MockFactory


class MathEx2Test extends FlatSpec with MockFactory {

  behavior of "MathEx2Test"
  val obj = new MathEx()
  "MaxEx2Test" should "add" in {
    obj.add(2, 3) should be(5.0 +- 0.01)
  }

  it should "sub" in {
    val obj = new MathEx()
    val result = obj.sub(2, 3)
    assert(result == -1)
  }
  "Work" should "doWork" in {
    val m=mock[IMath]
    (m.add _).expects(3,7).returning(10)
    (m.sub _).expects(10,7).returning(3)
    Work.doWork(3, 7, m)

  }


}
