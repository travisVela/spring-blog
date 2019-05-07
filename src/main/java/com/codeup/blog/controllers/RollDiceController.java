package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.ArrayList;
import java.util.List;

@Controller
public class RollDiceController {

    @GetMapping("/rolldice")
    public String showRollDice() {
        return "rollDice";
    }


    @GetMapping("rolldice/{number}")
    public String getDiceRoll(Model model, @PathVariable Integer number) {
        int randomNumber = randomNumber();
        String notMatch = "Close, but no cigar";
        String match = "You guessed it!";
        String randomString = "The roll was: " + randomNumber;
        String response = null;


        if (randomNumber == number) {
            response = match;
        } else if (randomNumber != number) {
            response = notMatch;
        }
        model.addAttribute("getDiceRoll", true);
        model.addAttribute("response", response);
        model.addAttribute("randomNumber", randomString);
        return "rollDice";
    }

    private int randomNumber() {
        return (int) (Math.random() * 6) + 1;
    }

}
