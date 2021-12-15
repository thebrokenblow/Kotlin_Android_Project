package classValidate

import interfaceValidate.IValidate
import javax.inject.Inject

open class ValidateAddress @Inject constructor(): IValidate<String?> {
    override fun validate(value : String?): Boolean = value != ""
}