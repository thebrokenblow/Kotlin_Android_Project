package add

import classInput.inputAddress.DaggerIInputComponentAddress
import classInput.inputCost.DaggerIInputComponentCost
import classInput.inputDiscount.DaggerIInputComponentDiscount
import classInput.inputName.DaggerIInputComponentName
import classInput.inputPatronymic.DaggerIInputComponentPatronymic
import classInput.inputSurname.DaggerIInputComponentSurname
import model.OnlineStoreDataBase


open class Add(private val listOfOnlineStoreDataBase : ArrayList<OnlineStoreDataBase>) {
    fun add() {
        val name = DaggerIInputComponentName.builder().build().loopInput().input("Введите имя: ")
        val surname = DaggerIInputComponentSurname.builder().build().loopInput().input("Введите фамилию: ")
        val patronymic = DaggerIInputComponentPatronymic.builder().build().loopInput().input("Введите отчество: ")
        val cost = DaggerIInputComponentCost.builder().build().loopInput().input("Введите цену: ")
        val discount = DaggerIInputComponentDiscount.builder().build().loopInput().input("Введите скидку: ")
        val address = DaggerIInputComponentAddress.builder().build().loopInput().input("Введите адрес: ")
        listOfOnlineStoreDataBase.add(OnlineStoreDataBase(name, surname, patronymic, cost, discount, address))
    }
}