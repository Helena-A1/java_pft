package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
 @Test
  public  void testDistance () {
   Point p1 = new Point(0, 0);
   Point p2 = new Point(3, 4);

//check that the distance value equals expected result
   Assert.assertEquals(Point.distance(p1, p2), 5.0);

   //check that the distance from p1 to p2 equals the backwards distance
   Assert.assertEquals(Point.distance(p1, p2), Point.distance(p2, p1));

 }


}


