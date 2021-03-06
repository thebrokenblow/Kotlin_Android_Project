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
    override fun validate(value: String?) = value != null && Regex("([A-Za-z??-????-??????]+)(([-]([A-Za-z??-????-??????]+))?)*").matches(value)
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
        println("?????????? ???????? ????????????????????")
        for (i in listOfOnlineStoreDataBase.indices) {
            println(i.toString() + ") ??????: " + listOfOnlineStoreDataBase[i].surname + " " +
                    listOfOnlineStoreDataBase[i].name + " " +
                    listOfOnlineStoreDataBase[i].patronymic + " " +
                    "????????: " + listOfOnlineStoreDataBase[i].cost + " " +
                    "????????????: " + listOfOnlineStoreDataBase[i].discount + " " +
                    "??????????: " + listOfOnlineStoreDataBase[i].address)
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
        println("?????????? ???????? ????????????????????")
        for ((count, i) in list.withIndex()) {
            println(count.toString() +
                    ") ??????: " +
                    i.surname + " " +
                    i.name + " " +
                    i.patronymic + " " +
                    "????????: " +
                    i.cost + " " +
                    "????????????: " +
                    i.discount + " " +
                    "??????????: " +
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

const val selectNameToChangeFilterSearchTheField = "??????"
const val selectSurnameToChangeFilterSearchTheField = "??????????????"
const val selectPatronymicToChangeFilterSearchTheField = "????????????????"
const val selectCostToChangeFilterSearchTheField = "????????"
const val selectDiscountToChangeFilterSearchTheField = "????????????"
const val selectAddressToChangeFilterSearchTheField = "??????????"

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
                    "?????????????? ??????: ",
                    resultInputNameOrSurnameOfPatronymic, resultValidateNameSurnamePatronymic
                )
            ).name,

            SurnameOfOrder(
                resultLoopInput.input(
                    "?????????????? ??????????????: ",
                    resultInputNameOrSurnameOfPatronymic, resultValidateNameSurnamePatronymic
                )
            ).surname,

            PatronymicOfOrder(
                resultLoopInput.input(
                    "?????????????? ????????????????: ",
                    resultInputNameOrSurnameOfPatronymic, resultValidateNameSurnamePatronymic
                )
            ).patronymic,

            CostOfOrder(
                resultLoopInput.input(
                    "?????????????? ????????: ",
                    resultInputCost, resultValidateCost
                )
            ).cost,

            DiscountOfOrder(
                resultLoopInput.input(
                    "?????????????? ????????????: ",
                    resultInputDiscount, resultValidateDiscount
                )
            ).discount,

            AddressOfOrder(
                resultLoopInput.input(
                    "?????????????? ??????????: ",
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
                "?????????????? ?????????? ???????????? ?????? ????????????????: ",
                resultInputIndexOfList,
                resultValidateIndexOfList,
                listOfOnlineStoreDataBase
            )
            if (countOfRemove != null)
                resultDeleteOrder.delete(countOfRemove, listOfOnlineStoreDataBase)
            println("???????????? ??????????????")
        } else
            println("?????????????? ??????, ?????????? ??????-???? ?????????????? ???????????????? ???????????? ?? ????????????")
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
                "?????????????? ?????????? ???????????? ?????? ??????????????????",
                resultInputIndexOfList,
                resultValidateIndexOfList,
                listOfOnlineStoreDataBase
            )

            val fieldOfListForChange = resultLoopInput.input(
                "???????????????? - ${selectNameToChangeFilterSearchTheField}, ?????????? ???????????????? ?????? ?? ???????? ???????????? " + "\n" +
                        "???????????????? - ${selectSurnameToChangeFilterSearchTheField}, ?????????? ???????????????? ?????????????? ?? ???????? ???????????? " + "\n" +
                        "???????????????? - ${selectPatronymicToChangeFilterSearchTheField}, ???????? ???????????????? ???????????????? ?? ???????? ???????????? " + "\n" +
                        "???????????????? - ${selectCostToChangeFilterSearchTheField}, ?????????? ???????????????? ???????? ?? ???????? ???????????? " + "\n" +
                        "???????????????? - ${selectDiscountToChangeFilterSearchTheField}, ?????????? ???????????????? ???????????? ?? ???????? ????????????" + "\n" +
                        "???????????????? - ${selectAddressToChangeFilterSearchTheField}, ?????????? ???????????????? ?????????? ?? ???????? ????????????" + "\n",
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
                                "?????????????? ??????: ",
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
                                "?????????????? ??????????????: ",
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
                                "?????????????? ????????????????: ",
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
                                "?????????????? ????????: ",
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
                                "?????????????? ????????????: ",
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
                                "?????????????? ??????????: ",
                                resultInputAddress,
                                resultValidateAddress
                            ),
                            listOfOnlineStoreDataBase
                        )
                }
            }
        } else println("?????????????? ??????, ?????????? ??????-???? ???????????????? ???????????????? ???????????? ?? ????????????")
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
            "???????????????? - ${selectNameToChangeFilterSearchTheField}, ?????????? ???????????????????? ?????????? ?????????? ?? ???????? ???????????? " + "\n" +
                    "???????????????? - ${selectSurnameToChangeFilterSearchTheField}, ?????????? ???????????????????? ?????????? ?????????????? ?? ???????? ???????????? " + "\n" +
                    "???????????????? - ${selectPatronymicToChangeFilterSearchTheField}, ???????? ???????????????????? ?????????? ???????????????? ?? ???????? ???????????? " + "\n" +
                    "???????????????? - ${selectCostToChangeFilterSearchTheField}, ?????????? ???????????????????? ?????????? ???? ???????? ?? ???????? ???????????? " + "\n" +
                    "???????????????? - ${selectDiscountToChangeFilterSearchTheField}, ?????????? ???????????????????? ?????????? ???????????? ?? ???????? ????????????" + "\n" +
                    "???????????????? - ${selectAddressToChangeFilterSearchTheField}, ?????????? ???????????????????? ?????????? ???????????? ?? ???????? ????????????" + "\n",
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
            "???????????????? - ${selectNameToChangeFilterSearchTheField}, ?????????? ???????????????????? ?????????? ?????????? ?? ???????? ???????????? " + "\n" +
                    "???????????????? - ${selectSurnameToChangeFilterSearchTheField}, ?????????? ???????????????????? ?????????? ?????????????? ?? ???????? ???????????? " + "\n" +
                    "???????????????? - ${selectPatronymicToChangeFilterSearchTheField}, ???????? ???????????????????? ?????????? ???????????????? ?? ???????? ???????????? " + "\n" +
                    "???????????????? - ${selectCostToChangeFilterSearchTheField}, ?????????? ???????????????????? ?????????? ???? ???????? ?? ???????? ???????????? " + "\n" +
                    "???????????????? - ${selectDiscountToChangeFilterSearchTheField}, ?????????? ???????????????????? ?????????? ???????????? ?? ???????? ????????????" + "\n" +
                    "???????????????? - ${selectAddressToChangeFilterSearchTheField}, ?????????? ???????????????????? ?????????? ???????????? ?? ???????? ????????????" + "\n",
            InputFieldToChangeOrSearch,
            resultValidateFieldToChangeOrSearch
        )

        when (fieldOfListForSearch) {
            selectNameToChangeFilterSearchTheField -> {
                val resultInput = resultLoopInput.input(
                    "?????????????? ??????: ",
                    resultInputNameOrSurnameOfPatronymic,
                    resultValidateNameSurnamePatronymic
                )

                if (resultInput != null)
                    resultFieldSearchInTheList.search(mapForSearch, resultInput, selectNameToChangeFilterSearchTheField,
                        resultPrintSearchAndFilterInformation, listOfOnlineStoreDataBase)
            }
            selectSurnameToChangeFilterSearchTheField -> {

                val resultInput = resultLoopInput.input(
                    "?????????????? ??????????????: ",
                    resultInputNameOrSurnameOfPatronymic,
                    resultValidateNameSurnamePatronymic
                )

                if (resultInput != null)
                    resultFieldSearchInTheList.search(mapForSearch, resultInput, selectSurnameToChangeFilterSearchTheField,
                        resultPrintSearchAndFilterInformation, listOfOnlineStoreDataBase)
            }
            selectPatronymicToChangeFilterSearchTheField -> {

                val resultInput = resultLoopInput.input(
                    "?????????????? ????????????????: ",
                    resultInputNameOrSurnameOfPatronymic,
                    resultValidateNameSurnamePatronymic
                )

                if (resultInput != null)
                    resultFieldSearchInTheList.search(mapForSearch, resultInput, selectPatronymicToChangeFilterSearchTheField,
                    resultPrintSearchAndFilterInformation, listOfOnlineStoreDataBase)
            }
            selectCostToChangeFilterSearchTheField -> {

                val resultInput = resultLoopInput.input(
                    "?????????????? ????????: ",
                    resultInputCost,
                    resultValidateCost
                )

                if (resultInput != null)
                    resultFieldSearchInTheList.search(mapForSearch, resultInput.toString(), selectCostToChangeFilterSearchTheField,
                    resultPrintSearchAndFilterInformation, listOfOnlineStoreDataBase)
            }
            selectDiscountToChangeFilterSearchTheField -> {

                val resultInput = resultLoopInput.input(
                    "?????????????? ????????????: ",
                    resultInputDiscount,
                    resultValidateDiscount
                )

                if (resultInput != null)
                    resultFieldSearchInTheList.search(mapForSearch, resultInput.toString(), selectDiscountToChangeFilterSearchTheField,
                    resultPrintSearchAndFilterInformation, listOfOnlineStoreDataBase)
            }
            selectAddressToChangeFilterSearchTheField -> {

                val resultInput = resultLoopInput.input(
                    "?????????????? ??????????: ",
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
        Triple(valueToAdd, "???????? ???????????? ???????????????? ????????????") { resultFieldAdd.add(
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
        Triple(valueToOutputOfRecords, "???????? ???????????? ?????????????? ?????? ????????????") { resultPrintAllInformation.print(listOfOnlineStoreDataBase) },
        Triple(valueToDeleteOfRecords, "???????? ???????????? ?????????????? ????????????") {resultFieldDelete.delete(
            resultValidateSizeOfList,
            resultDeleteOrder,
            listOfOnlineStoreDataBase,
            resultLoopInputIndexOfList,
            resultInputIndexOfList,
            resultValidateIndexOfList
        ) },
        Triple(valueToChangeOfRecords, "???????? ???????????? ???????????????? ????????????") { resultFieldChange.change(
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
        Triple(valueToFilterOfRecords, "???????? ???????????? ?????????????????????????? ????????????") { resultFieldFilter.filter(
            resultLoopInput,
            resultInputFieldToChangeOrSearch,
            resultValidateFieldToChangeOrSearch,
            resultFieldFilterInTheList,
            listOfOnlineStoreDataBase,
            resultPrintSearchAndFilterInformation
            ) },
        Triple(valueToSearchOfRecords, "???????? ???????????? ???????????????????? ?????????? ????????????") {resultFieldSearch.search(
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
            "?????????????? ?????????? ???? ????????????, ?????????? ?????????????? ??????????-???? ????????????????: ",
            InputActionNumber(),
            ValidateActionNumber()
        )

        mapMenu[resultOfInputOfferToEnterActionNumber!! - 1].third()
    } while (true)
}