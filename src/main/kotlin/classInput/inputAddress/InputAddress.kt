package classInput.inputAddress

import interfaceInput.IInput
import javax.inject.Inject

open class InputAddress @Inject constructor() : IInput<String?> {
    override fun input() : String? = readLine()
}