package com.redplanet


final case class Point(x: Int, y: Int) {

  def +(that: Point): Point =
    Point(x + that.x, y + that.y)

  def *(that: Point): Point =
    Point(x * that.x, y * that.y)

  def *(scalar: Int): Point =
    Point(x * scalar, y * scalar)
  
  def distance(that: Point): Double = {
    val xx = that.x - x
    val yy = that.y - y
    math.sqrt(xx * xx + yy * yy)
  }

}

object Point {
  
  type State = (Point, Direction)

  val Origin = Point(0, 0)
  
  def move(amount: Int)(state: State): State = {
   val (p, d) = state
   (p + (d.vector * amount), d)    
  }
  
  def rotateCW(state: State): State = {
    val heading = state._2 match {
      case Forward => CW
      case CW => Backward
      case Backward => CCW
      case CCW => Forward
    }
    (state._1, heading)
  }
  
  // There's probably a cleverer way to do this
  // e.g use negation and flipping judiciously
  def rotateCCW(state: State): State = {
    val heading = state._2 match {
      case Forward => CCW
      case CCW => Backward
      case Backward => CW
      case CW => Forward
    }
    (state._1, heading)
  }

  // TODO: Clamp the movement to the grid, nicer syntax for combinators etc
}

sealed trait Direction {
  def vector: Point
} 

case object Forward extends Direction { val vector = Point(1, 0) }
case object CCW extends Direction { val vector = Point(0, 1) }
case object CW extends Direction  { val vector = Point(0, -1) }
case object Backward extends Direction { val vector = Point(-1, 0) }