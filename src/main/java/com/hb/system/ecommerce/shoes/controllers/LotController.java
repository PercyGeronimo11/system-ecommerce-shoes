package com.hb.system.ecommerce.shoes.controllers;

import com.hb.system.ecommerce.shoes.entity.Lot;
import com.hb.system.ecommerce.shoes.repositories.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lot")
public class LotController {

    @Autowired
    private LotRepository lotsRepository;

    @GetMapping({"/index","/","list"})
    public String index(Model model){
        List<Lot> lotsList=lotsRepository.findAll();
        model.addAttribute("lots", lotsList);
        model.addAttribute("contenido", "lots/list");
        return "layout/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("lots",new Lot());
        model.addAttribute("contenido", "lots/create");
        return "layout/index";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Lot lots){
        lotsRepository.save(lots);
        return "redirect:/lot/index";
    }

    @GetMapping("/edit/{id}")
    public String editLot(Model model,@PathVariable int id){
        Optional<Lot> lotsFind=lotsRepository.findById(id);
        if(lotsFind.isEmpty()){
            return "redirect:/lot/index";
        }else{
            model.addAttribute("lots",lotsFind.get());
            model.addAttribute("contenido", "lots/edit");
            return "layout/index";
        }
    }

    @PostMapping("/update")
    public String updateLot(@ModelAttribute Lot lots){
        Optional<Lot> lotsFind=lotsRepository.findById(lots.getId());
        if (lotsFind.isPresent()){
            //lotsFind.get().setCategory(lots.getCategory());
            lotsFind.get().setPro_name(lots.getPro_name());
            lotsFind.get().setPro_description(lots.getPro_description());
            lotsFind.get().setPro_unit_price(lots.getPro_unit_price());
            lotsRepository.save(lotsFind.get());
        }else{

        }
        return "redirect:/lot/index";
    }

    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/lot/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        lotsRepository.deleteById(id);
        return "redirect:/lot/index";
    }
}


