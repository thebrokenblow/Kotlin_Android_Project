package classInput.inputDiscount

import interfaceInput.IInput
import javax.inject.Inject

open class InputDiscount @Inject constructor() : IInput<Int?> {
    override fun input() : Int? = readLine()?.toIntOrNull()
}