package chapter4.exercises.ex4

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter3.append
import chapter4.None
import chapter4.Option
import chapter4.Some
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

//tag::init[]
fun <A> sequence(xs: List<Option<A>>): Option<List<A>> {
    fun go(acc: Option<List<A>>, xs: List<Option<A>>): Option<List<A>> =
        when (xs) {
            is Nil -> acc
            is Cons -> xs.head.flatMap { element ->
                acc.flatMap { currentList ->
                    go(
                        Some(append(currentList, Cons(element, Nil))),
                        xs.tail
                    )
                }
            }
        }

    return go(Some(List.empty()), xs)
}
//end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise4 : WordSpec({

    "sequence" should {
        "turn a list of some options into an option of list" {
            val lo =
                List.of(Some(10), Some(20), Some(30))
            sequence(lo) shouldBe Some(List.of(10, 20, 30))
        }
        "turn a list of options containing none into a none" {
            val lo =
                List.of(Some(10), None, Some(30))
            sequence(lo) shouldBe None
        }
    }
})
