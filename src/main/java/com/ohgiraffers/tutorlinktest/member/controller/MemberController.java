package com.ohgiraffers.tutorlinktest.member.controller;

import com.ohgiraffers.tutorlinktest.member.dto.MemberDTO;
import com.ohgiraffers.tutorlinktest.member.service.MemberService;
import com.ohgiraffers.tutorlinktest.valid.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final MessageSourceAccessor messageSourceAccessor;
    public MemberController(MemberService memberService, MessageSourceAccessor messageSourceAccessor) {
        this.memberService = memberService;
        this.messageSourceAccessor = messageSourceAccessor;
    }
    @GetMapping("/login")
    public void loginPage() {}

    @GetMapping("/join")
    public void joinPage(){ }

    @PostMapping("/join")
    public String joinPage(MemberDTO memberInfo, RedirectAttributes rttr) {
        memberService.joinMember(memberInfo);
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.join"));
        return "redirect:/";
    }
    @GetMapping("/mypage")
    public void mypage(@AuthenticationPrincipal MemberDTO member) {
        log.info("로그인 member 번호 : {}", member.getMemberNo());
        log.info("로그인 member 아이디 : {}", member.getMemberId());
        log.info("로그인 member 이름 : {}", member.getMemberName());
    }
    @GetMapping("/tutee")
    public String findAllTutee(@PageableDefault Pageable pageable, Model model){
       return "/member/tutee";
    }

    @GetMapping("/tutor")
    public String findAllTutor(@PageableDefault Pageable pageable, Model model){
        return "/member/tutor";
    }
    @GetMapping("/member/{memberNo}")
    public ResponseEntity<?> findUserByNo() throws UserNotFoundException {
        boolean check = true;
        if(check) {
            throw new UserNotFoundException("회원 정보를 찾을 수 없습니다.");
        }
        return ResponseEntity.ok().build();
    }

}
