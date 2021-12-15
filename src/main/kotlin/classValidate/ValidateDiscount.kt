package classValidate

import interfaceValidate.IValidate
import javax.inject.Inject

open class ValidateDiscount @Inject constructor(): IValidate<Int?> {
    override fun validate(value : Int?): Boolean = value != null && value in 0..100
}