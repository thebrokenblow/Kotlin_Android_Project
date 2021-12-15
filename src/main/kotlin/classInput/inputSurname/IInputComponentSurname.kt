package classInput.inputSurname

import classInput.LoopInput
import dagger.Component

@Component(modules = [ISurnameModule::class])
interface IInputComponentSurname {
    fun loopInput() : LoopInput<String?>
}