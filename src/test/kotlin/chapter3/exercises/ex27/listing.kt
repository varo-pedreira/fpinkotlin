package chapter3.exercises.ex27

import chapter3.Branch
import chapter3.Leaf
import chapter3.Tree
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun <A, B> map(tree: Tree<A>, f: (A) -> B): Tree<B> =
    when (tree) {
        is Leaf -> Leaf(f(tree.value))
        is Branch -> Branch(
            right = map(tree.right, f),
            left = map(tree.left, f)
        )
    }

// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise27 : WordSpec({
    "tree map" should {
        "transform all leaves of a map" {
            val actual = Branch(
                Branch(Leaf(1), Leaf(2)),
                Branch(Leaf(3), Leaf(4))
            )
            val expected = Branch(
                Branch(Leaf(10), Leaf(20)),
                Branch(Leaf(30), Leaf(40))
            )
            map(actual) { it * 10 } shouldBe expected
        }
    }
})
