package com.redplanet


import org.scalatest.{ FlatSpec, Matchers }


class PointSpec extends FlatSpec with Matchers {

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

}
