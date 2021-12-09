interface IInput <T> {
    fun input() : T?
}

interface IValidate <T> {
    fun validate(value: T?) : Boolean
}

interface IValidateIndexOfList <T> {
    fun validate(value: T?, listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>) : Boolean
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

open class ValidateIndexOfList : IValidateIndexOfList<Int> {
    override fun validate(value: Int?, listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase>): Boolean = value != null && value >= 0  && value <= listOfOnlineStoreDataBase.size - 1
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

open class LoopInputIndexOfList {
    fun <T> input(prompt: String, valueInput: IInput<T>, valueValidate: IValidateIndexOfList<T>, listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>): T? {
        var resultValueInput: T?
        do {
            print(prompt)
            resultValueInput = valueInput.input()
            val flagFunction: Boolean = valueValidate.validate(resultValueInput, listOfOnlineStoreDataBase)
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
    open fun print(listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase>) {
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
    fun <T> change(index: Int, field: String, valueChange: T, listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase>) {
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

open class FieldFilterInTheList {
    fun filter(list: MutableMap<String,
            out (OnlineStoreDataBase) -> Any?>,
               fieldOfListForFilter: String,
               listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase>,
               resultPrintSearchAndFilterInformation: PrintSearchAndFilterInformation) {
        val mapForResult = (list[fieldOfListForFilter])
        if (mapForResult != null)
            resultPrintSearchAndFilterInformation.print(listOfOnlineStoreDataBase.sortedBy(mapForResult as (OnlineStoreDataBase) -> Comparable<Any>?))
    }
}

open class FieldSearchInTheList {
    fun search(
        map: MutableMap<String, (String) -> (OnlineStoreDataBase) -> Boolean>,
        valueSearch: String,
        selectNameToChangeTheField: String,
        resultPrintSearchAndFilterInformation: PrintSearchAndFilterInformation,
        listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase>
    ) {
        val mapForResult = map[selectNameToChangeTheField]
        if (mapForResult != null)
            resultPrintSearchAndFilterInformation.print(listOfOnlineStoreDataBase.filter(mapForResult(valueSearch)))
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

class FieldAdd {
    fun add(
        resultLoopInput: LoopInput,
        resultInputNameOrSurnameOfPatronymic: InputNameOrSurnameOfPatronymic,
        resultValidateNameSurnamePatronymic: ValidateNameSurnamePatronymic,
        resultInputCost: InputCost,
        resultValidateCost: ValidateCost,
        resultInputDiscount: InputDiscount,
        resultValidateDiscount: ValidateDiscount,
        resultInputAddress: InputAddress,
        resultValidateAddress: ValidateAddress,
        listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>
            ) {

        val resultOnlineStoreDataBase = OnlineStoreDataBase(
            NameOfOrder(
                resultLoopInput.input(
                    "Введите имя: ",
                    resultInputNameOrSurnameOfPatronymic, resultValidateNameSurnamePatronymic
                )
            ).name,

            SurnameOfOrder(
                resultLoopInput.input(
                    "Введите фамилию: ",
                    resultInputNameOrSurnameOfPatronymic, resultValidateNameSurnamePatronymic
                )
            ).surname,

            PatronymicOfOrder(
                resultLoopInput.input(
                    "Введите отчество: ",
                    resultInputNameOrSurnameOfPatronymic, resultValidateNameSurnamePatronymic
                )
            ).patronymic,

            CostOfOrder(
                resultLoopInput.input(
                    "Введите цену: ",
                    resultInputCost, resultValidateCost
                )
            ).cost,

            DiscountOfOrder(
                resultLoopInput.input(
                    "Введите скидку: ",
                    resultInputDiscount, resultValidateDiscount
                )
            ).discount,

            AddressOfOrder(
                resultLoopInput.input(
                    "Введите адрес: ",
                    resultInputAddress, resultValidateAddress
                )
            ).address
        )
        listOfOnlineStoreDataBase.add(resultOnlineStoreDataBase)
    }
}

class FiledDelete {
    fun delete(resultValidateSizeOfList : ValidateSizeOfList,
               resultDeleteOrder: DeleteOrder,
               listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>,
                resultLoopInput: LoopInputIndexOfList,
               resultInputIndexOfList: InputIndexOfList,
               resultValidateIndexOfList: ValidateIndexOfList) {
        if (resultValidateSizeOfList.validate(listOfOnlineStoreDataBase.size)) {
            val countOfRemove = resultLoopInput.input(
                "Введите номер записи для удаления: ",
                resultInputIndexOfList,
                resultValidateIndexOfList,
                listOfOnlineStoreDataBase
            )
            if (countOfRemove != null)
                resultDeleteOrder.delete(countOfRemove, listOfOnlineStoreDataBase)
            println("Данные удалены")
        } else
            println("Записей нет, чтобы что-то удалить добавьте запись в список")
    }
}

class FiledChange {
    fun change(resultValidateSizeOfList: ValidateSizeOfList,
               resultFieldChangeInTheList: FieldChangeInTheList,
               listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase>,
               resultLoopInputIndexOfList: LoopInputIndexOfList,
               resultLoopInput: LoopInput,
               resultInputNameOrSurnameOfPatronymic: InputNameOrSurnameOfPatronymic,
               resultValidateNameSurnamePatronymic: ValidateNameSurnamePatronymic,
               resultInputCost: InputCost,
               resultValidateCost: ValidateCost,
               resultInputDiscount: InputDiscount,
               resultValidateDiscount: ValidateDiscount,
               resultInputAddress: InputAddress,
               resultValidateAddress: ValidateAddress,
               resultInputIndexOfList: InputIndexOfList,
               resultValidateIndexOfList: ValidateIndexOfList,
               resultInputFieldToChangeOrSearch: InputFieldToChangeOrSearch,
               resultValidateFieldToChangeOrSearch: ValidateFieldToChangeOrSearch) {
        if (resultValidateSizeOfList.validate(listOfOnlineStoreDataBase.size)) {
            val indexOfListForChange = resultLoopInputIndexOfList.input(
                "Введите номер строки для изменения",
                resultInputIndexOfList,
                resultValidateIndexOfList,
                listOfOnlineStoreDataBase
            )

            val fieldOfListForChange = resultLoopInput.input(
                "Напишите - ${selectNameToChangeFilterSearchTheField}, чтобы изменить имя в базе данных " + "\n" +
                        "Напишите - ${selectSurnameToChangeFilterSearchTheField}, чтобы изменить фамилию в базе данных " + "\n" +
                        "Напишите - ${selectPatronymicToChangeFilterSearchTheField}, если изменить отчество в базе данных " + "\n" +
                        "Напишите - ${selectCostToChangeFilterSearchTheField}, чтобы изменить цену в базе данных " + "\n" +
                        "Напишите - ${selectDiscountToChangeFilterSearchTheField}, чтобы изменить скидку в базе данных" + "\n" +
                        "Напишите - ${selectAddressToChangeFilterSearchTheField}, чтобы изменить адрес в базе данных" + "\n",
                resultInputFieldToChangeOrSearch,
                resultValidateFieldToChangeOrSearch
            )
            if (indexOfListForChange != null && fieldOfListForChange != null) {
                when (fieldOfListForChange) {
                    selectNameToChangeFilterSearchTheField ->
                        resultFieldChangeInTheList.change(
                            indexOfListForChange,
                            fieldOfListForChange,
                            resultLoopInput.input(
                                "Введите имя: ",
                                resultInputNameOrSurnameOfPatronymic,
                                resultValidateNameSurnamePatronymic
                            ),
                            listOfOnlineStoreDataBase
                        )

                    selectSurnameToChangeFilterSearchTheField ->
                        resultFieldChangeInTheList.change(
                            indexOfListForChange,
                            fieldOfListForChange,
                            resultLoopInput.input(
                                "Введите фамилия: ",
                                resultInputNameOrSurnameOfPatronymic,
                                resultValidateNameSurnamePatronymic
                            ),
                            listOfOnlineStoreDataBase
                        )

                    selectPatronymicToChangeFilterSearchTheField ->
                        resultFieldChangeInTheList.change(
                            indexOfListForChange,
                            fieldOfListForChange,
                            resultLoopInput.input(
                                "Введите отчество: ",
                                resultInputNameOrSurnameOfPatronymic,
                                resultValidateNameSurnamePatronymic
                            ),
                            listOfOnlineStoreDataBase
                        )

                    selectCostToChangeFilterSearchTheField ->
                        resultFieldChangeInTheList.change(
                            indexOfListForChange,
                            fieldOfListForChange,
                            resultLoopInput.input(
                                "Введите цену: ",
                                resultInputCost,
                                resultValidateCost
                            ),
                            listOfOnlineStoreDataBase
                        )

                    selectDiscountToChangeFilterSearchTheField ->
                        resultFieldChangeInTheList.change(
                            indexOfListForChange,
                            fieldOfListForChange,
                            resultLoopInput.input(
                                "Введите скидку: ",
                                resultInputDiscount,
                                resultValidateDiscount
                            ),
                            listOfOnlineStoreDataBase
                        )

                    selectAddressToChangeFilterSearchTheField ->
                        resultFieldChangeInTheList.change(
                            indexOfListForChange,
                            fieldOfListForChange,
                            resultLoopInput.input(
                                "Введите адрес: ",
                                resultInputAddress,
                                resultValidateAddress
                            ),
                            listOfOnlineStoreDataBase
                        )
                }
            }
        } else println("Записей нет, чтобы что-то изменить добавьте запись в список")
    }
}

class FiledFilter {
    fun filter(resultLoopInput: LoopInput,
               resultInputFieldToChangeOrSearch: InputFieldToChangeOrSearch,
               resultValidateFieldToChangeOrSearch: ValidateFieldToChangeOrSearch,
               resultFieldFilterInTheList: FieldFilterInTheList,
               listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>,
               resultPrintSearchAndFilterInformation: PrintSearchAndFilterInformation) {
        val fieldOfListForFilter = resultLoopInput.input(
            "Напишите - ${selectNameToChangeFilterSearchTheField}, чтобы произвести поиск имени в базе данных " + "\n" +
                    "Напишите - ${selectSurnameToChangeFilterSearchTheField}, чтобы произвести поиск фамилии в базе данных " + "\n" +
                    "Напишите - ${selectPatronymicToChangeFilterSearchTheField}, если произвести поиск отчеству в базе данных " + "\n" +
                    "Напишите - ${selectCostToChangeFilterSearchTheField}, чтобы произвести поиск по цене в базе данных " + "\n" +
                    "Напишите - ${selectDiscountToChangeFilterSearchTheField}, чтобы произвести поиск скидке в базе данных" + "\n" +
                    "Напишите - ${selectAddressToChangeFilterSearchTheField}, чтобы произвести поиск адресу в базе данных" + "\n",
            resultInputFieldToChangeOrSearch,
            resultValidateFieldToChangeOrSearch
        )
        if (fieldOfListForFilter != null)
            resultFieldFilterInTheList.filter(mapForFilter, fieldOfListForFilter, listOfOnlineStoreDataBase, resultPrintSearchAndFilterInformation)
    }
}

open class FiledSearch {
    fun search(resultLoopInput: LoopInput,
               InputFieldToChangeOrSearch: InputFieldToChangeOrSearch,
               resultValidateFieldToChangeOrSearch: ValidateFieldToChangeOrSearch,
               resultFieldSearchInTheList: FieldSearchInTheList,
               resultInputNameOrSurnameOfPatronymic: InputNameOrSurnameOfPatronymic,
               resultValidateNameSurnamePatronymic: ValidateNameSurnamePatronymic,
               resultInputCost: InputCost,
               resultValidateCost: ValidateCost,
               resultInputDiscount: InputDiscount,
               resultValidateDiscount: ValidateDiscount,
               resultInputAddress: InputAddress,
               resultValidateAddress: ValidateAddress,
               listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>,
               resultPrintSearchAndFilterInformation: PrintSearchAndFilterInformation
               ) {
        val fieldOfListForSearch = resultLoopInput.input(
            "Напишите - ${selectNameToChangeFilterSearchTheField}, чтобы произвести поиск имени в базе данных " + "\n" +
                    "Напишите - ${selectSurnameToChangeFilterSearchTheField}, чтобы произвести поиск фамилии в базе данных " + "\n" +
                    "Напишите - ${selectPatronymicToChangeFilterSearchTheField}, если произвести поиск отчеству в базе данных " + "\n" +
                    "Напишите - ${selectCostToChangeFilterSearchTheField}, чтобы произвести поиск по цене в базе данных " + "\n" +
                    "Напишите - ${selectDiscountToChangeFilterSearchTheField}, чтобы произвести поиск скидке в базе данных" + "\n" +
                    "Напишите - ${selectAddressToChangeFilterSearchTheField}, чтобы произвести поиск адресу в базе данных" + "\n",
            InputFieldToChangeOrSearch,
            resultValidateFieldToChangeOrSearch
        )

        when (fieldOfListForSearch) {
            selectNameToChangeFilterSearchTheField -> {
                val resultInput = resultLoopInput.input(
                    "Введите имя: ",
                    resultInputNameOrSurnameOfPatronymic,
                    resultValidateNameSurnamePatronymic
                )

                if (resultInput != null)
                    resultFieldSearchInTheList.search(mapForSearch, resultInput, selectNameToChangeFilterSearchTheField,
                        resultPrintSearchAndFilterInformation, listOfOnlineStoreDataBase)
            }
            selectSurnameToChangeFilterSearchTheField -> {

                val resultInput = resultLoopInput.input(
                    "Введите фамилию: ",
                    resultInputNameOrSurnameOfPatronymic,
                    resultValidateNameSurnamePatronymic
                )

                if (resultInput != null)
                    resultFieldSearchInTheList.search(mapForSearch, resultInput, selectSurnameToChangeFilterSearchTheField,
                        resultPrintSearchAndFilterInformation, listOfOnlineStoreDataBase)
            }
            selectPatronymicToChangeFilterSearchTheField -> {

                val resultInput = resultLoopInput.input(
                    "Введите отчество: ",
                    resultInputNameOrSurnameOfPatronymic,
                    resultValidateNameSurnamePatronymic
                )

                if (resultInput != null)
                    resultFieldSearchInTheList.search(mapForSearch, resultInput, selectPatronymicToChangeFilterSearchTheField,
                    resultPrintSearchAndFilterInformation, listOfOnlineStoreDataBase)
            }
            selectCostToChangeFilterSearchTheField -> {

                val resultInput = resultLoopInput.input(
                    "Введите цену: ",
                    resultInputCost,
                    resultValidateCost
                )

                if (resultInput != null)
                    resultFieldSearchInTheList.search(mapForSearch, resultInput.toString(), selectCostToChangeFilterSearchTheField,
                    resultPrintSearchAndFilterInformation, listOfOnlineStoreDataBase)
            }
            selectDiscountToChangeFilterSearchTheField -> {

                val resultInput = resultLoopInput.input(
                    "Введите скидку: ",
                    resultInputDiscount,
                    resultValidateDiscount
                )

                if (resultInput != null)
                    resultFieldSearchInTheList.search(mapForSearch, resultInput.toString(), selectDiscountToChangeFilterSearchTheField,
                    resultPrintSearchAndFilterInformation, listOfOnlineStoreDataBase)
            }
            selectAddressToChangeFilterSearchTheField -> {

                val resultInput = resultLoopInput.input(
                    "Введите адрес: ",
                    resultInputAddress,
                    resultValidateAddress
                )

                if (resultInput != null)
                    resultFieldSearchInTheList.search(mapForSearch, resultInput, selectAddressToChangeFilterSearchTheField,
                    resultPrintSearchAndFilterInformation, listOfOnlineStoreDataBase)
            }
        }
    }
}

fun main() {
    val resultFieldAdd = FieldAdd()
    val resultLoopInput = LoopInput()
    val resultInputNameOrSurnameOfPatronymic = InputNameOrSurnameOfPatronymic()
    val resultValidateNameSurnamePatronymic = ValidateNameSurnamePatronymic()
    val resultInputCost = InputCost()
    val resultValidateCost = ValidateCost()
    val resultInputDiscount = InputDiscount()
    val resultValidateDiscount = ValidateDiscount()
    val resultInputAddress = InputAddress()
    val resultValidateAddress = ValidateAddress()
    val listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase> = arrayListOf()

    val resultPrintAllInformation = PrintAllInformation()

    val resultFieldDelete = FiledDelete()

    val resultValidateSizeOfList = ValidateSizeOfList()
    val resultDeleteOrder = DeleteOrder()
    val resultLoopInputIndexOfList = LoopInputIndexOfList()
    val resultFieldChangeInTheList = FieldChangeInTheList()
    val resultInputIndexOfList = InputIndexOfList()
    val resultValidateIndexOfList = ValidateIndexOfList()

    val resultFieldFilter = FiledFilter()
    val resultInputFieldToChangeOrSearch = InputFieldToChangeOrSearch()
    val resultValidateFieldToChangeOrSearch = ValidateFieldToChangeOrSearch()
    val resultFieldFilterInTheList =  FieldFilterInTheList()
    val resultPrintSearchAndFilterInformation = PrintSearchAndFilterInformation()


    val resultFieldSearch = FiledSearch()
    val resultFieldToChangeOrSearch = InputFieldToChangeOrSearch()
    val resultFieldSearchInTheList = FieldSearchInTheList()

    val resultFieldChange = FiledChange()

    val mapMenu = listOf(
        Triple(valueToAdd, "Если хотите добавить запись") { resultFieldAdd.add(
            resultLoopInput,
            resultInputNameOrSurnameOfPatronymic,
            resultValidateNameSurnamePatronymic,
            resultInputCost,
            resultValidateCost,
            resultInputDiscount,
            resultValidateDiscount,
            resultInputAddress,
            resultValidateAddress,
            listOfOnlineStoreDataBase
        )  },
        Triple(valueToOutputOfRecords, "Если хотите вывести все записи") { resultPrintAllInformation.print(listOfOnlineStoreDataBase) },
        Triple(valueToDeleteOfRecords, "Если хотите удалить запись") {resultFieldDelete.delete(
            resultValidateSizeOfList,
            resultDeleteOrder,
            listOfOnlineStoreDataBase,
            resultLoopInputIndexOfList,
            resultInputIndexOfList,
            resultValidateIndexOfList
        ) },
        Triple(valueToChangeOfRecords, "Если хотите изменить запись") { resultFieldChange.change(
            resultValidateSizeOfList,
            resultFieldChangeInTheList,
            listOfOnlineStoreDataBase,
            resultLoopInputIndexOfList,
            resultLoopInput,
            resultInputNameOrSurnameOfPatronymic,
            resultValidateNameSurnamePatronymic,
            resultInputCost,
            resultValidateCost,
            resultInputDiscount,
            resultValidateDiscount,
            resultInputAddress,
            resultValidateAddress,
            resultInputIndexOfList,
            resultValidateIndexOfList,
            resultInputFieldToChangeOrSearch,
            resultValidateFieldToChangeOrSearch
        ) },
        Triple(valueToFilterOfRecords, "Если хотите отфильтровать запись") { resultFieldFilter.filter(
            resultLoopInput,
            resultInputFieldToChangeOrSearch,
            resultValidateFieldToChangeOrSearch,
            resultFieldFilterInTheList,
            listOfOnlineStoreDataBase,
            resultPrintSearchAndFilterInformation
            ) },
        Triple(valueToSearchOfRecords, "Если хотите произвести поиск записи") {resultFieldSearch.search(
            resultLoopInput,
            resultFieldToChangeOrSearch,
            resultValidateFieldToChangeOrSearch,
            resultFieldSearchInTheList,
            resultInputNameOrSurnameOfPatronymic,
            resultValidateNameSurnamePatronymic,
            resultInputCost,
            resultValidateCost,
            resultInputDiscount,
            resultValidateDiscount,
            resultInputAddress,
            resultValidateAddress,
            listOfOnlineStoreDataBase,
            resultPrintSearchAndFilterInformation
        ) }
    )
    do {
        for (i in mapMenu)
            println(i.first.toString() + " " + i.second)

        val resultOfInputOfferToEnterActionNumber = LoopInput().input(
            "Введите цифру из списка, чтобы сделать какое-то дейтсвие: ",
            InputActionNumber(),
            ValidateActionNumber()
        )

        mapMenu[resultOfInputOfferToEnterActionNumber!! - 1].third()
    } while (true)
}