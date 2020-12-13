package com.maxxxwk.familytree


data class Person(
    val name: String,
    val age: Int,
    val mother: Person?,
    val father: Person?,
    val siblings: MutableList<Person>,
    val children: MutableList<Person>
){
    var numberOfRelatives = 0
        get() {
            var counter = 0
            mother?.let {
                counter += 1
                counter += it.numberOfRelatives - siblings.size - 1
            }
            father?.let {
                counter += 1
                counter += it.numberOfRelatives - siblings.size - 1
            }
            counter += siblings.size
            counter += children.size
            return counter
        }
        private set
}
fun getMe(): Person{
    val grandMother1 = Person("Nadia", 75, null, null, mutableListOf(), mutableListOf())
    val grandFather1 = Person("Dmytro", 78, null, null, mutableListOf(), mutableListOf())
    val grandMother2 = Person("Maria", 72, null, null, mutableListOf(), mutableListOf())
    val grandFather2 = Person("Ivan", 72, null, null, mutableListOf(), mutableListOf())
    val aunt = Person("Anna", 46, grandMother2, grandFather2, mutableListOf(), mutableListOf())
    val uncle = Person("Roman", 40, grandMother2, grandFather2, mutableListOf(), mutableListOf())
    val mother = Person("Tetiana", 50, grandMother2, grandFather2, mutableListOf(), mutableListOf())
    aunt.siblings?.apply {
        add(uncle)
        add(mother)
    }
    uncle.siblings?.apply {
        add(aunt)
        add(mother)
    }
    mother.siblings?.apply {
        add(aunt)
        add(uncle)
    }
    grandMother2.children?.apply {
        add(aunt)
        add(uncle)
        add(mother)
    }
    grandFather2.children?.apply {
        add(aunt)
        add(uncle)
        add(mother)
    }
    val father = Person("Anatoliy", 55, grandMother1, grandFather1, mutableListOf(), mutableListOf())
    grandMother1.children?.add(father)
    grandFather1.children?.add(father)
    val brother = Person("Roman", 24, mother, father, mutableListOf(), mutableListOf())
    val me = Person("Maksym", 18, mother, father, mutableListOf(brother), mutableListOf())
    mother.children?.apply {
        add(me)
        add(brother)
    }
    father.children?.apply {
        add(me)
        add(brother)
    }
    brother.siblings?.add(me)
    return me
}

fun main() {
    val me = getMe()
    println(me.numberOfRelatives)
}