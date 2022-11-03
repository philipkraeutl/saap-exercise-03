package com.saap.clientserver.controller

import com.saap.clientserver.model.Drink
import com.saap.clientserver.service.CalculatorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/drinks")
class DrinkController {
    @PostMapping("/drink")
    fun insertDrink(@RequestBody drink: Drink): ResponseEntity<List<Drink>> {
        CalculatorService.drinks.add(drink)
        return ResponseEntity(CalculatorService.drinks, HttpStatus.OK)
    }

    @PutMapping("/drink/byName")
    fun updateDrinkName(oldName: String, newName: String): ResponseEntity<Drink> {
        CalculatorService.drinks.let { drinks ->
            val drink = drinks.find { it.name.equals(oldName, true) } ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST)
            val index = drinks.indexOf(drink)
            drinks.remove(drink)
            val newDrink = drink.copy(name = newName)
            drinks.add(index, newDrink)
            return ResponseEntity(newDrink, HttpStatus.OK)
        }
    }

    @DeleteMapping("/drink/deleteLast")
    fun deleteLastDrink(): ResponseEntity<Drink> {
        CalculatorService.drinks.apply {
            val delete = this.removeLastOrNull() ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST)
            return ResponseEntity(delete, HttpStatus.OK)
        }
    }
}
