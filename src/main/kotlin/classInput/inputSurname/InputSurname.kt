package classInput.inputSurname

import interfaceInput.IInput
import javax.inject.Inject

open class InputSurname @Inject constructor() : IInput<String?> {
    override fun input() : String? = readLine()
}