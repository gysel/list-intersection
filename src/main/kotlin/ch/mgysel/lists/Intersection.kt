package ch.mgysel.lists

enum class Intersection {
    KOTLIN_STDLIB {
        override fun <T> apply(first: Collection<T>, second: Collection<T>): Set<T> {
            return first intersect second
        }
    },
    ITERATION {
        override fun <T> apply(first: Collection<T>, second: Collection<T>): Set<T> {
            val set = first.toSet()
            return second.filter { set.contains(it) }.toSet()
        }
    },
    SEQUENCE {
        override fun <T> apply(first: Collection<T>, second: Collection<T>): Set<T> {
            val set = first.toSet()
            return second.asSequence().filter { set.contains(it) }.toSet()
        }
    };

    abstract fun <T> apply(first: Collection<T>, second: Collection<T>): Set<T>
}
