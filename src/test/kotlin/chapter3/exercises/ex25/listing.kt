package chapter3.exercises.ex25

import chapter3.Branch
import chapter3.Leaf
import chapter3.Tree
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun maximum(tree: Tree<Int>): Int {
    fun find(tree: Tree<Int>, acc: Int): Int = when (tree) {
        is Branch -> maxOf(find(tree.left, acc), find(tree.right, acc))
        is Leaf -> maxOf(acc, tree.value)
    }
    return find(tree, 0)
}
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise25 : WordSpec({
    "tree maximum" should {
        "determine the maximum value held in a tree" {
            val tree = Branch(
                Branch(Leaf(1), Leaf(9)),
                Branch(Leaf(3), Leaf(4))
            )
            maximum(tree) shouldBe 9
        }
    }
})
