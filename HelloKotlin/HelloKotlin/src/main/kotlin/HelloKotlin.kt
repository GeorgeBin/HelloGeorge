/**
 * Hello 展示内容：
 * 变量声明
 * 基础类型
 *
 * https://kotlinlang.org/docs/home.html
 *
 */

// 顶层函数与变量
val topLevelVar: String = "I am a top-level variable"

fun topLevelFunction() {
    println("This is a top-level function")
}

fun main() {

    /******** 变量 ********/

    /*
    关键字     变量类型
     ↓          ↓           */
    var price: Int = 100;   /*
     ↑            ↑
   变量名        变量值       */

    // val 常量、var 变量
    val price2 = 32 // 省略分号，类型推导
    var nullableString: String? = null // 可空类型，需要显示声明，默认都是不可空类型
    // TODO：Unit、Any、Nothing、Any


    /********  基础类型 Basic types（皆为对象） ********/
    /* 亿：10^8（100,000,000）
       兆：10^12（1,000,000,000,000）
       京：10^16（10,000,000,000,000,000）
       垓：10^20（100,000,000,000,000,000,000）*/

    // Numbers
    // 整型（x位=bit width）
    val longVaL: Long = 64 // 64位（-9,223,372,036,854,775,808=-2的63次方) ~ 9,223,372,036,854,775,807=2的63次方-1）±9垓
    var intVar = 32 // 32位（ -2,147,483,648=-2的31次方 ~ 2,147,483,647=2的31次方-1） ±21 亿
    val shortVaL: Short = 16.toShort() // 16位 只能显式转换（-32768 ~ 32767）
    val byteVaL: Byte = 0b01// 8位（-128 ~ 127）
    // 无符号整数：UByte, UShort, UInt, ULong
    val uLong: ULong = 100u
    val uInt = 100u
    val uShort = uInt.toUShort()
    val uByte: UByte = 0b0101u
    // 浮点型
    val doubleVaL: Double = 64.00
    val floatVaL: Float = 32.0F

    // 其他
    val charVaL = 'd'// 16位
    val booleanVaL: Boolean = true// 8位

    val stringVaL = "I am a top-level variable"// 字符串
    println("字符串模板（string templates）: $stringVaL")// 字符串模板 String templates：直接在字符串中访问变量

    // 原始字符串 Multiline strings
    val multilineString = """
        当我们的字符串有复杂的格式时
        原始字符串 非常的方便
        因为它可以做到所见即所得。 
        也可以引用其他变量，例如 $charVaL $longVaL
        """

    val arrayInt = arrayOf(1, 2, 3) // 数组
    val oneToThree: IntRange = 1..3 // 区间：代表 [1, 3]
    val sixTo0Step2 = 6 downTo 0 step 2 // 逆序区间：代表 [6, 4, 2, 0]
    val items = listOf("apple", "banana", "kiwi") // 集合

    val filteredFruits = items.filter { it.startsWith("a") } // Lambda 表达式与集合过滤
    println("Fruits that start with 'a': $filteredFruits")

    val lengthMap = items.map { it to it.length } // Map 操作
    println("Map of fruits to their lengths: $lengthMap")


    // 函数
    intVar = oneLineFunction(arrayInt) // 函数调用
    helloFunction(name = "Kotlin") // 命名参数
    manyParameter(name = "George", age = 10) // 使用默认值

    /******** 基础语法 ********/

    // while：与 Java 相同

    // if：可赋值
    nullableString = if (booleanVaL) "Big" else null

    // when：可赋值。要求逻辑完全，用于替换 switch。
    nullableString = when (intVar) {
        1 -> "一"
        2 -> null
        else -> "不是一也不是二，intVar=$intVar"
    }
    println(describe(floatVaL)) // when + Any

    // for：可赋值
    for (i in arrayInt) println("contains $i")
    for (i in oneToThree) println("contains $i")
    for (i in 6 downTo 0 step 2) println("contains $i")


    // Elvis 操作符
    // var length = if (nullableString != null) nullableString.length else 0
    var length = nullableString?.length ?: 0

    multilineString.printLength() // 扩展函数


    val person = Person("John", 25)
    println("Person's name: ${person.name}, age: ${person.age}")// 复杂变量用 ${}（template expression）

    // Lambda 表达式
    val sum = { x: Int, y: Int -> x + y }
    println("Sum of 3 and 4: ${sum(3, 4)}")
}

/******** 函数 ********/

/*
关键字    函数名          参数类型   返回值类型
 ↓        ↓                ↓       ↓      */
fun helloFunction(name: String): String {
    return "Hello $name !"
}/*   ↑
           花括号内为：函数体                 */

fun oneLineFunction(array: Array<Int>) = array.size // 一行的函数可简化

fun manyParameter(
    name: String,
    age: Int,
    female: Boolean = true // 默认值
): String {
    return "My name is $name, I'm $age years old, I'm a ${if (female) "girl" else "boy"}"
}

// 扩展函数、可空类型、Elvis 操作符、字符串模板、Unit
fun String?.printLength(): Unit {
    println("This String length is ${this?.length ?: "null"}")
}

// when 表达式 + Any
fun describe(obj: Any): String = when (obj) {
    1 -> "One"
    "Hello" -> "Greeting"
    is Long -> "Long number"
    !is String -> "Not a string"
    else -> "Unknown"
}

/******** 类 ********/

// 类和构造函数
class Person(val name: String, var age: Int)

// 数据类和伴生对象
data class User(val username: String, val email: String) {
    companion object {
        fun createGuestUser(): User {
            return User("guest", "guest@example.com")
        }
    }
}

