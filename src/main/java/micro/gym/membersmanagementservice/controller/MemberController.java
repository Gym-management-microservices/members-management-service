package micro.gym.membersmanagementservice.controller;

import micro.gym.membersmanagementservice.model.Member;
import micro.gym.membersmanagementservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/registration")
    @PreAuthorize("hasRole('ADMIN')")
    public Member addMember(@RequestBody Member member) {
        return memberService.registerMember(member);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'TRAINER')")
    public Iterable<Member> getAllMembers() {
        return memberService.findAll();
    }

}
