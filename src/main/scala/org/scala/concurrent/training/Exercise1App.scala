package org.scala.concurrent.training

import scala.util.Try

object Exercise1App extends App {
  /*
  1. Implement a compose  method with the following signature:
            def compose[A, B, C]
            (g: B => C, f: A => B): A => C = ???
  This method must return a function h, which is the composition of the functions f and g
   */
  def compose[A, B, C](g: B ⇒ C, f: A ⇒ B): A ⇒ C = (x: A) ⇒ g(f(x))

  val stringToInt = (x: String) ⇒ x.toInt
  val biggerThan10 = (x: Int) ⇒ x > 10
  val checkIfStringBiggerThan10 = compose(biggerThan10, stringToInt)
  println(checkIfStringBiggerThan10("100"))
  println(checkIfStringBiggerThan10("5"))

  /*
  2. Implement a fuse method with the following signature:
            def fuse[A, B]
            (a: Option[A], b: Option[B]): Option[(A, B)] = ???
    The resulting Option object should contain a tuple of values from the Option objects a and b,
    given that both a and b are non-empty. Use for-comprehensions
   * */

  def fuse[A, B](a: Option[A], b: Option[B]): Option[(A, B)] =
    for (aContent ← a; bContent ← b) yield (aContent, bContent)

  println(fuse(Some(123), Some("Online")))
  println(fuse(None, Some("Online")))

  /*
  3. Implement a check method, which takes a set of values of type T and a function of type T => Boolean:
            def check[T](xs: Seq[T])(pred: T => Boolean): Boolean = ???
  The method must return true if and only if the pred function returns true for all the values in xs without throwing an exception.
  Use the check method as follows:

            check(0 until 10)(40 / _ > 0)
      TIP
    The check method has a curried definition: instead of just one parameter list, it has two of them.
    Curried definitions allow a nicer syntax when calling the function, but are otherwise semantically equivalent to single-parameter list definitions.
   * */

  def check[T](xs: Seq[T])(pred: T ⇒ Boolean): Boolean =
    Try(xs.forall(pred)).getOrElse(false)

  println(check(0 until 10)(40 / _ > 0))

  /*
  4. Modify the Pair class from this chapter so that it can be used in a pattern match.
    class Pair[P, Q](val first: P, val second: Q)
   * */

  def checkPair[P, Q](p: Pair[P, Q]): String = p match {
    case Pair(a: Int, b: String) ⇒ "Int and String"
    case _ ⇒ "I don't know"
  }

  case class Pair[P, Q](val first: P, val second: Q)
  println(checkPair(Pair(123, "World")))
  println(checkPair(Pair("hello", "World")))

  /*
  5. Implement a permutations function, which, given a string,
   returns a sequence of strings that are lexicographic permutations of the input string:
            def permutations(x: String): Seq[String]
   * */

  def permutation(x: String): Seq[String] =
    x.permutations.toSeq

  println(permutation("anang"))

  /*
  6. Implement a combinations function that, given a sequence of elements, produces an iterator over all possible combinations of length n.
  A combination is a way of selecting elements from the collection so that every element is selected once, and the order of elements does not matter. For example, given a collection Seq(1, 4, 9, 16), combinations of length 2 are Seq(1, 4), Seq(1, 9), Seq(1, 16), Seq(4, 9), Seq(4, 16), and Seq(9, 16). The combinations function has the following signature:
            def combinations(n: Int, xs: Seq[Int]): Iterator[Seq[Int]]
    See the Iterator API in the standard library documentation
   * */
  /*
  7. Implement a method that takes a regular expression, and returns a partial function from a string to lists of matches within that string:
            def matcher   (regex: String): PartialFunction[String,
            List[String]]
The partial function should not be defined if there are no matches within the argument strings.
Otherwise, it should use the regular expression to output the list of matches.
   * */

  /*
  8. Consider that you and three of your colleagues working in an office divided into cubicles.
  You cannot see each other, and you are not allowed to verbally communicate, as that might disturb other workers.
  Instead, you can throw pieces of paper with short messages at each other.
  Since you are confined in a cubicle, neither of you can tell if the message has reached its destination.
  At any point, you or one of your colleagues may be called to the boss's office and kept there indefinitely.
  Design an algorithm in which you and your colleagues can decide when to meet at the local bar.
  With the exception of the one among you who was called to the boss's office, all of you have to decide on the same time.
  What if some of the paper pieces can arbitrarily miss the target cubicle?
   * */

  /*
  9. Imagine that, in the previous exercise, you and your colleagues also have a whiteboard in the hall next to the office.
  Each one of you can occasionally pass through the hall and write something on the whiteboard,
  but there is no guarantee that either of you will be in the hall at the same time.
  Solve the problem from the previous exercise, this time using the whiteboard.
 * */
}
