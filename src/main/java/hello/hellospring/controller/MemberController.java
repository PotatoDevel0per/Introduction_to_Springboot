package hello.hellospring.controller;

//import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;
import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import service.MemberService;

import java.util.List;

@Controller
public class MemberController {

    //스프링컨테이너에 하나만 등록시키자
    private final MemberService memberService;

    //커맨드 + N -> Constructor 생성자 자동 생성
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //이렇게 했을 때 에러 발생

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMember();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
