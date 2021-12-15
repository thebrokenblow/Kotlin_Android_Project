package print

import model.OnlineStoreDataBase

open class Print (
    private val listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase>){
    open fun print() {
        println("Вывод всей информации")
        for (i in listOfOnlineStoreDataBase.indices) {
            println(i.toString() + ") ФИО: " + listOfOnlineStoreDataBase[i].surname + " " +
                    listOfOnlineStoreDataBase[i].name + " " +
                    listOfOnlineStoreDataBase[i].patronymic + " " +
                    "Цена: " + listOfOnlineStoreDataBase[i].cost + " " +
                    "Скидка: " + listOfOnlineStoreDataBase[i].discount + " " +
                    "Адрес: " + listOfOnlineStoreDataBase[i].address)
        }
    }
}