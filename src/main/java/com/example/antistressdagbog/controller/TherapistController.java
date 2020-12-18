package com.example.antistressdagbog.controller;

import com.example.antistressdagbog.dto.DiaryEntryCSVDto;
import com.example.antistressdagbog.model.Account;
import com.example.antistressdagbog.model.DiaryEntry;
import com.example.antistressdagbog.model.UserCredentials;
import com.example.antistressdagbog.repository.AccountRepository;
import com.example.antistressdagbog.repository.UserRepository;
import com.example.antistressdagbog.service.DiaryEntryService;
import com.example.antistressdagbog.utility.DiaryCSVMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
public class TherapistController {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @GetMapping("/therapist/clients")
    public String getClients(Model model, HttpServletRequest request){
        Long account_id = userRepository.findByUsername(request.getRemoteUser()).getAccount().getId();

        List<Account> accounts = accountRepository.findAllByTherapist(account_id);
        List<UserCredentials> clients = new ArrayList<>();
        for(Account acount : accounts){
            UserCredentials client = userRepository.findByAccount(acount);
            clients.add(client);
        }
        model.addAttribute("clients", clients);
        return "therapist/clients";
    }

}
