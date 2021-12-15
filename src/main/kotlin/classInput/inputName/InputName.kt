package classInput.inputName
import interfaceInput.IInput
import javax.inject.Inject

open class InputName @Inject constructor() : IInput<String?> {
    override fun input() : String? = readLine()
}