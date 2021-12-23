package chapter3.exercises.ex22

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter3.append
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

// tag::init[]
fun <A> zipWith(xa: List<A>, xb: List<A>, f: (A, A) -> A): List<A> {
    tailrec fun go(
        first: List<A>,
        second: List<A>,
        acc: List<A>,
        f: (A, A) -> A
    ): List<A> =
        when (first) {
            is Nil -> acc
            is Cons -> when (second) {
                is Nil -> acc
                is Cons -> go(
                    acc = append(acc, List.of(f(first.head , second.head))),
                    first = first.tail,
                    second = second.tail,
                    f = f
                )
            }
        }
    return go(first = xa, second = xb, acc = List.empty(), f = f)
}

// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise22 : WordSpec({
    "list zipWith" should {
        "apply a function to elements of two corresponding lists" {
            zipWith(
                List.of(1, 2, 3),
                List.of(4, 5, 6)
            ) { x, y -> x + y } shouldBe List.of(5, 7, 9)
        }
    }
})
