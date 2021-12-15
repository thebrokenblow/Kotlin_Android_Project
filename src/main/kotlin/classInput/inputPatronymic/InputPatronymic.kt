package classInput.inputPatronymic

import interfaceInput.IInput
import javax.inject.Inject

open class InputPatronymic @Inject constructor() : IInput<String?> {
    override fun input() : String? = readLine()
}