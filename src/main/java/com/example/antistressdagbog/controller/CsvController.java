package com.example.antistressdagbog.controller;

import com.example.antistressdagbog.dto.DiaryEntryCSVDto;
import com.example.antistressdagbog.model.DiaryEntry;
import com.example.antistressdagbog.model.UserCredentials;
import com.example.antistressdagbog.repository.DiaryEntryRepository;
import com.example.antistressdagbog.repository.UserRepository;
import com.example.antistressdagbog.service.DiaryEntryService;
import com.example.antistressdagbog.utility.DiaryCSVMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@Controller
public class CsvController {

    private final DiaryEntryService diaryService;

    private final UserRepository userRepository;

    private final DiaryCSVMapper diaryCSVMapper;

    @GetMapping("/therapist/exportdata/{thisClient}")
    public void exportUserDiaryToCSV(HttpServletResponse response, @PathVariable String thisClient) throws IOException {
        response.setContentType("text/csv");

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        String currentDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(now);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        UserCredentials user = userRepository.findById(thisClient).get();
        List<DiaryEntry> usersDiary = diaryService.listAllByAccount(user.getAccount());

        List<DiaryEntryCSVDto> userDiaryCSV = diaryCSVMapper.toDiaryEntryCSVDtos(user.getUsername(), usersDiary);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.EXCEL_PREFERENCE);
        String[] csvHeader = {"Client_id", "Date", "Week", "Day of week", "Morning", "Midday","Evening", "Sleep", "Note_Morning", "Note_Midday", "Note_Evening", "Note_Sleep"};
        String[] nameMapping = {"user", "date", "week", "dayOfWeek", "morning", "afternoon", "evening", "sleep", "note1", "note2", "note3", "noteSleep"};

        csvWriter.writeHeader(csvHeader);

        for (DiaryEntryCSVDto entry: userDiaryCSV) {
            csvWriter.write(entry, nameMapping);
        }
        csvWriter.close();
    }

}
