def transform(s: String): Option[String] = {
  try {
    Some(s.toUpperCase)
  } catch {
    case e: Exception => None
  }
}
println(transform("name"))
