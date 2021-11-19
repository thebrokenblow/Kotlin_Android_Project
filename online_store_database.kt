interface IInput <T> {
    fun input() : T?
}

interface IValidate <T> {
    fun validate(value: T?) : Boolean
}

interface HasName1 {
    var name: String?
}

interface HasSurname1 {
    var surname: String?
}

interface HasPatronymic1 {
    var patronymic: String?
}

interface HasCost1 {
    var cost: Double?
}

interface HasDiscount1 {
    var discount: Int?
}

interface HasAddressOfOrder1 {
    var address: String?
}

open class NameOfOrder1(nameOfOrder: String?) : HasName1 {
    override var name: String? = nameOfOrder
}

open class SurnameOfOrder1(surnameOfOrder1: String?) : HasSurname1 {
    override var surname: String? = surnameOfOrder1
}

open class PatronymicOfOrder1(patronymicOfOrder1: String?) : HasPatronymic1 {
    override var patronymic: String? = patronymicOfOrder1
}

open class AddressOfOrder1(addressOfOrder: String?) : HasAddressOfOrder1 {
    override var address: String? = addressOfOrder
}

open class DiscountOfOrder1(discountOfOrder: Int?) : HasDiscount1 {
    override var discount: Int? = discountOfOrder
}

open class CostOfOrder1(discountOfOrder: Double?) : HasCost1 {
    override var cost: Double? = discountOfOrder
}

open class OnlineStoreDataBase(
    override var name: String?,
    override var surname: String?,
    override var patronymic: String?,
    override var cost: Double?,
    override var discount: Int?,
    override var address: String?,
) : HasName1,
    HasSurname1,
    HasPatronymic1,
    HasCost1,
    HasDiscount1,
    HasAddressOfOrder1

open class InputNameOrSurnameOfPatronymic : IInput<String> {
    override fun input(): String? = readLine()
}
open class InputDiscount : IInput<Int>{
    override fun input(): Int? = readLine()?.toIntOrNull()
}

open class InputCost : IInput<Double> {
    override fun input(): Double? = readLine()?.toDoubleOrNull()
}

open class InputAddress : IInput<String> {
    override fun input(): String? = readLine()
}

open class InputIndexToDelete1 : IInput<Int> {
    override fun input(): Int? = readLine()?.toIntOrNull()
}

open class InputActionNumber : IInput<Int>{
    override fun input(): Int? = readLine()?.toIntOrNull()
}

open class ValidateNameSurnamePatronymic : IValidate<String> {
    override fun validate(value: String?) = value != null && Regex("([A-Za-zА-Яа-яёЁ]+)(([-]([A-Za-zА-Яа-яёЁ]+))?)*").matches(value)
}

open class ValidateCost : IValidate<Double> {
    override fun validate(value: Double?): Boolean = value != null && value > 0
}

open class ValidateDiscount : IValidate<Int> {
    override fun validate(value: Int?): Boolean =  value != null && value in 0..100
}

open class ValidateAddress : IValidate<String>{
    override fun validate(value: String?): Boolean = value != ""
}

open class ValidateIndexForRemove : IValidate<Int> {
    override fun validate(value: Int?): Boolean = value != null && value >= 0  && value <= listOfOnlineStoreDataBase.size - 1
}

open class ValidateActionNumber : IValidate<Int> {
    override fun validate(value: Int?): Boolean = value != null && value in valueOfExitingProgram..valueToChangeOfRecords
}

open class LoopInput {
    fun <T> input(prompt: String, valueInput: IInput<T>, valueValidate: IValidate<T>): T? {
        var resultValueInput: T?
        do {
            print(prompt)
            resultValueInput = valueInput.input()
            val flagFunction: Boolean = valueValidate.validate(resultValueInput)
        } while (!flagFunction)
        return resultValueInput
    }
}

open class DeleteOrder1 {
    open fun delete(indexForDelete: Int, listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>){
        listOfOnlineStoreDataBase.removeAt(indexForDelete)
    }
}

open class PrintAboutOrder1 {
    open fun print(listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>) {
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


const val valueNeutral = -1
const val valueOfExitingProgram = 0
const val valueToAdd = 1
const val valueToOutputOfRecords = 2
const val valueToDeleteOfRecords = 3
const val valueToChangeOfRecords = 4

fun offerToEnterActionNumber() { //Функция выбора действия с базой данных (добавление, вывод, удаление, изменение)
    println("Напишите - ${valueToAdd}, если хотите добавить запись")
    println("Напишите - ${valueToOutputOfRecords}, если хотите вывести все записи")
    println("Напишите - ${valueToDeleteOfRecords}, если хотите удалить запись")
    println("Напишите - ${valueToChangeOfRecords}, если хотите изменить запись")
    println("Напишите - ${valueOfExitingProgram}, если хотите выйти из программы")
}

val listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase> = arrayListOf()

fun main() {
    var resultOfInputOfferToEnterActionNumber: Int? = valueNeutral
    while (resultOfInputOfferToEnterActionNumber != valueOfExitingProgram) {
        offerToEnterActionNumber()
        resultOfInputOfferToEnterActionNumber = LoopInput().input(
            "Введите цифру из списка, чтобы сделать какое-то дейтсвие: ",
            InputActionNumber(),
            ValidateActionNumber())

        when (resultOfInputOfferToEnterActionNumber) {
            valueToAdd -> {

                val resultOnlineStoreDataBase =
                    OnlineStoreDataBase(NameOfOrder1(LoopInput().input("Введите имя: ",
                        InputNameOrSurnameOfPatronymic(), ValidateNameSurnamePatronymic())).name,

                        SurnameOfOrder1(LoopInput().input("Введите фамилию: ",
                            InputNameOrSurnameOfPatronymic(), ValidateNameSurnamePatronymic())).surname,

                        PatronymicOfOrder1(LoopInput().input("Введите отчество: ",
                            InputNameOrSurnameOfPatronymic(), ValidateNameSurnamePatronymic())).patronymic,

                        CostOfOrder1(LoopInput().input("Введите цену: ",
                            InputCost(), ValidateCost())).cost,

                        DiscountOfOrder1(LoopInput().input("Введите скидку: ",
                            InputDiscount(), ValidateDiscount())).discount,

                        AddressOfOrder1(LoopInput().input("Введите адрес: ",
                            InputAddress(), ValidateAddress())).address)

                listOfOnlineStoreDataBase.add(resultOnlineStoreDataBase)
            }
            valueToOutputOfRecords -> {
                val printAboutOrder = PrintAboutOrder1()
                printAboutOrder.print(listOfOnlineStoreDataBase)
            }
            valueToDeleteOfRecords -> {
                val deleteOrder = DeleteOrder1()
                if (listOfOnlineStoreDataBase.size > 0 ) {
                    val countOfRemove = LoopInput().input("Введите номер для удаления записи: ",
                        InputIndexToDelete1(), ValidateIndexForRemove())
                    if (countOfRemove != null)
                        deleteOrder.delete(countOfRemove, listOfOnlineStoreDataBase)
                    println("Данные удалены")
                }
                else
                    println("Записей нет")
            }
        }
    }
}