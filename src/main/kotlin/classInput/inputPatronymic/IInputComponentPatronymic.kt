package classInput.inputPatronymic

import classInput.LoopInput
import dagger.Component

@Component(modules = [IPatronymicModule::class])
interface IInputComponentPatronymic {
    fun loopInput() : LoopInput<String?>
}