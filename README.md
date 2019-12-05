### Part 1 - Basic Movement

*Arbitrary grid size*

Two options that spring to mind initially:

1 Normalised coordinates i.e use values between 0.0 and 1.0
2 Use a dynamic grid size with the values being set at runtime via configuration/program input

In either case the coordinates can be modelled using a fairly straightforward `Point` object:

```scala
type MRFloat = ???
case class Point(x: MRFloat, y: MRFloat)
```

*Movement*

* Forward  - A 180 degree rotation gives us reverse 
* Rotation - Let's naively assume rotating by 90 degree increments is good enough

```scala
// 'Forward' is represented by moving along the positive x-axis
val Forward = Point(1,0)
// Move in the negative-y direction
val CW = Point(0, -1)
// Positive-y
val CCW = Point(0, 1) 
// etc
```

When we need to expand into a more complex model, we'll look at maintaining rotation matrix state

*Edge detection*

Applying a modulus of the grid size to the coordinates would give us a reset to zero


### Part 2 - Auto Pilot


1 If a straight line is required here, we can calculate that by simple trigonometry. If we actually need to track along that line
  then we'l definitely require the more complex model.
  
2 Collision detection is commonly provided by data maps, for example in games. That definitely requires more math!


### Part 3

Seems like this could be modelled as a stream? It almost feels like it could be similar to svg data...

Maybe a stream of commands that could be interpreted variously to produce _any reasonable_ format.
