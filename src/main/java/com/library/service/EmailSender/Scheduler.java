package com.library.service.EmailSender;

import com.library.repository.BookRepository;
import com.library.repository.ReaderRepository;
import com.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    private static final String SUBJECT = "Status of library books";

    //@Scheduled(cron = "0 0 10 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void sendEmail() {
        long booksCount = bookRepository.count();
        long usersCount = readerRepository.count();
        emailService.send(
                new Mail(
                        "dariuszkodilla@gmail.com",
                        SUBJECT,
                        "Curently there are " + booksCount + " books in library and " + usersCount +
                                 " users!",
                        null
                )
        );

    }
}
