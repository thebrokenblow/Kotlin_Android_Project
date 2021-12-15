package classInput.inputIndexOfList

import interfaceInput.IInput
import javax.inject.Inject

open class InputIndexOfList @Inject constructor() : IInput<Int?> {
    override fun input() : Int? = readLine()?.toIntOrNull()
}