import kotlin.system.exitProcess

/*
第一步，读取输入命令；
第二步，判断命令是不是 exit，如果用户输入的是“exit”则直接退出程序；
第三步，解析算式，分解出“数字”与“操作符”：“1”“+”“2”；
第四步，根据操作符类型，算出结果：3；
第五步，输出结果：1 + 2 = 3；
第六步，进入下一个 while 循环。
*/

fun main() {

    val splash =
        """
    -----------------------------
    这是一个加减乘除计算器，依次输入：
    数字 运算符 数字 回车
    则可得到结果
    输入 exit 命令则退出
    -----------------------------
    """

    val exit = "exit"

    println(splash)
    while (true) {

        var input: String? = readlnOrNull() ?: continue
        if (exit == input) exitProcess(0)
        val inputSplit = input?.split(" ")
        val result: Int? = calculate(inputSplit)
        println("${result ?: "输入错误"}")
    }
}

fun calculate(inputSplit: List<String>?): Int? {
    if (inputSplit?.size != 3) return null;
    val left = inputSplit.get(0).toInt()
    val operation = inputSplit.get(1)
    val right = inputSplit.get(2).toInt()

    when (operation) {
        "+" -> return left + right;
        "-" -> return left - right;
        "*" -> return left * right;
        "/" -> return left / right;
        else -> return null
    }
}
