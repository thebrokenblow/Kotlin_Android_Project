import add.Add
import classInput.inputIndexOfList.DaggerInputComponentOfferToEnterActionNumber
import model.OnlineStoreDataBase
import print.Print

const val valueOfExitingProgram = 0
const val valueToAdd = 1
const val valueToOutputOfRecords = 2
const val valueToDeleteOfRecords = 3
const val valueToChangeOfRecords = 4
const val valueToFilterOfRecords = 5
const val valueToSearchOfRecords = 6

fun main() {
    val listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase> = arrayListOf()
    val mapMenu = listOf(
        Triple(valueToAdd, "Если хотите добавить запись") { Add(listOfOnlineStoreDataBase).add() },
        Triple(valueToOutputOfRecords, "Если хотите вывести все записи") { Print(listOfOnlineStoreDataBase).print() },
        Triple(valueToDeleteOfRecords, "Если хотите удалить запись") {  },
        Triple(valueToChangeOfRecords, "Если хотите изменить запись") {  },
        Triple(valueToFilterOfRecords, "Если хотите отфильтровать запись") {  },
        Triple(valueToSearchOfRecords, "Если хотите произвести поиск записи") { }
    )
    do {
        for (i in mapMenu)
            println(i.first.toString() + " " + i.second)

        mapMenu[DaggerInputComponentOfferToEnterActionNumber.
        builder().
        build().
        loopInput().
        input("Введите цифру из списка, чтобы сделать какое-то дейтсвие: ")!! - 1].
        third()
    } while (true)
}