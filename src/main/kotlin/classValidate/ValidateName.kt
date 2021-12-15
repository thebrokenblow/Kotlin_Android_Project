package classValidate

import interfaceValidate.IValidate
import javax.inject.Inject

open class ValidateName @Inject constructor(): IValidate<String?> {
    override fun validate(value : String?): Boolean = value != "" && Regex("([A-Za-zА-Яа-яёЁ]+)(([-]([A-Za-zА-Яа-яёЁ]+))?)*").matches(value.toString())
}
