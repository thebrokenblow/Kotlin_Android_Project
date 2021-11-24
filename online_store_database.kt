interface IInput <T> {
    fun input() : T?
}

interface IValidate <T> {
    fun validate(value: T?) : Boolean
}

interface IHasName {
    var name: String?
}

interface IHasSurname {
    var surname: String?
}

interface IHasPatronymic {
    var patronymic: String?
}

interface IHasCost {
    var cost: Double?
}

interface IHasDiscount {
    var discount: Int?
}

interface IHasAddressOfOrder {
    var address: String?
}

open class NameOfOrder(nameOfOrder: String?) : IHasName {
    override var name: String? = nameOfOrder
}

open class SurnameOfOrder(surnameOfOrder: String?) : IHasSurname {
    override var surname: String? = surnameOfOrder
}

open class PatronymicOfOrder(patronymicOfOrder: String?) : IHasPatronymic {
    override var patronymic: String? = patronymicOfOrder
}

open class CostOfOrder(discountOfOrder: Double?) : IHasCost {
    override var cost: Double? = discountOfOrder
}

open class DiscountOfOrder(discountOfOrder: Int?) : IHasDiscount {
    override var discount: Int? = discountOfOrder
}

open class AddressOfOrder(addressOfOrder: String?) : IHasAddressOfOrder {
    override var address: String? = addressOfOrder
}


open class OnlineStoreDataBase(
    override var name: String?,
    override var surname: String?,
    override var patronymic: String?,
    override var cost: Double?,
    override var discount: Int?,
    override var address: String?,
) : IHasName,
    IHasSurname,
    IHasPatronymic,
    IHasCost,
    IHasDiscount,
    IHasAddressOfOrder

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

open class InputIndexOfList: IInput<Int> {
    override fun input(): Int? = readLine()?.toIntOrNull()
}

open class InputActionNumber : IInput<Int>{
    override fun input(): Int? = readLine()?.toIntOrNull()
}

open class InputFieldToChangeOrSearch: IInput<String> {
    override fun input(): String? = readLine()
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

open class ValidateIndexOfList : IValidate<Int> {
    override fun validate(value: Int?): Boolean = value != null && value >= 0  && value <= listOfOnlineStoreDataBase.size - 1
}

open class ValidateActionNumber : IValidate<Int> {
    override fun validate(value: Int?): Boolean = value != null && value in valueOfExitingProgram..valueToSearchOfRecords
}

open class ValidateSizeOfList : IValidate<Int> {
    override fun validate(value: Int?): Boolean = value != null && value > 0
}

open class ValidateFieldToChangeOrSearch : IValidate<String> {
    override fun validate(value: String?): Boolean = value == selectNameToChangeTheField ||
                                                     value == selectSurnameToChangeTheField ||
                                                     value == selectPatronymicToChangeTheField ||
                                                     value == selectCostToChangeTheField ||
                                                     value == selectDiscountToChangeTheField ||
                                                     value == selectAddressToChangeTheField
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

open class DeleteOrder {
    open fun delete(indexForDelete: Int, listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>){
        listOfOnlineStoreDataBase.removeAt(indexForDelete)
    }
}

open class PrintAboutOrder {
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

class FieldChangeInTheList {
    fun <T> change(index: Int, field: String, valueChange: T) {
        when (field) {
            selectNameToChangeTheField -> listOfOnlineStoreDataBase[index].name = valueChange.toString()

            selectSurnameToChangeTheField -> listOfOnlineStoreDataBase[index].surname = valueChange.toString()

            selectPatronymicToChangeTheField -> listOfOnlineStoreDataBase[index].patronymic = valueChange.toString()

            selectCostToChangeTheField -> listOfOnlineStoreDataBase[index].cost = valueChange.toString().toDoubleOrNull()

            selectDiscountToChangeTheField -> listOfOnlineStoreDataBase[index].discount = valueChange.toString().toIntOrNull()

            selectAddressToChangeTheField -> listOfOnlineStoreDataBase[index].patronymic = valueChange.toString()
         }
    }
}

class FieldSearchInTheList {
    fun search(
        map: MutableMap<String, (String) -> (OnlineStoreDataBase) -> Boolean>,
        valueSearch: String,
        selectNameToChangeTheField: String
    ) {
        val mapForResult = map[selectNameToChangeTheField]
        if (mapForResult != null)
            PrintAboutOrder().print(listOfOnlineStoreDataBase.filter(mapForResult(valueSearch))  as ArrayList<OnlineStoreDataBase>)
    }
}

class FieldFilterInTheList {
    fun filter() {
            val t = listOfOnlineStoreDataBase.sortedBy{ x -> x.name }
            PrintAboutOrder1().print(t)
    }
}

open class PrintAboutOrder1 {
    open fun print(t: List<OnlineStoreDataBase>) {
        println("Вывод всей информации")
        for (i in t) {
            println(i.toString() + ") ФИО: " + i.surname + " " +
                    i.name + " " +
                    i.patronymic + " " +
                    "Цена: " + i.cost + " " +
                    "Скидка: " + i.discount + " " +
                    "Адрес: " + i.address)
        }
    }
}

const val valueNeutral = -1
const val valueOfExitingProgram = 0
const val valueToAdd = 1
const val valueToOutputOfRecords = 2
const val valueToDeleteOfRecords = 3
const val valueToChangeOfRecords = 4
const val valueToFilterOfRecords = 5
const val valueToSearchOfRecords = 6

const val selectNameToChangeTheField = "Имя"
const val selectSurnameToChangeTheField = "Фамилия"
const val selectPatronymicToChangeTheField = "Отчество"
const val selectCostToChangeTheField = "Цена"
const val selectDiscountToChangeTheField = "Скидка"
const val selectAddressToChangeTheField = "Адрес"

fun offerToEnterActionNumber() { //ListIsTriple число/ формулировка путкла/ лямбда
    println("Напишите - ${valueToAdd}, если хотите добавить запись")
    println("Напишите - ${valueToOutputOfRecords}, если хотите вывести все записи")
    println("Напишите - ${valueToDeleteOfRecords}, если хотите удалить запись")
    println("Напишите - ${valueToChangeOfRecords}, если хотите изменить запись")
    println("Напишите - ${valueToFilterOfRecords}, если хотите отфильтровать запись")
    println("Напишите - ${valueToSearchOfRecords}, если хотите произвести поиск записи")
    println("Напишите - ${valueOfExitingProgram}, если хотите выйти из программы")
}
//val t = listOf(Triple(valueToAdd, "Если хотите добавить запись", ))
val listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase> = arrayListOf()

val map = mutableMapOf(selectNameToChangeTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.name == valueSearch}},
    selectSurnameToChangeTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.surname == valueSearch}},
    selectPatronymicToChangeTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.patronymic == valueSearch}},
    selectCostToChangeTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.cost.toString() == valueSearch}},
    selectDiscountToChangeTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.discount.toString() == valueSearch}},
    selectAddressToChangeTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.address == valueSearch}})

fun main() {
    var resultOfInputOfferToEnterActionNumber: Int? = valueNeutral
    while (resultOfInputOfferToEnterActionNumber != valueOfExitingProgram) {
        offerToEnterActionNumber()

        resultOfInputOfferToEnterActionNumber = LoopInput().input(
            "Введите цифру из списка, чтобы сделать какое-то дейтсвие: ",
            InputActionNumber(),
            ValidateActionNumber()
        )
        when (resultOfInputOfferToEnterActionNumber) {

            valueToAdd -> {
                val resultOnlineStoreDataBase =
                    OnlineStoreDataBase(
                        NameOfOrder(
                            LoopInput().input(
                                "Введите имя: ",
                                InputNameOrSurnameOfPatronymic(), ValidateNameSurnamePatronymic()
                            )
                        ).name,

                        SurnameOfOrder(
                            LoopInput().input(
                                "Введите фамилию: ",
                                InputNameOrSurnameOfPatronymic(), ValidateNameSurnamePatronymic()
                            )
                        ).surname,

                        PatronymicOfOrder(
                            LoopInput().input(
                                "Введите отчество: ",
                                InputNameOrSurnameOfPatronymic(), ValidateNameSurnamePatronymic()
                            )
                        ).patronymic,

                        CostOfOrder(
                            LoopInput().input(
                                "Введите цену: ",
                                InputCost(), ValidateCost()
                            )
                        ).cost,

                        DiscountOfOrder(
                            LoopInput().input(
                                "Введите скидку: ",
                                InputDiscount(), ValidateDiscount()
                            )
                        ).discount,

                        AddressOfOrder(
                            LoopInput().input(
                                "Введите адрес: ",
                                InputAddress(), ValidateAddress()
                            )
                        ).address
                    )
                listOfOnlineStoreDataBase.add(resultOnlineStoreDataBase)
            }

            valueToOutputOfRecords -> {
                PrintAboutOrder().print(listOfOnlineStoreDataBase)
            }

            valueToDeleteOfRecords -> {
                if (ValidateSizeOfList().validate(listOfOnlineStoreDataBase.size)) {
                    val countOfRemove = LoopInput().input(
                        "Введите номер записи для удаления: ",
                        InputIndexOfList(),
                        ValidateIndexOfList()
                    )
                    if (countOfRemove != null)
                        DeleteOrder().delete(countOfRemove, listOfOnlineStoreDataBase)
                    println("Данные удалены")
                } else
                    println("Записей нет, чтобы что-то удалить добавьте запись в список")
            }

            valueToChangeOfRecords -> {
                if (ValidateSizeOfList().validate(listOfOnlineStoreDataBase.size)) {
                    val indexOfListForChange = LoopInput().input(
                        "Введите номер строки для изменения",
                        InputIndexOfList(),
                        ValidateIndexOfList()
                    )

                    val fieldOfListForChange = LoopInput().input(
                        "Напишите - ${selectNameToChangeTheField}, чтобы изменить имя в базе данных " + "\n" +
                                "Напишите - ${selectSurnameToChangeTheField}, чтобы изменить фамилию в базе данных " + "\n" +
                                "Напишите - ${selectPatronymicToChangeTheField}, если изменить отчество в базе данных " + "\n" +
                                "Напишите - ${selectCostToChangeTheField}, чтобы изменить цену в базе данных " + "\n" +
                                "Напишите - ${selectDiscountToChangeTheField}, чтобы изменить скидку в базе данных" + "\n" +
                                "Напишите - ${selectAddressToChangeTheField}, чтобы изменить адрес в базе данных" + "\n",
                        InputFieldToChangeOrSearch(),
                        ValidateFieldToChangeOrSearch()
                    )
                    if (indexOfListForChange != null && fieldOfListForChange != null) {
                        when (fieldOfListForChange) {
                            selectNameToChangeTheField ->
                                FieldChangeInTheList().change(
                                    indexOfListForChange,
                                    fieldOfListForChange,
                                    LoopInput().input(
                                        "Введите имя: ",
                                        InputNameOrSurnameOfPatronymic(),
                                        ValidateNameSurnamePatronymic()
                                    )
                                )

                            selectSurnameToChangeTheField ->
                                FieldChangeInTheList().change(
                                    indexOfListForChange,
                                    fieldOfListForChange,
                                    LoopInput().input(
                                        "Введите фамилия: ",
                                        InputNameOrSurnameOfPatronymic(),
                                        ValidateNameSurnamePatronymic()
                                    )
                                )

                            selectPatronymicToChangeTheField ->
                                FieldChangeInTheList().change(
                                    indexOfListForChange,
                                    fieldOfListForChange,
                                    LoopInput().input(
                                        "Введите отчество: ",
                                        InputNameOrSurnameOfPatronymic(),
                                        ValidateNameSurnamePatronymic()
                                    )
                                )

                            selectCostToChangeTheField ->
                                FieldChangeInTheList().change(
                                    indexOfListForChange,
                                    fieldOfListForChange,
                                    LoopInput().input(
                                        "Введите цену: ",
                                        InputCost(),
                                        ValidateCost()
                                    )
                                )

                            selectDiscountToChangeTheField ->
                                FieldChangeInTheList().change(
                                    indexOfListForChange,
                                    fieldOfListForChange,
                                    LoopInput().input(
                                        "Введите скидку: ",
                                        InputDiscount(),
                                        ValidateDiscount()
                                    )
                                )

                            selectAddressToChangeTheField ->
                                FieldChangeInTheList().change(
                                    indexOfListForChange,
                                    fieldOfListForChange,
                                    LoopInput().input(
                                        "Введите адрес: ",
                                        InputAddress(),
                                        ValidateAddress()
                                    )
                                )
                        }
                    }
                } else println("Записей нет, чтобы что-то изменить добавьте запись в список")
            }

            valueToFilterOfRecords -> {
                val fieldOfListForFilter = LoopInput().input(
                    "Напишите - ${selectNameToChangeTheField}, чтобы произвести поиск имени в базе данных " + "\n" +
                            "Напишите - ${selectSurnameToChangeTheField}, чтобы произвести поиск фамилии в базе данных " + "\n" +
                            "Напишите - ${selectPatronymicToChangeTheField}, если произвести поиск отчеству в базе данных " + "\n" +
                            "Напишите - ${selectCostToChangeTheField}, чтобы произвести поиск по цене в базе данных " + "\n" +
                            "Напишите - ${selectDiscountToChangeTheField}, чтобы произвести поиск скидке в базе данных" + "\n" +
                            "Напишите - ${selectAddressToChangeTheField}, чтобы произвести поиск адресу в базе данных" + "\n",
                    InputFieldToChangeOrSearch(),
                    ValidateFieldToChangeOrSearch()
                )
                FieldFilterInTheList().filter()
                when(fieldOfListForFilter) {
                    selectNameToChangeTheField -> {
                            //FieldFilterInTheList().filter(map, fieldOfListForFilter)
                    }
                }
            }
            valueToSearchOfRecords -> {
                val fieldOfListForSearch = LoopInput().input(
                    "Напишите - ${selectNameToChangeTheField}, чтобы произвести поиск имени в базе данных " + "\n" +
                            "Напишите - ${selectSurnameToChangeTheField}, чтобы произвести поиск фамилии в базе данных " + "\n" +
                            "Напишите - ${selectPatronymicToChangeTheField}, если произвести поиск отчеству в базе данных " + "\n" +
                            "Напишите - ${selectCostToChangeTheField}, чтобы произвести поиск по цене в базе данных " + "\n" +
                            "Напишите - ${selectDiscountToChangeTheField}, чтобы произвести поиск скидке в базе данных" + "\n" +
                            "Напишите - ${selectAddressToChangeTheField}, чтобы произвести поиск адресу в базе данных" + "\n",
                    InputFieldToChangeOrSearch(),
                    ValidateFieldToChangeOrSearch()
                )

                when (fieldOfListForSearch) {
                    selectNameToChangeTheField -> {

                        val resultLoopInput = LoopInput().input(
                            "Введите имя: ",
                            InputNameOrSurnameOfPatronymic(),
                            ValidateNameSurnamePatronymic()
                        )

                        if (resultLoopInput != null)
                        FieldSearchInTheList().search(map, resultLoopInput, selectNameToChangeTheField)
                    }
                    selectSurnameToChangeTheField -> {

                        val resultLoopInput = LoopInput().input(
                            "Введите фамилию: ",
                            InputNameOrSurnameOfPatronymic(),
                            ValidateNameSurnamePatronymic()
                        )

                        if (resultLoopInput != null)
                            FieldSearchInTheList().search(map, resultLoopInput, selectSurnameToChangeTheField)
                    }
                    selectPatronymicToChangeTheField -> {

                        val resultLoopInput = LoopInput().input(
                            "Введите отчество: ",
                            InputNameOrSurnameOfPatronymic(),
                            ValidateNameSurnamePatronymic()
                        )

                        if (resultLoopInput != null)
                            FieldSearchInTheList().search(map, resultLoopInput, selectPatronymicToChangeTheField)
                    }
                    selectCostToChangeTheField -> {

                        val resultLoopInput = LoopInput().input(
                            "Введите цену: ",
                            InputCost(),
                            ValidateCost()
                        )

                        if (resultLoopInput != null)
                            FieldSearchInTheList().search(map, resultLoopInput.toString(), selectCostToChangeTheField)
                    }
                    selectDiscountToChangeTheField -> {

                        val resultLoopInput = LoopInput().input(
                            "Введите скидку: ",
                            InputDiscount(),
                            ValidateDiscount()
                        )

                        if (resultLoopInput != null)
                            FieldSearchInTheList().search(map, resultLoopInput.toString(), selectDiscountToChangeTheField)
                    }
                    selectAddressToChangeTheField -> {

                        val resultLoopInput = LoopInput().input(
                            "Введите адрес: ",
                            InputAddress(),
                            ValidateAddress()
                        )

                        if (resultLoopInput != null)
                            FieldSearchInTheList().search(map, resultLoopInput, selectAddressToChangeTheField)
                    }
                }
            }
        }
    }
}