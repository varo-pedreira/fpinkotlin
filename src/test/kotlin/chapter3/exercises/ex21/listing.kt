package chapter3.exercises.ex21

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter3.append
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun add(xa: List<Int>, xb: List<Int>): List<Int> {
    tailrec fun go(
        first: List<Int>,
        second: List<Int>,
        acc: List<Int>
    ): List<Int> =
        when (first) {
            is Nil -> acc
            is Cons -> when (second) {
                is Nil -> acc
                is Cons -> go(
                    acc = append(acc, List.of(first.head + second.head)),
                    first = first.tail,
                    second = second.tail
                )
            }
        }
    return go(first = xa, second = xb, acc = List.empty())
}
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise21 : WordSpec({
    "list add" should {
        "add elements of two corresponding lists" {
            add(List.of(1, 2, 3), List.of(4, 5, 6)) shouldBe
                List.of(5, 7, 9)
        }
    }
})
