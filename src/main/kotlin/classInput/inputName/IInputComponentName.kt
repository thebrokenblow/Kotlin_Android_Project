package classInput.inputName

import classInput.LoopInput
import dagger.Component

@Component(modules = [INameModule::class])
interface IInputComponentName {
    fun loopInput() : LoopInput<String?>
}