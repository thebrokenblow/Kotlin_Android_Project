package classInput.loopInputIndexOfList


import classValidate.ValidateIndexOfList
import interfaceInput.IInput
import model.OnlineStoreDataBase
import javax.inject.Inject

open class LoopInputIndexOfList @Inject constructor(
    private val valueInput: IInput<Int?>,
    private val valueValidate: ValidateIndexOfList
) {
    fun input(help: String, listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>): Int? {
        var resultValueInput: Int?
        do {
            print(help)
            resultValueInput = valueInput.input()
            val flagFunction: Boolean = valueValidate.validate(resultValueInput, listOfOnlineStoreDataBase)
        } while (!flagFunction)
        return resultValueInput
    }
}