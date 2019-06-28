object BellManFord extends  App {

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

  def init(num: Int, start: Int): Vector[Vertex] = {
    Vector.tabulate(num)(i => {
      Vertex(i, if (i == start) 0 else inf, None)
    })
  }

  def relax(u: Vertex, v: Vertex, weight: Double): Unit = {
    if (v.dist > u.dist + weight) {
      v.dist = u.dist + weight
      v.parent = Some(u)
    }
  }

  def bellmanFord(g: Vector[Vector[Edge]], s: Int): (Boolean, Vector[Vertex]) = {
    val verts = init(g.size, s)
    val edges = g.flatten
    println(verts.map(v => v.index + " " + v.dist).mkString(", "))
    for (i <-1 to verts.size-1){
      for (e<-edges) relax (verts(e.sourse),verts(e.dest),e.weight)
      println(verts.map(v=>v.index+ " " +v.dist).mkString(", "))
  }
  (edges.forall(e => verts(e.dest).dist <= verts(e.sourse).dist + e.weight), verts)
}

  override def main(args: Array[String]): Unit = super.main(args)
    bellmanFord(connectList, 7)
}
