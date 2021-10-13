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

class InputAddressOfOrder {
    fun input(): AddressOfOrder {
        var address: String
        do {
            println("Введите адрес")
            address = readLine().toString().trim()
            if (!correctInputNameSurnamePatronymic(address))
                println("Вы ввели некорректное имя")
        } while(address == "")
        return AddressOfOrder(address)
    }
}

interface HasDiscount {
    var discount: Int
}

open class DiscountOfOrder(discountOfOrder: Int) : HasDiscount {
    override var discount: Int = discountOfOrder
}

fun correctInputDiscountOfOrder(discountOfOrder: Int) : Boolean {
    return discountOfOrder in 0..100
}

fun errorOutputAboutIncorrectDiscount() {
    println("Вы ввели некорректную скидку")
}

class InputDiscountOfOrder {
    fun input(): DiscountOfOrder {
        var discountOfOrder: Int?
        do {
            println("Введите скидку за товар в процентах")
            val str = readLine().toString()
            discountOfOrder = str.toIntOrNull()
            if (discountOfOrder != null) {
                if (!correctInputDiscountOfOrder(discountOfOrder))
                    errorOutputAboutIncorrectDiscount()
            } else errorOutputAboutIncorrectDiscount()
        } while (discountOfOrder == null || !correctInputDiscountOfOrder(discountOfOrder))
        return DiscountOfOrder(discountOfOrder)
    }
}

interface HasCost {
    var cost: Double
}

open class CostOfOrder(costOfOrder: Double) : HasCost {
    override var cost: Double = costOfOrder
}

fun correctInputCostOfOrder(costOfOrder: Double) : Boolean {
    return costOfOrder > 0.0 && costOfOrder < Double.MAX_VALUE
}

fun errorOutputAboutIncorrectCost() {
    println("Вы ввели некорректную Цену")
}

class InputCostOfOrder {
    fun input(): CostOfOrder {
        var costOfOrder: Double?
        do {
            println("Введите цену за товар")
            val str = readLine().toString()
            costOfOrder = str.toDoubleOrNull()
            if (costOfOrder != null) {
                if (!correctInputCostOfOrder(costOfOrder))
                    errorOutputAboutIncorrectCost()
            } else errorOutputAboutIncorrectCost()
        } while (costOfOrder == null || !correctInputCostOfOrder(costOfOrder))
        return CostOfOrder(costOfOrder)
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

fun correctInputNameSurnamePatronymic(stringToCheck : String) : Boolean {
    val result = Regex("([A-Za-zА-Яа-яёЁ-]*)")
    return result.matches(stringToCheck)
}

class InputPerson {
    fun input(): Person {
        var name: String
        var surname: String
        var patronymic: String
        do {
         println("Введите имя")
            name = readLine().toString().trim()
            if (!correctInputNameSurnamePatronymic(name))
                println("Вы ввели некорректное имя")
        } while(name == "" || !correctInputNameSurnamePatronymic(name))
        do {
            println("Введите фамилию")
            surname = readLine().toString().trim()
            if (!correctInputNameSurnamePatronymic(surname))
                println("Вы ввели некорректную фамилию")
        } while (surname == "" || !correctInputNameSurnamePatronymic(surname))
        do {
            println("Введите отчество или  напишите '$absenceOfPatronymic' при его отсутствии")
            patronymic = readLine().toString().trim()
            if (!correctInputNameSurnamePatronymic(surname))
                println("Вы ввели некорректное отчество")
        } while (surname == "" || surname == absenceOfPatronymic || !correctInputNameSurnamePatronymic(patronymic))
        if (patronymic == absenceOfPatronymic)
            patronymic = ""
        return Person(name, surname, patronymic)
    }
}

class PrintAboutOrder {
    fun print(ListOfPeopleOrder: ArrayList<String>, ListOfCostInformation: ArrayList<Double>,
              ListOfDiscountInformation: ArrayList<Int>, ListOfAddress: ArrayList<String>) {
        println("Вывод всей информации")
        for (i in ListOfPeopleOrder.indices) {
            print("ФИО: " + ListOfPeopleOrder[i] + " ")
            print("Стоимость: " + ListOfCostInformation[i].toString() + " ")
            print("Скидка в процентах: " + ListOfDiscountInformation[i] + "%")
            println("Адрес: " + ListOfAddress[i])
        }
    }
}

const val absenceOfPatronymic = "отмена"
const val valueOfExitingProgram = 0
const val valueToAdd = 1
const val OutputOfRecords = 2

fun main() {
    var choosingAction: Int? = -1
    val listOfPerson : ArrayList<String> = arrayListOf()
    val listOfCost : ArrayList<Double> = arrayListOf()
    val listOfDiscount : ArrayList<Int> = arrayListOf()
    val listOfAddressOfOrder : ArrayList<String> = arrayListOf()
    println("База данных заказов Интернет-магазина")
    while (choosingAction != valueOfExitingProgram) {
        println("Напишите - ${valueToAdd}, если хотите добавить запись")
        println("Напишите - ${OutputOfRecords}, если хотите вывести все записи")
        println("Напишите - ${valueOfExitingProgram}, если хотите вывести все записи")
        val str = readLine()
        choosingAction = str?.toIntOrNull()
        if (choosingAction != null){
            if (choosingAction == valueToAdd) {
                val inputPerson = InputPerson()
                val resultInputPerson = inputPerson.input()
                val person = Person(resultInputPerson.name, resultInputPerson.surname, resultInputPerson.patronymic)

                val inputCostOfOrder = InputCostOfOrder()
                val resultInputCostOfOrder = inputCostOfOrder.input()
                val costOfOrder = CostOfOrder(resultInputCostOfOrder.cost)

                val inputDiscountOfOrder = InputDiscountOfOrder()
                val resultInputDiscountOfOrder = inputDiscountOfOrder.input()
                val discountOfOrder = DiscountOfOrder(resultInputDiscountOfOrder.discount)

                val inputAddressOfOrder = InputAddressOfOrder()
                val resultInputAddressOfOrder = inputAddressOfOrder.input()
                val addressOfOrder = AddressOfOrder(resultInputAddressOfOrder.address)

                listOfPerson.add(person.name + " " + person.surname + " " + person.patronymic)
                listOfCost.add(costOfOrder.cost)
                listOfDiscount.add(discountOfOrder.discount)
                listOfAddressOfOrder.add(addressOfOrder.address)
            }
            else if (choosingAction == OutputOfRecords) {
                val printAboutOrder = PrintAboutOrder()
                printAboutOrder.print(listOfPerson, listOfCost, listOfDiscount, listOfAddressOfOrder)
            }
        } else println("Вы ввели не число")
    }
}