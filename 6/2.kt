package `6`


class List<T>() {
    var ChangeList = mutableListOf<T>()
    fun a1dd(el:T):Boolean {
        if (el!=null) {
            ChangeList.add(el)
            return true
        }
        return false
    }
    fun d1elete():Boolean {
        var bench = ChangeList.first()
        if (bench!=null) {
            ChangeList.remove(bench)
            return true
        }
        return false
    }
    fun p1rint(count: Int,n:Int):Unit {
        if (count>=0&&n>=0&&n<=ChangeList.size-1) {
            for(idx in n until n+count) {
                println(ChangeList[idx])
            }
        }
    }
    fun eraseAll() {
        ChangeList.clear()
    }
}
//Вариант 2,1,5
fun main() {
    var test = List<Int>()
    test.a1dd(4)
    test.a1dd(6)
    test.a1dd(7)
    test.a1dd(10)
    test.a1dd(11)
    test.a1dd(62)
    test.a1dd(444)
    println(test.ChangeList)
    test.p1rint(3,2)
    //println(test.ChangeList)
}
