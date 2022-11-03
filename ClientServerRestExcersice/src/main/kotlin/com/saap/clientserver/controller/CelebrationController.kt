package com.saap.clientserver.controller

import com.saap.clientserver.model.DrinkAmount
import com.saap.clientserver.service.CalculatorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/celebrations")
class CelebrationController(private val calculatorService: CalculatorService) {

    @GetMapping("/celebration/calculate")
    fun getCalculationForCelebration(amountMen: Int, amountWomen: Int): ResponseEntity<List<DrinkAmount>> {
        return ResponseEntity(calculatorService.calculateDrinks(amountMen, amountWomen), HttpStatus.OK)
    }
}
