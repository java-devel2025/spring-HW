package com.example.hogwarts.controller;
import com.example.hogwarts.model.Avatar;
import com.example.hogwarts.service.AvatarService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/avatar")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping
    public Page<Avatar> getAllAvatars(@RequestParam int page,
                                      @RequestParam int size) {
        return avatarService.getAllAvatars(page, size);
    }
}