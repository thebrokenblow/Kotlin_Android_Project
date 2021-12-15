package classInput.inputAddress

import classInput.LoopInput
import dagger.Component

@Component(modules = [IAddressModule::class])
interface IInputComponentAddress {
    fun loopInput() : LoopInput<String?>
}