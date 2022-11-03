package com.saap.clientserver.service

import com.saap.clientserver.model.Drink
import com.saap.clientserver.model.DrinkAmount
import com.saap.clientserver.model.Gender
import org.springframework.stereotype.Service

@Service
class CalculatorService() {
    companion object {
        val drinks = mutableListOf<Drink>()
    }
    fun calculateDrinks(amountMen: Int, amountWomen: Int): List<DrinkAmount> {
        val drinksAmountList = mutableListOf<DrinkAmount>()
        val amountPerMan = 10
        val drinksMen = drinks.filter { it.drinkingGender == Gender.MEN }
        drinksMen.forEach {
            drinksAmountList.add(DrinkAmount(it, amountPerMan * amountMen))
        }
        val amountPerWoman = 4
        val drinksWomen = drinks.filter { it.drinkingGender == Gender.WOMEN }
        drinksWomen.forEach {
            drinksAmountList.add(DrinkAmount(it, amountPerWoman * amountWomen))
        }
        return drinksAmountList
    }
}
