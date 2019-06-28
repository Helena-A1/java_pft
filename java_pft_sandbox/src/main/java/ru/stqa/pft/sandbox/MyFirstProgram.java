package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    hello("world");
    hello("User");
    hello("Alena");

    Square s = new Square(5);
    System.out.println(" Площадь квадрата сo стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    // Задание №2: Потренироваться использовать функции, классы, объекты и методы
    //  Объявление точек
    Point p1 = new Point(0, 0);
    Point p2 = new Point(3, 4);

    System.out.println(" Тoчка p1 имеет координаты " + p1.x + " и " + p1.y);
    System.out.println(" Тoчка p2 имеет координаты " + p2.x + " и " + p2.y);

    // использование функции для нахождения расстояния
    System.out.println(" Расстояние между точками равно " + p1.distance(p2,p2));

    // использование метода для нахождения расстояния
    System.out.println(" Расстояние между точками равно " + p1.distance(p2));

  }

  public static void hello(String somebody) {

    System.out.println(" Hello " + somebody + "!");
  }

  }
