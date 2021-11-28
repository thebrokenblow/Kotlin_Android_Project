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
    override fun validate(value: String?): Boolean = value == selectNameToChangeFilterSearchTheField ||
                                                     value == selectSurnameToChangeFilterSearchTheField ||
                                                     value == selectPatronymicToChangeFilterSearchTheField ||
                                                     value == selectCostToChangeFilterSearchTheField ||
                                                     value == selectDiscountToChangeFilterSearchTheField ||
                                                     value == selectAddressToChangeFilterSearchTheField
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

open class PrintAllInformation {
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

class FieldChangeInTheList {
    fun <T> change(index: Int, field: String, valueChange: T) {
        when (field) {
            selectNameToChangeFilterSearchTheField -> listOfOnlineStoreDataBase[index].name = valueChange.toString()

            selectSurnameToChangeFilterSearchTheField -> listOfOnlineStoreDataBase[index].surname = valueChange.toString()

            selectPatronymicToChangeFilterSearchTheField -> listOfOnlineStoreDataBase[index].patronymic = valueChange.toString()

            selectCostToChangeFilterSearchTheField -> listOfOnlineStoreDataBase[index].cost = valueChange.toString().toDoubleOrNull()

            selectDiscountToChangeFilterSearchTheField -> listOfOnlineStoreDataBase[index].discount = valueChange.toString().toIntOrNull()

            selectAddressToChangeFilterSearchTheField -> listOfOnlineStoreDataBase[index].patronymic = valueChange.toString()
         }
    }
}

class FieldFilterInTheList {
    fun filter(list: MutableMap<String, out (OnlineStoreDataBase) -> Any?>, fieldOfListForFilter: String) {
        val t = (list[fieldOfListForFilter])
        if (t != null)
        PrintSearchAndFilterInformation().print(listOfOnlineStoreDataBase.sortedBy(t as (OnlineStoreDataBase) -> Comparable<Any>?))
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
            PrintSearchAndFilterInformation().print(listOfOnlineStoreDataBase.filter(mapForResult(valueSearch)))
    }
}

open class PrintSearchAndFilterInformation {
    open fun print(list: List<OnlineStoreDataBase>) {
        println("Вывод всей информации")
        for ((count, i) in list.withIndex()) {
            println(count.toString() +
                    ") ФИО: " +
                    i.surname + " " +
                    i.name + " " +
                    i.patronymic + " " +
                    "Цена: " +
                    i.cost + " " +
                    "Скидка: " +
                    i.discount + " " +
                    "Адрес: " +
                    i.address)
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

const val selectNameToChangeFilterSearchTheField = "Имя"
const val selectSurnameToChangeFilterSearchTheField = "Фамилия"
const val selectPatronymicToChangeFilterSearchTheField = "Отчество"
const val selectCostToChangeFilterSearchTheField = "Цена"
const val selectDiscountToChangeFilterSearchTheField = "Скидка"
const val selectAddressToChangeFilterSearchTheField = "Адрес"

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

val mapForSearch = mutableMapOf(
    selectNameToChangeFilterSearchTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.name == valueSearch}},
    selectSurnameToChangeFilterSearchTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.surname == valueSearch}},
    selectPatronymicToChangeFilterSearchTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.patronymic == valueSearch}},
    selectCostToChangeFilterSearchTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.cost.toString() == valueSearch}},
    selectDiscountToChangeFilterSearchTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.discount.toString() == valueSearch}},
    selectAddressToChangeFilterSearchTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.address == valueSearch}}
)

val mapForFilter = mutableMapOf(
    selectNameToChangeFilterSearchTheField to {x: OnlineStoreDataBase -> x.name as String?},
    selectSurnameToChangeFilterSearchTheField to {x: OnlineStoreDataBase -> x.surname as String?},
    selectPatronymicToChangeFilterSearchTheField to {x: OnlineStoreDataBase -> x.patronymic as String?},
    selectCostToChangeFilterSearchTheField to {x: OnlineStoreDataBase -> x.cost as Double?},
    selectDiscountToChangeFilterSearchTheField to {x: OnlineStoreDataBase -> x.discount as Int?},
    selectAddressToChangeFilterSearchTheField to {x: OnlineStoreDataBase -> x.address as String?}
)

val mapMenu = mutableMapOf(
    valueToAdd to {x: OnlineStoreDataBase -> }
)

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
                val resultOnlineStoreDataBase = OnlineStoreDataBase(
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
                PrintAllInformation().print()
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
                        "Напишите - ${selectNameToChangeFilterSearchTheField}, чтобы изменить имя в базе данных " + "\n" +
                                "Напишите - ${selectSurnameToChangeFilterSearchTheField}, чтобы изменить фамилию в базе данных " + "\n" +
                                "Напишите - ${selectPatronymicToChangeFilterSearchTheField}, если изменить отчество в базе данных " + "\n" +
                                "Напишите - ${selectCostToChangeFilterSearchTheField}, чтобы изменить цену в базе данных " + "\n" +
                                "Напишите - ${selectDiscountToChangeFilterSearchTheField}, чтобы изменить скидку в базе данных" + "\n" +
                                "Напишите - ${selectAddressToChangeFilterSearchTheField}, чтобы изменить адрес в базе данных" + "\n",
                        InputFieldToChangeOrSearch(),
                        ValidateFieldToChangeOrSearch()
                    )
                    if (indexOfListForChange != null && fieldOfListForChange != null) {
                        when (fieldOfListForChange) {
                            selectNameToChangeFilterSearchTheField ->
                                FieldChangeInTheList().change(
                                    indexOfListForChange,
                                    fieldOfListForChange,
                                    LoopInput().input(
                                        "Введите имя: ",
                                        InputNameOrSurnameOfPatronymic(),
                                        ValidateNameSurnamePatronymic()
                                    )
                                )

                            selectSurnameToChangeFilterSearchTheField ->
                                FieldChangeInTheList().change(
                                    indexOfListForChange,
                                    fieldOfListForChange,
                                    LoopInput().input(
                                        "Введите фамилия: ",
                                        InputNameOrSurnameOfPatronymic(),
                                        ValidateNameSurnamePatronymic()
                                    )
                                )

                            selectPatronymicToChangeFilterSearchTheField ->
                                FieldChangeInTheList().change(
                                    indexOfListForChange,
                                    fieldOfListForChange,
                                    LoopInput().input(
                                        "Введите отчество: ",
                                        InputNameOrSurnameOfPatronymic(),
                                        ValidateNameSurnamePatronymic()
                                    )
                                )

                            selectCostToChangeFilterSearchTheField ->
                                FieldChangeInTheList().change(
                                    indexOfListForChange,
                                    fieldOfListForChange,
                                    LoopInput().input(
                                        "Введите цену: ",
                                        InputCost(),
                                        ValidateCost()
                                    )
                                )

                            selectDiscountToChangeFilterSearchTheField ->
                                FieldChangeInTheList().change(
                                    indexOfListForChange,
                                    fieldOfListForChange,
                                    LoopInput().input(
                                        "Введите скидку: ",
                                        InputDiscount(),
                                        ValidateDiscount()
                                    )
                                )

                            selectAddressToChangeFilterSearchTheField ->
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
                    "Напишите - ${selectNameToChangeFilterSearchTheField}, чтобы произвести поиск имени в базе данных " + "\n" +
                            "Напишите - ${selectSurnameToChangeFilterSearchTheField}, чтобы произвести поиск фамилии в базе данных " + "\n" +
                            "Напишите - ${selectPatronymicToChangeFilterSearchTheField}, если произвести поиск отчеству в базе данных " + "\n" +
                            "Напишите - ${selectCostToChangeFilterSearchTheField}, чтобы произвести поиск по цене в базе данных " + "\n" +
                            "Напишите - ${selectDiscountToChangeFilterSearchTheField}, чтобы произвести поиск скидке в базе данных" + "\n" +
                            "Напишите - ${selectAddressToChangeFilterSearchTheField}, чтобы произвести поиск адресу в базе данных" + "\n",
                    InputFieldToChangeOrSearch(),
                    ValidateFieldToChangeOrSearch()
                )
                if (fieldOfListForFilter != null)
                    FieldFilterInTheList().filter(mapForFilter, fieldOfListForFilter)
            }
            valueToSearchOfRecords -> {
                val fieldOfListForSearch = LoopInput().input(
                    "Напишите - ${selectNameToChangeFilterSearchTheField}, чтобы произвести поиск имени в базе данных " + "\n" +
                            "Напишите - ${selectSurnameToChangeFilterSearchTheField}, чтобы произвести поиск фамилии в базе данных " + "\n" +
                            "Напишите - ${selectPatronymicToChangeFilterSearchTheField}, если произвести поиск отчеству в базе данных " + "\n" +
                            "Напишите - ${selectCostToChangeFilterSearchTheField}, чтобы произвести поиск по цене в базе данных " + "\n" +
                            "Напишите - ${selectDiscountToChangeFilterSearchTheField}, чтобы произвести поиск скидке в базе данных" + "\n" +
                            "Напишите - ${selectAddressToChangeFilterSearchTheField}, чтобы произвести поиск адресу в базе данных" + "\n",
                    InputFieldToChangeOrSearch(),
                    ValidateFieldToChangeOrSearch()
                )

                when (fieldOfListForSearch) {
                    selectNameToChangeFilterSearchTheField -> {

                        val resultLoopInput = LoopInput().input(
                            "Введите имя: ",
                            InputNameOrSurnameOfPatronymic(),
                            ValidateNameSurnamePatronymic()
                        )

                        if (resultLoopInput != null)
                            FieldSearchInTheList().search(mapForSearch, resultLoopInput, selectNameToChangeFilterSearchTheField)
                    }
                    selectSurnameToChangeFilterSearchTheField -> {

                        val resultLoopInput = LoopInput().input(
                            "Введите фамилию: ",
                            InputNameOrSurnameOfPatronymic(),
                            ValidateNameSurnamePatronymic()
                        )

                        if (resultLoopInput != null)
                            FieldSearchInTheList().search(mapForSearch, resultLoopInput, selectSurnameToChangeFilterSearchTheField)
                    }
                    selectPatronymicToChangeFilterSearchTheField -> {

                        val resultLoopInput = LoopInput().input(
                            "Введите отчество: ",
                            InputNameOrSurnameOfPatronymic(),
                            ValidateNameSurnamePatronymic()
                        )

                        if (resultLoopInput != null)
                            FieldSearchInTheList().search(mapForSearch, resultLoopInput, selectPatronymicToChangeFilterSearchTheField)
                    }
                    selectCostToChangeFilterSearchTheField -> {

                        val resultLoopInput = LoopInput().input(
                            "Введите цену: ",
                            InputCost(),
                            ValidateCost()
                        )

                        if (resultLoopInput != null)
                            FieldSearchInTheList().search(mapForSearch, resultLoopInput.toString(), selectCostToChangeFilterSearchTheField)
                    }
                    selectDiscountToChangeFilterSearchTheField -> {

                        val resultLoopInput = LoopInput().input(
                            "Введите скидку: ",
                            InputDiscount(),
                            ValidateDiscount()
                        )

                        if (resultLoopInput != null)
                            FieldSearchInTheList().search(mapForSearch, resultLoopInput.toString(), selectDiscountToChangeFilterSearchTheField)
                    }
                    selectAddressToChangeFilterSearchTheField -> {

                        val resultLoopInput = LoopInput().input(
                            "Введите адрес: ",
                            InputAddress(),
                            ValidateAddress()
                        )

                        if (resultLoopInput != null)
                            FieldSearchInTheList().search(mapForSearch, resultLoopInput, selectAddressToChangeFilterSearchTheField)
                    }
                }
            }
        }
    }
}