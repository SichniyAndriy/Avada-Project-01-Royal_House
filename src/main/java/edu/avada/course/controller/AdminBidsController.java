package edu.avada.course.controller;

import edu.avada.course.model.admindto.AdminBidDto;
import edu.avada.course.model.entity.Bid.BidStatus;
import edu.avada.course.service.AdminBidService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String viewBids(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            Model model) {
        Page<AdminBidDto> bidPage = adminBidService.getBidPage(page, size);
        model.addAttribute("bidPage", bidPage);
        return "admin/bids";
    }

    @GetMapping("/show/{id}")
    public String viewBid(@PathVariable Long id, Model model) {
        AdminBidDto bid = adminBidService.getBidById(id);
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

    @PostMapping("/add-new")
    public String addNewBid(
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("comment") String comment,
            @RequestParam("date") LocalDate date
    ) {
        AdminBidDto newAdminBidDto = new AdminBidDto();
        newAdminBidDto.setName(name);
        newAdminBidDto.setPhone(phone);
        newAdminBidDto.setEmail(email);
        newAdminBidDto.setComment(comment);
        newAdminBidDto.setDate(date);
        newAdminBidDto.setStatus(BidStatus.NEW);
        adminBidService.add(newAdminBidDto);
        return "redirect:/admin/bids";
    }
}
