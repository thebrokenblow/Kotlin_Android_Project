package classInput

import interfaceInput.IInput
import interfaceValidate.IValidate
import javax.inject.Inject

open class LoopInput<T> @Inject constructor(private val input : IInput<T>, private val validate : IValidate<T>) {
    fun input(help: String) : T? {
        var string : T?
        do {
            print(help)
            string = input.input()
        } while (string != null && !validate.validate(string))
        return string
    }
}