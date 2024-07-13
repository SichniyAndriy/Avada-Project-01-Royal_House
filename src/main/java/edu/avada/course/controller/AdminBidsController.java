package edu.avada.course.controller;

import edu.avada.course.model.entity.Bid;
import edu.avada.course.service.AdminBidService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/bids")
public class AdminBidsController {
    private final AdminBidService adminBidService;

    public AdminBidsController(
            @Autowired AdminBidService adminBidService
    ) {
        this.adminBidService = adminBidService;
    }

    @GetMapping
    public String viewBids(Model model) {
        Set<Bid> allBids = adminBidService.getAllBids();
        model.addAttribute("bids", allBids);
        return "admin/bids";
    }

    @GetMapping("/show/{id}")
    public String viewBid(@PathVariable Long id, Model model) {
        Bid bid = adminBidService.getBidById(id);
        model.addAttribute("bid", bid);
        return "admin/bid_card";
    }

    @GetMapping("/delete/{id}")
    public String deleteBid(@PathVariable long id) {
        adminBidService.deleteBidById(id);
        return "forward:/admin/bids";
    }

    @GetMapping("/change-bid-status/{id}")
    public String changeBidStatus(@PathVariable long id) {
        adminBidService.changeBidStatusById(id);
        return "forward:/admin/bids";
    }
}