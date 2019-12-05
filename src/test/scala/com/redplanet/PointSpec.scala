package com.redplanet


import org.scalatest.{ FlatSpec, Matchers }


class PointSpec extends FlatSpec with Matchers {
  
  import Point._
  
  val start: State = (Origin, Forward)

  "Point" should "support addition" in {
    val p = Point(1, 1) + Point(3, 4)
    p should be(Point(4, 5))
  }
  
  it should "support multiplication e.g to achieve rotation" in {
    val current = Point(1, 1)
    val directionCCW = Point(0, 1)
    val headNorth = Point(0, 5)
    val newPosition = current + (headNorth * directionCCW)
    newPosition should be(Point(1, 6))
  }
  
  // Thinking about it, if we want to move in a single direction, we need to 
  // restrict the ways in which points are added/mutliplied.
  // A scalar value should be the unit of movement
  "Moving forward" should "result in a progression along the positive x-axis" in {
    val next = move(10)(start)
    next._1 should be(Point(10, 0))
  }
  
  it should "allow chaining multiple operations" in {
    val moves = List.fill(10)(10)
    val end = moves.foldLeft(start){
      case (state, scalar) => move(scalar)(state)
    }
    end._1 should be(Point(100,0))
  }
  
  "Rotating" should "allow moving in the -y-axis via clockwise rotation" in {
    val initial: State = (Point(2, 2), Forward)
    val next = move(2)(rotateCW(initial))
    next._1 should be(Point(2, 0))
  }
  
  it should "compound rotations to 'move around'" in {
    val initial: State = (Point(0, 2), Forward)
    val ops = List(move(2)_, rotateCW _, move(2)_, rotateCW _, move(2)_)
    val result = ops.foldLeft(initial){
      case (s, f) => f(s)
    }
    result._1 should be(Origin)
  }
  
  it should "work similarly for counter-clockwise rotations" in {
    val initial = start
    val ops = List(move(2)_, rotateCCW _, move(2)_, rotateCCW _, move(2)_)
    val result = ops.foldLeft(initial){
      case (s, f) => f(s)
    }
    result._1 should be(Point(0, 2))
  }
  
  // Note that this doesn't give us any direction, just an absolute distance
  "Distance" should "involve pythagoras!" in {
    val p0 = Point(3, 4)
    val p1 = Point(6, 8)
    p0.distance(p1) should be(5.0)
  }
  
  it should "give the same answer if the points are reversed!" in {
    Point(6, 8).distance(Point(3, 4)) should be(5.0)
  }

}
