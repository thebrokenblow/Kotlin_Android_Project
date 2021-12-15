package interfaceValidate

interface IValidate<T> {
    fun validate(value: T?) : Boolean
}