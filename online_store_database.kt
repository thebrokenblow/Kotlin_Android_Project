interface HasAddressOfOrder {
    var address: String
}

open class AddressOfOrder(addressOfOrder: String) : HasAddressOfOrder {
    override var address: String = addressOfOrder
        set(value) {
            if (value != "")
                field = value
        }
}

fun validationAddressOfOrder() : AddressOfOrder { //Функция валидации адреса
    var resultInputAddressOfOrder : String
    do {
        val inputAddressOfOrder = InputAddressOfOrder()
        resultInputAddressOfOrder = inputAddressOfOrder.input()
    } while (resultInputAddressOfOrder == "")
    return AddressOfOrder(resultInputAddressOfOrder)
}

class InputAddressOfOrder {
    fun input(): String { //Функция ввод адреса доставки
        println("Введите адрес")
        return readLine().toString().trim()
    }
}

interface HasDiscount {
    var discount: Int
}

open class DiscountOfOrder(discountOfOrder: Int) : HasDiscount {
    override var discount: Int = discountOfOrder
}

fun inputCorrectDiscountOfOrder(discountOfOrder: Int) : Boolean { //Валидация скидки
    return discountOfOrder in 0..100
}

fun outputErrorAboutIncorrectDiscount() { //Вывод ошибки при вводе скидку в процентах
    println("Вы ввели некорректную скидку")
}

fun validationDiscountOfOrder() : DiscountOfOrder { //Функция валидации скидки
    var resultInputDiscountOfOrder : Int?
    do {
        val inputDiscountOfOrder = InputDiscountOfOrder()
        resultInputDiscountOfOrder = inputDiscountOfOrder.input()
        if (resultInputDiscountOfOrder != null) {
            if (!inputCorrectDiscountOfOrder(resultInputDiscountOfOrder))
                outputErrorAboutIncorrectDiscount()
        } else outputErrorAboutIncorrectDiscount()
    } while (resultInputDiscountOfOrder == null || !inputCorrectDiscountOfOrder(resultInputDiscountOfOrder))
    return DiscountOfOrder(resultInputDiscountOfOrder)
}

class InputDiscountOfOrder {
    fun input(): Int? { //Функция ввода скидки в процентах
        val discountOfOrder: Int?
        println("Введите скидку за товар в процентах")
        val str = readLine().toString()
        discountOfOrder = str.toIntOrNull()
        return discountOfOrder
    }
}

interface HasCost {
    var cost: Double
}

open class CostOfOrder(costOfOrder: Double) : HasCost {
    override var cost: Double = costOfOrder
}

fun inputCorrectCostOfOrder(costOfOrder: Double) : Boolean { //Функция проверки цены на минимальное и максимальное значение
    return costOfOrder > 0.0 && costOfOrder < Double.MAX_VALUE
}

fun outputErrorAboutIncorrectCost() { //Функция вывода ошибки о неправильно введёной цене
    println("Вы ввели некорректную Цену")
}

fun validationCostOfOrder() : CostOfOrder { //Функция валидации цены
    var resultInputCostOfOrder : Double?
    do {
        val inputCostOfOrder = InputCostOfOrder()
        resultInputCostOfOrder = inputCostOfOrder.input()
        if (resultInputCostOfOrder != null) {
            if (!inputCorrectCostOfOrder(resultInputCostOfOrder))
                outputErrorAboutIncorrectCost()
        } else outputErrorAboutIncorrectCost()
    } while (resultInputCostOfOrder == null || !inputCorrectCostOfOrder(resultInputCostOfOrder))
    return CostOfOrder(resultInputCostOfOrder)
}

class InputCostOfOrder {
    fun input(): Double? { //Функция ввода цены заказа
        val costOfOrder: Double?
            println("Введите цену за товар")
            val str = readLine().toString()
            costOfOrder = str.toDoubleOrNull()
        return costOfOrder
    }
}

interface HasName {
    var name: String
    var surname: String
    var patronymic: String
}

open class Person(nameOfPerson: String, surnameOfPerson: String, patronymicOfPerson: String) : HasName {
    override var name: String = nameOfPerson
        set(value) {
            if (value != "")
            field = value
        }
    override var surname: String = surnameOfPerson
        set(value) {
            if (value != "")
                field = value
        }
    override var patronymic: String = patronymicOfPerson
        set(value) {
            if (value != "")
                field = value
        }
}

fun inputCorrectNameSurnamePatronymic(stringToCheck : String) : Boolean { //Функция проверки правильности ввода ФИО
    val result = Regex("([A-Za-zА-Яа-яёЁ-]*)")
    return result.matches(stringToCheck)
}

fun validationNameSurnamePatronymicOfOrder() : Person { //Функция валидации ФИО
    var resultInputNameOfOrder : String
    var resultInputSurnameOfOrder : String
    var resultInputPatronymicOfOrder : String

    do {
        val inputPerson = InputPerson()
        resultInputNameOfOrder = inputPerson.inputName()
        if (!inputCorrectNameSurnamePatronymic(resultInputNameOfOrder))
            println("Вы ввели некорректное имя")
    } while ((resultInputNameOfOrder == "") || !inputCorrectNameSurnamePatronymic(resultInputNameOfOrder))

    do {
        val inputPerson = InputPerson()
        resultInputSurnameOfOrder = inputPerson.inputSurname()
        if (!inputCorrectNameSurnamePatronymic(resultInputSurnameOfOrder))
            println("Вы ввели некорректную фамилию")
    } while ((resultInputSurnameOfOrder == "") || !inputCorrectNameSurnamePatronymic(resultInputSurnameOfOrder))
    do {
        val inputPerson = InputPerson()
        resultInputPatronymicOfOrder = inputPerson.inputPatronymic()
        if (!inputCorrectNameSurnamePatronymic(resultInputPatronymicOfOrder))
            println("Вы ввели некорректное отчество")
    } while (resultInputPatronymicOfOrder == "" && resultInputPatronymicOfOrder == cancellationAction || !inputCorrectNameSurnamePatronymic(resultInputPatronymicOfOrder))
    if (resultInputPatronymicOfOrder == cancellationAction)
        resultInputPatronymicOfOrder = ""
    return Person(resultInputNameOfOrder, resultInputSurnameOfOrder, resultInputPatronymicOfOrder)
}

class InputPerson {
    fun inputName(): String { //Ввод имени заказчика
        println("Введите имя")
        return readLine().toString().trim()
    }
    fun inputSurname() : String { //Ввод фамилии заказчика
        println("Введите фамилию")
        return readLine().toString().trim()
    }
    fun inputPatronymic() : String { //Ввод отчества заказчика
        println("Введите отчество или  напишите '$cancellationAction' при его отсутствии")
        return readLine().toString().trim()
    }
}

class PrintAboutOrder {
    fun print(ListOfPeopleOrder: ArrayList<String>, ListOfCostInformation: ArrayList<Double>,
              ListOfDiscountInformation: ArrayList<Int>, ListOfAddress: ArrayList<String>) { //Функция вывода всей информации в базе данных
        println("Вывод всей информации")
        for (i in ListOfPeopleOrder.indices) {
            print(ListOfPeopleOrder.indexOf(ListOfPeopleOrder[i]).toString() + ") " + "ФИО: " + ListOfPeopleOrder[i] + " ")
            print("Стоимость: " + ListOfCostInformation[i].toString() + " ")
            print("Скидка в процентах: " + ListOfDiscountInformation[i] + "% ")
            println("Адрес: " + ListOfAddress[i])
        }
    }
}

class DeleteOrder {
    fun delete(indexForDelete: Int, ListOfPeopleOrder: ArrayList<String>, ListOfCostInformation: ArrayList<Double>,
               ListOfDiscountInformation: ArrayList<Int>, ListOfAddress: ArrayList<String>){
        ListOfPeopleOrder.removeAt(indexForDelete)
        ListOfCostInformation.removeAt(indexForDelete)
        ListOfDiscountInformation.removeAt(indexForDelete)
        ListOfAddress.removeAt(indexForDelete)
    }
}

const val cancellationAction = "отмена"
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

class InputOfferToEnterActionNumber {
    fun input(): Int { //Функция проверки введённого числа для дейтсвия
        var choosingAction : Int?
        do {
            val str = readLine()
            choosingAction = str?.toIntOrNull()
            if (choosingAction == null) {
                println("Вы ввели не число, выберите из списка действие, которое хотите провести и введите число ещё раз")
                offerToEnterActionNumber()
            }
            else if (choosingAction !in valueOfExitingProgram..valueToChangeOfRecords) {
                println("Вы ввели число, которого нет в списке, выберите из списка действие, которое хотите провести и введите число ещё раз")
                offerToEnterActionNumber()
            }
        } while (choosingAction == null)
        return choosingAction
    }
}

fun validationInputIndexToDelete(ListOfPeopleOrder: ArrayList<String>) : Int { //Функция валидации индекса списка для удаления
    var flagListIndex = true
    var countOfRemove: Int?
    do {
        val inputIndexToDelete = InputIndexToDelete()
        countOfRemove = inputIndexToDelete.input()
        if (countOfRemove != null)
            if (countOfRemove == valueNeutral || countOfRemove in 0..ListOfPeopleOrder.size)
                flagListIndex = false
            else println("Нет номера такой записи")
    } while (countOfRemove == null || flagListIndex)
    return countOfRemove
}

class InputIndexToDelete {
    fun input(): Int? { //Функция ввода индекса списка для удаления
        println("Введите номер записи, которую вы хотите удалить или напиши '$valueNeutral' для отмены действия")
        val stringCountOfRemove: String? = readLine()
        return stringCountOfRemove?.toIntOrNull()
    }
}

fun outputOfInformationDatabase() { //Функция вывода название базы данных
    println("База данных заказов Интернет-магазина")
}

fun main() {

    val listOfPerson : ArrayList<String> = arrayListOf()
    val listOfCost : ArrayList<Double> = arrayListOf()
    val listOfDiscount : ArrayList<Int> = arrayListOf()
    val listOfAddressOfOrder : ArrayList<String> = arrayListOf()
    outputOfInformationDatabase()
    var resultOfInputOfferToEnterActionNumber = valueNeutral
    while (resultOfInputOfferToEnterActionNumber != valueOfExitingProgram) {
        offerToEnterActionNumber()
        val inputOfferToEnterActionNumber = InputOfferToEnterActionNumber()
        resultOfInputOfferToEnterActionNumber = inputOfferToEnterActionNumber.input()
        when (resultOfInputOfferToEnterActionNumber) {
            valueToAdd -> {
                val resultInputPerson = validationNameSurnamePatronymicOfOrder()
                val person = Person(resultInputPerson.name, resultInputPerson.surname, resultInputPerson.patronymic)

                val resultInputCostOfOrder = validationCostOfOrder()
                val costOfOrder = CostOfOrder(resultInputCostOfOrder.cost)

                val resultInputDiscountOfOrder = validationDiscountOfOrder()
                val discountOfOrder = DiscountOfOrder(resultInputDiscountOfOrder.discount)

                val resultInputAddressOfOrder = validationAddressOfOrder()
                val addressOfOrder = AddressOfOrder(resultInputAddressOfOrder.address)

                listOfPerson.add(person.name + " " + person.surname + " " + person.patronymic)
                listOfCost.add(costOfOrder.cost)
                listOfDiscount.add(discountOfOrder.discount)
                listOfAddressOfOrder.add(addressOfOrder.address)
            }
            valueToOutputOfRecords -> {
                val printAboutOrder = PrintAboutOrder()
                printAboutOrder.print(listOfPerson, listOfCost, listOfDiscount, listOfAddressOfOrder)
            }
            valueToDeleteOfRecords -> {
                val deleteOrder = DeleteOrder()
                if (listOfPerson.size != 0) {
                    val countOfRemove = validationInputIndexToDelete(listOfPerson)
                    if (countOfRemove != valueNeutral) {
                        deleteOrder.delete(countOfRemove, listOfPerson, listOfCost, listOfDiscount, listOfAddressOfOrder)
                        println("Данные удалены")
                    }
                }
                else
                    println("Записей нет")
            }
            valueToChangeOfRecords -> {
            }
        }
    }
}