//import dagger.Component
//import dagger.Module
//import javax.inject.Inject
//
//@Component(modules=[IInputModule::class])
//interface IInput <T> {
//    fun input() : T?
//}
//
//@Module interface IInputModule
//
//@Component(modules=[IValidateModule::class])
//interface IValidate <T> {
//    fun validate(value: T?) : Boolean
//}
//
//@Module interface IValidateModule
//
//@Component(modules=[IValidateIndexOfListModule::class])
//interface IValidateIndexOfList <T> {
//    fun validate(value: T?, listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>) : Boolean
//}
//
//@Module interface IValidateIndexOfListModule
//
//@Component(modules=[IHasNameModule::class])
//interface IHasName {
//    var name: String?
//}
//
//@Module interface IHasNameModule
//
//@Component(modules=[IHasSurnameModule::class])
//interface IHasSurname {
//    var surname: String?
//}
//
//@Module interface IHasSurnameModule
//
//@Component(modules=[IHasPatronymicModule::class])
//interface IHasPatronymic {
//    var patronymic: String?
//}
//
//@Module interface IHasPatronymicModule
//
//@Component(modules=[IHasCostModule::class])
//interface IHasCost {
//    var cost: Double?
//}
//
//@Module interface IHasCostModule
//
//@Component(modules=[IHasDiscountModule::class])
//interface IHasDiscount {
//    var discount: Int?
//}
//
//@Module interface IHasDiscountModule
//
//@Component(modules=[IHasAddressModule::class])
//interface IHasAddressOfOrder {
//    var address: String?
//}
//
//@Module interface IHasAddressModule
//
//open class NameOfOrder @Inject constructor (nameOfOrder: String?) : IHasName {
//    override var name: String? = nameOfOrder
//}
//
//open class SurnameOfOrder @Inject constructor (surnameOfOrder: String?) : IHasSurname {
//    override var surname: String? = surnameOfOrder
//}
//
//open class PatronymicOfOrder @Inject constructor (patronymicOfOrder: String?) : IHasPatronymic {
//    override var patronymic: String? = patronymicOfOrder
//}
//
//open class CostOfOrder @Inject constructor (discountOfOrder: Double?) : IHasCost {
//    override var cost: Double? = discountOfOrder
//}
//
//open class DiscountOfOrder @Inject constructor (discountOfOrder: Int?) : IHasDiscount {
//    override var discount: Int? = discountOfOrder
//}
//
//open class AddressOfOrder @Inject constructor (addressOfOrder: String?) : IHasAddressOfOrder {
//    override var address: String? = addressOfOrder
//}
//
//open class OnlineStoreDataBase @Inject constructor(
//    override var name: String?,
//    override var surname: String?,
//    override var patronymic: String?,
//    override var cost: Double?,
//    override var discount: Int?,
//    override var address: String?,
//) : IHasName,
//    IHasSurname,
//    IHasPatronymic,
//    IHasCost,
//    IHasDiscount,
//    IHasAddressOfOrder
//
//open class InputNameOrSurnameOfPatronymic @Inject constructor() : IInput<String> {
//    override fun input(): String? = readLine()
//}
//
//open class InputDiscount @Inject constructor() : IInput<Int>{
//    override fun input(): Int? = readLine()?.toIntOrNull()
//}
//
//open class InputCost @Inject constructor() : IInput<Double> {
//    override fun input(): Double? = readLine()?.toDoubleOrNull()
//}
//
//open class InputAddress @Inject constructor() : IInput<String> {
//    override fun input(): String? = readLine()
//}
//
//open class InputIndexOfList @Inject constructor() : IInput<Int> {
//    override fun input(): Int? = readLine()?.toIntOrNull()
//}
//
//open class InputActionNumber @Inject constructor() : IInput<Int>{
//    override fun input(): Int? = readLine()?.toIntOrNull()
//}
//
//open class InputFieldToChangeOrSearch @Inject constructor() : IInput<String> {
//    override fun input(): String? = readLine()
//}
//
//open class ValidateNameSurnamePatronymic @Inject constructor() : IValidate<String> {
//    override fun validate(value: String?) = value != null && Regex("([A-Za-zА-Яа-яёЁ]+)(([-]([A-Za-zА-Яа-яёЁ]+))?)*").matches(value)
//}
//
//open class ValidateCost @Inject constructor() : IValidate<Double> {
//    override fun validate(value: Double?): Boolean = value != null && value > 0
//}
//
//open class ValidateDiscount @Inject constructor() : IValidate<Int> {
//    override fun validate(value: Int?): Boolean =  value != null && value in 0..100
//}
//
//open class ValidateAddress @Inject constructor() : IValidate<String>{
//    override fun validate(value: String?): Boolean = value != ""
//}
//
//open class ValidateIndexOfList @Inject constructor() : IValidateIndexOfList<Int> {
//    override fun validate(value: Int?, listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase>): Boolean = value != null && value >= 0  && value <= listOfOnlineStoreDataBase.size - 1
//}
//
//open class ValidateActionNumber @Inject constructor() : IValidate<Int> {
//    override fun validate(value: Int?): Boolean = value != null && value in valueOfExitingProgram..valueToSearchOfRecords
//}
//
//open class ValidateSizeOfList @Inject constructor() : IValidate<Int> {
//    override fun validate(value: Int?): Boolean = value != null && value > 0
//}
//
//open class ValidateFieldToChangeOrSearch @Inject constructor() : IValidate<String> {
//    override fun validate(value: String?): Boolean = value == selectNameToChangeFilterSearchTheField ||
//            value == selectSurnameToChangeFilterSearchTheField ||
//            value == selectPatronymicToChangeFilterSearchTheField ||
//            value == selectCostToChangeFilterSearchTheField ||
//            value == selectDiscountToChangeFilterSearchTheField ||
//            value == selectAddressToChangeFilterSearchTheField
//}
//
//open class LoopInput <T> @Inject constructor (
//    private val prompt: String,
//    private val valueInput: IInput<T>,
//    private val valueValidate: IValidate<T>) {
//    fun input(): T? {
//        var resultValueInput: T?
//        do {
//            print(prompt)
//            resultValueInput = valueInput.input()
//            val flagFunction: Boolean = valueValidate.validate(resultValueInput)
//        } while (!flagFunction)
//        return resultValueInput
//    }
//}
//
//open class LoopInputIndexOfList <T> @Inject constructor(
//    private val prompt: String,
//    private val valueInput: IInput<T>,
//    private val valueValidate: IValidateIndexOfList<T>,
//    private val listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>) {
//    fun <T> input(): T? {
//        var resultValueInput: T?
//        do {
//            print(prompt)
//            resultValueInput = valueInput.input()
//            val flagFunction: Boolean = valueValidate.validate(resultValueInput, listOfOnlineStoreDataBase)
//        } while (!flagFunction)
//        return resultValueInput
//    }
//}
//
//open class DeleteOrder @Inject constructor(
//    private val indexForDelete: Int,
//    private val listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>) {
//    open fun delete() {
//        listOfOnlineStoreDataBase.removeAt(indexForDelete)
//    }
//}
//
//open class PrintAllInformation @Inject constructor(
//    private val listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase>){
//    open fun print() {
//        println("Вывод всей информации")
//        for (i in listOfOnlineStoreDataBase.indices) {
//            println(i.toString() + ") ФИО: " + listOfOnlineStoreDataBase[i].surname + " " +
//                    listOfOnlineStoreDataBase[i].name + " " +
//                    listOfOnlineStoreDataBase[i].patronymic + " " +
//                    "Цена: " + listOfOnlineStoreDataBase[i].cost + " " +
//                    "Скидка: " + listOfOnlineStoreDataBase[i].discount + " " +
//                    "Адрес: " + listOfOnlineStoreDataBase[i].address)
//        }
//    }
//}
//
//open class FieldChangeInTheList <T> @Inject constructor(
//    private val index: Int,
//    private val field: String,
//    private val valueChange: T,
//    private val listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase>) {
//    fun change() {
//        when (field) {
//            selectNameToChangeFilterSearchTheField -> listOfOnlineStoreDataBase[index].name = valueChange.toString()
//            selectSurnameToChangeFilterSearchTheField -> listOfOnlineStoreDataBase[index].surname = valueChange.toString()
//            selectPatronymicToChangeFilterSearchTheField -> listOfOnlineStoreDataBase[index].patronymic = valueChange.toString()
//            selectCostToChangeFilterSearchTheField -> listOfOnlineStoreDataBase[index].cost = valueChange.toString().toDoubleOrNull()
//            selectDiscountToChangeFilterSearchTheField -> listOfOnlineStoreDataBase[index].discount = valueChange.toString().toIntOrNull()
//            selectAddressToChangeFilterSearchTheField -> listOfOnlineStoreDataBase[index].patronymic = valueChange.toString()
//        }
//    }
//}
//
//open class FieldFilterInTheList @Inject constructor(
//    private val list: MutableMap<String,
//            out (OnlineStoreDataBase) -> Any?>,
//    private val fieldOfListForFilter: String,
//    private val listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase>, ) {
//    fun filter() {
//        val mapForResult = (list[fieldOfListForFilter])
//        if (mapForResult != null) {
//            val resultPrintSearchAndFilterInformation =
//                PrintSearchAndFilterInformation(listOfOnlineStoreDataBase.sortedBy(mapForResult as (OnlineStoreDataBase) -> Comparable<Any>?))
//            resultPrintSearchAndFilterInformation.print()
//        }
//    }
//}
//
//open class FieldSearchInTheList @Inject constructor (
//    private val map: MutableMap<String, (String) -> (OnlineStoreDataBase) -> Boolean>,
//    private val valueSearch: String,
//    private val selectNameToChangeTheField: String,
//    private val listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase>) {
//    fun search() {
//        val mapForResult = map[selectNameToChangeTheField]
//        if (mapForResult != null) {
//            val resultPrintSearchAndFilterInformation = PrintSearchAndFilterInformation(listOfOnlineStoreDataBase.filter(mapForResult(valueSearch)))
//            resultPrintSearchAndFilterInformation.print()
//        }
//    }
//}
//
//open class PrintSearchAndFilterInformation @Inject constructor(private val list: List<OnlineStoreDataBase>) {
//    fun print() {
//        println("Вывод всей информации")
//        for ((count, i) in list.withIndex()) {
//            println(count.toString() +
//                    ") ФИО: " +
//                    i.surname + " " +
//                    i.name + " " +
//                    i.patronymic + " " +
//                    "Цена: " +
//                    i.cost + " " +
//                    "Скидка: " +
//                    i.discount + " " +
//                    "Адрес: " +
//                    i.address)
//        }
//    }
//}
//
//const val valueOfExitingProgram = 0
//const val valueToAdd = 1
//const val valueToOutputOfRecords = 2
//const val valueToDeleteOfRecords = 3
//const val valueToChangeOfRecords = 4
//const val valueToFilterOfRecords = 5
//const val valueToSearchOfRecords = 6
//
//const val selectNameToChangeFilterSearchTheField = "Имя"
//const val selectSurnameToChangeFilterSearchTheField = "Фамилия"
//const val selectPatronymicToChangeFilterSearchTheField = "Отчество"
//const val selectCostToChangeFilterSearchTheField = "Цена"
//const val selectDiscountToChangeFilterSearchTheField = "Скидка"
//const val selectAddressToChangeFilterSearchTheField = "Адрес"
//
//val mapForSearch = mutableMapOf(
//    selectNameToChangeFilterSearchTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.name == valueSearch}},
//    selectSurnameToChangeFilterSearchTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.surname == valueSearch}},
//    selectPatronymicToChangeFilterSearchTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.patronymic == valueSearch}},
//    selectCostToChangeFilterSearchTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.cost.toString() == valueSearch}},
//    selectDiscountToChangeFilterSearchTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.discount.toString() == valueSearch}},
//    selectAddressToChangeFilterSearchTheField to {valueSearch: String -> {x: OnlineStoreDataBase -> x.address == valueSearch}}
//)
//
//val mapForFilter = mutableMapOf(
//    selectNameToChangeFilterSearchTheField to {x: OnlineStoreDataBase -> x.name as String?},
//    selectSurnameToChangeFilterSearchTheField to {x: OnlineStoreDataBase -> x.surname as String?},
//    selectPatronymicToChangeFilterSearchTheField to {x: OnlineStoreDataBase -> x.patronymic as String?},
//    selectCostToChangeFilterSearchTheField to {x: OnlineStoreDataBase -> x.cost as Double?},
//    selectDiscountToChangeFilterSearchTheField to {x: OnlineStoreDataBase -> x.discount as Int?},
//    selectAddressToChangeFilterSearchTheField to {x: OnlineStoreDataBase -> x.address as String?}
//)
//
//class FieldAdd @Inject constructor(
//    private val resultInputNameOrSurnameOfPatronymic: InputNameOrSurnameOfPatronymic,
//    private val resultValidateNameSurnamePatronymic: ValidateNameSurnamePatronymic,
//    private val resultInputCost: InputCost,
//    private val resultValidateCost: ValidateCost,
//    private val resultInputDiscount: InputDiscount,
//    private val resultValidateDiscount: ValidateDiscount,
//    private val resultInputAddress: InputAddress,
//    private val resultValidateAddress: ValidateAddress,
//    private val listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>) {
//    fun add() {
//        val resultLoopInputName = LoopInput(
//            "Введите имя: ",
//            resultInputNameOrSurnameOfPatronymic,
//            resultValidateNameSurnamePatronymic)
//
//        val resultLoopInputSurname = LoopInput(
//            "Введите фамилию: ",
//            resultInputNameOrSurnameOfPatronymic,
//            resultValidateNameSurnamePatronymic)
//
//        val resultLoopInputPatronymic = LoopInput(
//            "Введите отчество: ",
//            resultInputNameOrSurnameOfPatronymic,
//            resultValidateNameSurnamePatronymic)
//
//        val resultLoopInputCost = LoopInput(
//            "Введите цену: ",
//            resultInputCost,
//            resultValidateCost)
//
//        val resultLoopInputDiscount = LoopInput(
//            "Введите скидку: ",
//            resultInputDiscount,
//            resultValidateDiscount)
//
//        val resultLoopInputAddress = LoopInput(
//            "Введите адрес: ",
//            resultInputAddress,
//            resultValidateAddress)
//
//        val resultOnlineStoreDataBase = OnlineStoreDataBase(
//            NameOfOrder(resultLoopInputName.input()).name,
//            SurnameOfOrder(resultLoopInputSurname.input()).surname,
//            PatronymicOfOrder(resultLoopInputPatronymic.input()).patronymic,
//            CostOfOrder(resultLoopInputCost.input()).cost,
//            DiscountOfOrder(resultLoopInputDiscount.input()).discount,
//            AddressOfOrder(resultLoopInputAddress.input()).address)
//
//        listOfOnlineStoreDataBase.add(resultOnlineStoreDataBase)
//    }
//}
//
//class FiledDelete @Inject constructor(
//    private val resultValidateSizeOfList : ValidateSizeOfList,
//    private val listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>,
//    private val resultInputIndexOfList: InputIndexOfList,
//    private val resultValidateIndexOfList: ValidateIndexOfList) {
//    fun delete() {
//        if (resultValidateSizeOfList.validate(listOfOnlineStoreDataBase.size)) {
//            InputIndexOfList().input()
//            val resultLoopInput = LoopInputIndexOfList(
//                "Введите номер записи для удаления: ",
//                resultInputIndexOfList,
//                resultValidateIndexOfList,
//                listOfOnlineStoreDataBase)
//
//            val countOfRemove = resultLoopInput.input<Int>()
//            if (countOfRemove != null) {
//                val resultDeleteOrder = DeleteOrder(countOfRemove, listOfOnlineStoreDataBase)
//                resultDeleteOrder.delete()
//            }
//            println("Данные удалены")
//        } else
//            println("Записей нет, чтобы что-то удалить добавьте запись в список")
//    }
//
//}//class FiledDelete @Inject constructor(
//    private val resultValidateSizeOfList : ValidateSizeOfList,
//    private val listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>,
//    private val resultInputIndexOfList: InputIndexOfList,
//    private val resultValidateIndexOfList: ValidateIndexOfList) {
//    fun delete() {
//        if (resultValidateSizeOfList.validate(listOfOnlineStoreDataBase.size)) {
//            InputIndexOfList().input()
//            val resultLoopInput = LoopInputIndexOfList(
//                "Введите номер записи для удаления: ",
//                resultInputIndexOfList,
//                resultValidateIndexOfList,
//                listOfOnlineStoreDataBase)
//
//            val countOfRemove = resultLoopInput.input<Int>()
//            if (countOfRemove != null) {
//                val resultDeleteOrder = DeleteOrder(countOfRemove, listOfOnlineStoreDataBase)
//                resultDeleteOrder.delete()
//            }
//            println("Данные удалены")
//        } else
//            println("Записей нет, чтобы что-то удалить добавьте запись в список")
//    }
//}
//
//class FiledChange @Inject constructor(
//    private val resultValidateSizeOfList: ValidateSizeOfList,
//    private val listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase>,
//    private val resultInputNameOrSurnameOfPatronymic: InputNameOrSurnameOfPatronymic,
//    private val resultValidateNameSurnamePatronymic: ValidateNameSurnamePatronymic,
//    private val resultInputCost: InputCost,
//    private val resultValidateCost: ValidateCost,
//    private val resultInputDiscount: InputDiscount,
//    private val resultValidateDiscount: ValidateDiscount,
//    private val resultInputAddress: InputAddress,
//    private val resultValidateAddress: ValidateAddress,
//    private val resultInputIndexOfList: InputIndexOfList,
//    private val resultValidateIndexOfList: ValidateIndexOfList,
//    private val resultInputFieldToChangeOrSearch: InputFieldToChangeOrSearch,
//    private val resultValidateFieldToChangeOrSearch: ValidateFieldToChangeOrSearch) {
//    fun change() {
//        if (resultValidateSizeOfList.validate(listOfOnlineStoreDataBase.size)) {
//            val resultLoopInputIndexOfList =  LoopInputIndexOfList(
//                "Введите номер строки для изменения",
//                resultInputIndexOfList,
//                resultValidateIndexOfList,
//                listOfOnlineStoreDataBase)
//
//            val indexOfListForChange = resultLoopInputIndexOfList.input<Int>()
//
//            val resultLoopInputMenu = LoopInput(
//                "Напишите - ${selectNameToChangeFilterSearchTheField}, чтобы изменить имя в базе данных " + "\n" +
//                        "Напишите - ${selectSurnameToChangeFilterSearchTheField}, чтобы изменить фамилию в базе данных " + "\n" +
//                        "Напишите - ${selectPatronymicToChangeFilterSearchTheField}, если изменить отчество в базе данных " + "\n" +
//                        "Напишите - ${selectCostToChangeFilterSearchTheField}, чтобы изменить цену в базе данных " + "\n" +
//                        "Напишите - ${selectDiscountToChangeFilterSearchTheField}, чтобы изменить скидку в базе данных" + "\n" +
//                        "Напишите - ${selectAddressToChangeFilterSearchTheField}, чтобы изменить адрес в базе данных" + "\n",
//                resultInputFieldToChangeOrSearch,
//                resultValidateFieldToChangeOrSearch)
//
//            val fieldOfListForChange = resultLoopInputMenu.input()
//            if (indexOfListForChange != null && fieldOfListForChange != null) {
//                when (fieldOfListForChange) {
//                    selectNameToChangeFilterSearchTheField -> {
//                        val resultLoopInput = LoopInput(
//                            "Введите имя: ",
//                            resultInputNameOrSurnameOfPatronymic,
//                            resultValidateNameSurnamePatronymic)
//                        val resultLoopInputName = resultLoopInput.input()
//
//                        val resultFieldChangeInTheList = FieldChangeInTheList(
//                            indexOfListForChange,
//                            fieldOfListForChange,
//                            resultLoopInputName,
//                            listOfOnlineStoreDataBase)
//                        resultFieldChangeInTheList.change()
//                    }
//
//
//                    selectSurnameToChangeFilterSearchTheField -> {
//                        val resultLoopInput = LoopInput(
//                            "Введите фамилия: ",
//                            resultInputNameOrSurnameOfPatronymic,
//                            resultValidateNameSurnamePatronymic)
//                        val resultLoopInputSurname = resultLoopInput.input()
//
//                        val resultFieldChangeInTheList = FieldChangeInTheList(
//                            indexOfListForChange,
//                            fieldOfListForChange,
//                            resultLoopInputSurname,
//                            listOfOnlineStoreDataBase)
//                        resultFieldChangeInTheList.change()
//                    }
//
//                    selectPatronymicToChangeFilterSearchTheField -> {
//                        val resultLoopInput = LoopInput(
//                            "Введите отчество: ",
//                            resultInputNameOrSurnameOfPatronymic,
//                            resultValidateNameSurnamePatronymic)
//                        val resultLoopInputPatronymic = resultLoopInput.input()
//
//                        val resultFieldChangeInTheList = FieldChangeInTheList(
//                            indexOfListForChange,
//                            fieldOfListForChange,
//                            resultLoopInputPatronymic,
//                            listOfOnlineStoreDataBase)
//                        resultFieldChangeInTheList.change()
//                    }
//
//                    selectCostToChangeFilterSearchTheField -> {
//                        val resultLoopInput = LoopInput(
//                            "Введите цену: ",
//                            resultInputCost,
//                            resultValidateCost)
//                        val resultLoopInputCost = resultLoopInput.input()
//
//                        val resultFieldChangeInTheList = FieldChangeInTheList(
//                            indexOfListForChange,
//                            fieldOfListForChange,
//                            resultLoopInputCost,
//                            listOfOnlineStoreDataBase)
//                        resultFieldChangeInTheList.change()
//                    }
//
//                    selectDiscountToChangeFilterSearchTheField -> {
//                        val resultLoopInput = LoopInput(
//                            "Введите скидку: ",
//                            resultInputDiscount,
//                            resultValidateDiscount)
//                        val resultLoopInputDiscount = resultLoopInput.input()
//
//                        val resultFieldChangeInTheList = FieldChangeInTheList(
//                            indexOfListForChange,
//                            fieldOfListForChange,
//                            resultLoopInputDiscount,
//                            listOfOnlineStoreDataBase)
//                        resultFieldChangeInTheList.change()
//                    }
//
//                    selectAddressToChangeFilterSearchTheField -> {
//                        val resultLoopInput = LoopInput(
//                            "Введите адрес: ",
//                            resultInputAddress,
//                            resultValidateAddress)
//                        val resultLoopInputAddress = resultLoopInput.input()
//
//                        val resultFieldChangeInTheList = FieldChangeInTheList(
//                            indexOfListForChange,
//                            fieldOfListForChange,
//                            resultLoopInputAddress,
//                            listOfOnlineStoreDataBase)
//                        resultFieldChangeInTheList.change()
//                    }
//                }
//            }
//        } else println("Записей нет, чтобы что-то изменить добавьте запись в список")
//    }
//}
//
//class FiledFilter @Inject constructor(
//    private val resultInputFieldToChangeOrSearch: InputFieldToChangeOrSearch,
//    private val resultValidateFieldToChangeOrSearch: ValidateFieldToChangeOrSearch,
//    private val listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>, ) {
//    fun filter() {
//
//        val resultLoopInput = LoopInput(
//            "Напишите - ${selectNameToChangeFilterSearchTheField}, чтобы произвести поиск имени в базе данных " + "\n" +
//                    "Напишите - ${selectSurnameToChangeFilterSearchTheField}, чтобы произвести поиск фамилии в базе данных " + "\n" +
//                    "Напишите - ${selectPatronymicToChangeFilterSearchTheField}, если произвести поиск отчеству в базе данных " + "\n" +
//                    "Напишите - ${selectCostToChangeFilterSearchTheField}, чтобы произвести поиск по цене в базе данных " + "\n" +
//                    "Напишите - ${selectDiscountToChangeFilterSearchTheField}, чтобы произвести поиск скидке в базе данных" + "\n" +
//                    "Напишите - ${selectAddressToChangeFilterSearchTheField}, чтобы произвести поиск адресу в базе данных" + "\n",
//            resultInputFieldToChangeOrSearch,
//            resultValidateFieldToChangeOrSearch)
//
//        val fieldOfListForFilter = resultLoopInput.input()
//
//        if (fieldOfListForFilter != null) {
//            val resultFieldFilterInTheList = FieldFilterInTheList(mapForFilter, fieldOfListForFilter, listOfOnlineStoreDataBase)
//            resultFieldFilterInTheList.filter()
//        }
//    }
//}
//
//open class FiledSearch @Inject constructor(
//    private val InputFieldToChangeOrSearch: InputFieldToChangeOrSearch,
//    private val resultValidateFieldToChangeOrSearch: ValidateFieldToChangeOrSearch,
//    private val resultInputNameOrSurnameOfPatronymic: InputNameOrSurnameOfPatronymic,
//    private val resultValidateNameSurnamePatronymic: ValidateNameSurnamePatronymic,
//    private val resultInputCost: InputCost,
//    private val resultValidateCost: ValidateCost,
//    private val resultInputDiscount: InputDiscount,
//    private val resultValidateDiscount: ValidateDiscount,
//    private val resultInputAddress: InputAddress,
//    private val resultValidateAddress: ValidateAddress,
//    private val listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>) {
//    fun search() {
//
//        val resultLoopInput = LoopInput(
//            "Напишите - ${selectNameToChangeFilterSearchTheField}, чтобы произвести поиск имени в базе данных " + "\n" +
//                    "Напишите - ${selectSurnameToChangeFilterSearchTheField}, чтобы произвести поиск фамилии в базе данных " + "\n" +
//                    "Напишите - ${selectPatronymicToChangeFilterSearchTheField}, если произвести поиск отчеству в базе данных " + "\n" +
//                    "Напишите - ${selectCostToChangeFilterSearchTheField}, чтобы произвести поиск по цене в базе данных " + "\n" +
//                    "Напишите - ${selectDiscountToChangeFilterSearchTheField}, чтобы произвести поиск скидке в базе данных" + "\n" +
//                    "Напишите - ${selectAddressToChangeFilterSearchTheField}, чтобы произвести поиск адресу в базе данных" + "\n",
//            InputFieldToChangeOrSearch,
//            resultValidateFieldToChangeOrSearch
//        )
//
//        when (resultLoopInput.input()) {
//            selectNameToChangeFilterSearchTheField -> {
//
//                val resultInput = LoopInput(
//                    "Введите имя: ",
//                    resultInputNameOrSurnameOfPatronymic,
//                    resultValidateNameSurnamePatronymic
//                )
//
//                val resultInputName = resultInput.input()
//
//                val resultFieldSearchInTheList = FieldSearchInTheList(
//                    mapForSearch,
//                    resultInputName.toString(),
//                    selectNameToChangeFilterSearchTheField,
//                    listOfOnlineStoreDataBase)
//                resultFieldSearchInTheList.search()
//            }
//            selectSurnameToChangeFilterSearchTheField -> {
//                val resultInput = LoopInput(
//                    "Введите фамилию: ",
//                    resultInputNameOrSurnameOfPatronymic,
//                    resultValidateNameSurnamePatronymic
//                )
//
//                val resultInputName = resultInput.input()
//
//                val resultFieldSearchInTheList = FieldSearchInTheList(
//                    mapForSearch,
//                    resultInputName.toString(),
//                    selectNameToChangeFilterSearchTheField,
//                    listOfOnlineStoreDataBase)
//                resultFieldSearchInTheList.search()
//            }
//            selectPatronymicToChangeFilterSearchTheField -> {
//                val resultInput = LoopInput(
//                    "Введите отчество: ",
//                    resultInputNameOrSurnameOfPatronymic,
//                    resultValidateNameSurnamePatronymic
//                )
//
//                val resultInputName = resultInput.input()
//
//                val resultFieldSearchInTheList = FieldSearchInTheList(
//                    mapForSearch,
//                    resultInputName.toString(),
//                    selectNameToChangeFilterSearchTheField,
//                    listOfOnlineStoreDataBase)
//                resultFieldSearchInTheList.search()
//            }
//            selectCostToChangeFilterSearchTheField -> {
//                val resultInput = LoopInput(
//                    "Введите цену: ",
//                    resultInputCost,
//                    resultValidateCost
//                )
//
//                val resultInputName = resultInput.input()
//
//                val resultFieldSearchInTheList = FieldSearchInTheList(
//                    mapForSearch,
//                    resultInputName.toString(),
//                    selectNameToChangeFilterSearchTheField,
//                    listOfOnlineStoreDataBase)
//                resultFieldSearchInTheList.search()
//            }
//            selectDiscountToChangeFilterSearchTheField -> {
//
//                val resultInput = LoopInput(
//                    "Введите скидку: ",
//                    resultInputDiscount,
//                    resultValidateDiscount
//                )
//
//                val resultInputName = resultInput.input()
//
//                val resultFieldSearchInTheList = FieldSearchInTheList(
//                    mapForSearch,
//                    resultInputName.toString(),
//                    selectNameToChangeFilterSearchTheField,
//                    listOfOnlineStoreDataBase)
//                resultFieldSearchInTheList.search()
//            }
//            selectAddressToChangeFilterSearchTheField -> {
//
//                val resultInput = LoopInput(
//                    "Введите адрес: ",
//                    resultInputAddress,
//                    resultValidateAddress
//                )
//
//                val resultInputName = resultInput.input()
//
//                val resultFieldSearchInTheList = FieldSearchInTheList(
//                    mapForSearch,
//                    resultInputName.toString(),
//                    selectNameToChangeFilterSearchTheField,
//                    listOfOnlineStoreDataBase)
//                resultFieldSearchInTheList.search()
//            }
//        }
//    }
//}
//
//fun main() {
//
//    val resultInputNameOrSurnameOfPatronymic = InputNameOrSurnameOfPatronymic()
//    val resultValidateNameSurnamePatronymic = ValidateNameSurnamePatronymic()
//    val resultInputCost = InputCost()
//    val resultValidateCost = ValidateCost()
//    val resultInputDiscount = InputDiscount()
//    val resultValidateDiscount = ValidateDiscount()
//    val resultInputAddress = InputAddress()
//    val resultValidateAddress = ValidateAddress()
//    val listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase> = arrayListOf()
//
//    val resultValidateSizeOfList = ValidateSizeOfList()
//
//
//    val resultInputIndexOfList = InputIndexOfList()
//    val resultValidateIndexOfList = ValidateIndexOfList()
//
//
//    val resultInputFieldToChangeOrSearch = InputFieldToChangeOrSearch()
//    val resultValidateFieldToChangeOrSearch = ValidateFieldToChangeOrSearch()
//
//    val resultFieldAdd = FieldAdd(
//        resultInputNameOrSurnameOfPatronymic,
//        resultValidateNameSurnamePatronymic,
//        resultInputCost,
//        resultValidateCost,
//        resultInputDiscount,
//        resultValidateDiscount,
//        resultInputAddress,
//        resultValidateAddress,
//        listOfOnlineStoreDataBase)
//
//    val resultPrintAllInformation = PrintAllInformation(listOfOnlineStoreDataBase)
//    val resultLoopInputIndexOfList = InputIndexOfList()
//    val resultFieldDelete = FiledDelete(
//        resultValidateSizeOfList,
//        listOfOnlineStoreDataBase,
//        resultLoopInputIndexOfList,
//        resultValidateIndexOfList)
//
//    val resultFieldChange = FiledChange(resultValidateSizeOfList,
//        listOfOnlineStoreDataBase,
//        resultInputNameOrSurnameOfPatronymic,
//        resultValidateNameSurnamePatronymic,
//        resultInputCost,
//        resultValidateCost,
//        resultInputDiscount,
//        resultValidateDiscount,
//        resultInputAddress,
//        resultValidateAddress,
//        resultInputIndexOfList,
//        resultValidateIndexOfList,
//        resultInputFieldToChangeOrSearch,
//        resultValidateFieldToChangeOrSearch)
//
//    val resultFieldFilter = FiledFilter(
//        resultInputFieldToChangeOrSearch,
//        resultValidateFieldToChangeOrSearch,
//        listOfOnlineStoreDataBase)
//
//    val resultFieldSearch = FiledSearch(
//        resultInputFieldToChangeOrSearch,
//        resultValidateFieldToChangeOrSearch,
//        resultInputNameOrSurnameOfPatronymic,
//        resultValidateNameSurnamePatronymic,
//        resultInputCost,
//        resultValidateCost,
//        resultInputDiscount,
//        resultValidateDiscount,
//        resultInputAddress,
//        resultValidateAddress,
//        listOfOnlineStoreDataBase)
//
//    val mapMenu = listOf(
//        Triple(valueToAdd, "Если хотите добавить запись") { resultFieldAdd.add()  },
//        Triple(valueToOutputOfRecords, "Если хотите вывести все записи") { resultPrintAllInformation.print() },
//        Triple(valueToDeleteOfRecords, "Если хотите удалить запись") {resultFieldDelete.delete() },
//        Triple(valueToChangeOfRecords, "Если хотите изменить запись") { resultFieldChange.change() },
//        Triple(valueToFilterOfRecords, "Если хотите отфильтровать запись") { resultFieldFilter.filter() },
//        Triple(valueToSearchOfRecords, "Если хотите произвести поиск записи") {resultFieldSearch.search() }
//    )
//    do {
//        for (i in mapMenu)
//            println(i.first.toString() + " " + i.second)
//
//        val resultOfInputOfferToEnterActionNumber = LoopInput(
//            "Введите цифру из списка, чтобы сделать какое-то дейтсвие: ",
//            InputActionNumber(),
//            ValidateActionNumber()
//        ).input()
//
//        mapMenu[resultOfInputOfferToEnterActionNumber!! - 1].third()
//    } while (true)
//}