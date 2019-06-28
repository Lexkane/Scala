object FloydWarshall extends App {

  case class Edge(sourse: Int, dest: Int, weight: Double)

  case class Vertex(index: Int, var dist: Double, var parent: Option[Vertex])

  val inf = Double.PositiveInfinity
  val connectList = Vector(Vector(Edge(0, 1, 3), Edge(0, 3, 1), Edge(0, 4, 2)),
    Vector(Edge(1, 5, 4)),
    Vector(Edge(2, 0, 0)),
    Vector(Edge(3, 7, 1)),
    Vector(Edge(4, 2, 7), Edge(4, 8, 2)),
    Vector(Edge(5, 2, 1)),
    Vector(Edge(6, 0, 3)),
    Vector(Edge(7, 6, 5), Edge(7, 8, 11)),
    Vector(Edge(8, 5, 3)))

  val connectMatrix = Vector.tabulate(connectList.size, connectList.size)((i, j) => {
    connectList(i).find(_.dest == j).getOrElse(Edge(i, j, inf)).weight
  })
  type Graph = Vector[Vector[Double]]

  def printMatrix(m: Graph): Unit = {
    for (row <- m) {
      println(row.mkString(", "))
    }
    println()
  }

  printMatrix(connectMatrix)

  def extendShortestPath(l: Graph, w: Graph): Graph = {
    Vector.tabulate(l.size, l.size)((i, j) => {
      (0 until l.size).foldLeft(inf)((lprime, k) => lprime min l(i)(k) + w(k)(j))
    })
  }

  def slowVersion(w: Graph): Graph = {
    (1 to w.size - 1).foldLeft(w)((l, i) => extendShortestPath(l, w))
  }

  //printMatrix(slowVersion(connectMatrix))

  def fastVersion(w: Graph): Graph = {
    (1 to math.ceil(math.log(w.size) / math.log(2)).toInt + 1).foldLeft(w)((l, i) => extendShortestPath(l, l))
  }

  //printMatrix(fastVersion(connectMatrix))

  def floydWarshall(w: Graph): Graph = {
    (0 until w.size).foldLeft(w)((d, k) => {
      val dprime = Vector.tabulate(w.size, w.size)((i, j) => {
        d(i)(j) min d(i)(k) + d(k)(j)
      })
      printMatrix(dprime)
      dprime
    })
  }


}
