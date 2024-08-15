package edu.avada.course.controller;

import edu.avada.course.model.admindto.AdminBidDto;
import edu.avada.course.model.entity.Bid.BidStatus;
import edu.avada.course.service.AdminBidService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bid")
public class BidController {
    private AdminBidService adminBidService;

    public BidController(
            @Autowired AdminBidService adminBidService
    ) {
        this.adminBidService = adminBidService;
    }

    @GetMapping
    public String bid(Model model) {
        model.addAttribute("bid", new AdminBidDto());
        return "client/bid";
    }

    @PostMapping("/add-new")
    public String addNewBid(@ModelAttribute("bid") AdminBidDto adminBidDto) {
        adminBidDto.setStatus(BidStatus.NEW);
        adminBidDto.setDate(LocalDate.now());
        adminBidService.add(adminBidDto);
        return "redirect:/bid";
    }
}
