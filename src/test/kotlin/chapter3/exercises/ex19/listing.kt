package chapter3.exercises.ex19

import chapter3.List
import chapter3.append
import chapter3.foldLeft
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun <A, B> flatMap(xa: List<A>, f: (A) -> List<B>): List<B> =
    foldLeft(xa, List.empty()) { ls, x -> append(ls, f(x)) }

// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise19 : WordSpec({
    "list flatmap" should {
        "map and flatten a list" {
            val xs = List.of(1, 2, 3)
            flatMap(xs) { i -> List.of(i, i) } shouldBe
                List.of(1, 1, 2, 2, 3, 3)
        }
    }
})
