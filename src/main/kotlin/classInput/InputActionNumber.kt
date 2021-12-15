package classInput

import interfaceInput.IInput
import javax.inject.Inject

open class InputActionNumber @Inject constructor() : IInput<Int?> {
    override fun input(): Int? = readLine()?.toIntOrNull()
}