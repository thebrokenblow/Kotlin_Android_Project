package classInput.inputCost

import interfaceInput.IInput
import javax.inject.Inject

open class InputCost @Inject constructor() : IInput<Double?> {
    override fun input() : Double? = readLine()?.toDoubleOrNull()
}