package com.dinhson.sunshop.appAdmin.sizeManagement;

import com.dinhson.sunshop.appProduct.sizes.Size;
import com.dinhson.sunshop.appProduct.sizes.SizeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("admin/sizes")
public class SizeManagementController {

    private final SizeService sizeService;

    @GetMapping
    public String getSizes(@RequestParam(required = false) String search,
                           Model model){
        Iterable<Size> sizes = sizeService.findAllSize();
        model.addAttribute("sizes", sizes);
        model.addAttribute("search", search);
        return "admin-sizes-management";
    }

    @PostMapping
    public String addSize(@RequestParam String size,
                          @RequestParam String height,
                          @RequestParam String weight,
                          RedirectAttributes redirectAttributes){
        String message = sizeService.createNewSize(size, height, weight);

        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/admin/sizes";
    }

    @PostMapping("update")
    public String updateSize(@RequestParam Integer id,
                          @RequestParam String size,
                          @RequestParam String height,
                          @RequestParam String weight,
                          RedirectAttributes redirectAttributes){
        String message = sizeService.updateSize(id, size, height, weight);

        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/admin/sizes";
    }
}
