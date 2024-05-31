package org.example.jpa_demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.jpa_demo.entity.Threads;
import org.example.jpa_demo.event.ThreadsEventPublisher;
import org.example.jpa_demo.service.ThreadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
public class ThreadsController {

    private static final Log logger = LogFactory.getLog(ThreadsController.class);

    private final ThreadsService threadsService;
    private final ThreadsEventPublisher threadsEventPublisher;

    @Autowired
    public ThreadsController(ThreadsService threadsService, ThreadsEventPublisher threadsEventPublisher) {
        this.threadsService = threadsService;
        this.threadsEventPublisher = threadsEventPublisher;
    }


    @GetMapping("/threads/{ticketId}")
    public ResponseEntity<List<Threads>> getThreadsByTicketId(@PathVariable String ticketId) {
        logger.info("getTicketList start");
        List<Threads> threadsList = threadsService.getThreadsByTicketID(ticketId);
        return new ResponseEntity<>(threadsList, HttpStatus.OK);
    }

    @PostMapping("/addThreads")
    public String addTicket(MultipartHttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        MultipartFile file = request.getFile("mediaFiles");

        int ticketID = Integer.parseInt(parameterMap.get("ticketID")[0]);
        int userID = Integer.parseInt(parameterMap.get("userID")[0]);
        String ticketType = (String) parameterMap.get("ticketType")[0];
        String message = (String) parameterMap.get("message")[0];
        /*
        int ticketID = Integer.parseInt(requestBody.get("ticketID").toString());
        int userID = Integer.parseInt(requestBody.get("userID").toString());

        String ticketType = (String) requestBody.get("ticketType");
        String message = (String) requestBody.get("message");*/
        // Process the ticket data and media files
        // Save the ticket details and media files to a database or storage
        // ticketService.createTicket(ticketType,subject,ticketDetails);
        String filePath = "";

        if (file != null) {
            // Process and save the attached media file
            logger.info("add file" + file.getOriginalFilename());
            filePath = (saveFile(file));
        }

        logger.info("ticketID = " + ticketID);
        logger.info("userID = " + userID);
        logger.info("ticketType = " + ticketType);
        logger.info("message = " + message);

        Threads threads = threadsService.createThreads(ticketID, userID, ticketType, message,filePath);
        threadsEventPublisher.publishThreadsCreatedEvent(threads);
        // Return a response indicating success or failure
        logger.info("new threads" + threads);
        return "threads added successfully";
    }

    private String saveFile(MultipartFile file) {

            String fileName = file.getOriginalFilename();
            try {
                byte[] bytes = file.getBytes();
                // Example: save to the server
                //String serverPath = "/path/to/linux/server/directory/" + fileName;
                // write the bytes to the server path

                // If running on Windows, the file path should be modified as below:
                String serverPath = "D:\\project\\jpa_demo\\src\\main\\resources\\static\\asset\\upload\\" + fileName;
                // write the bytes to the server path
                logger.info("Save File to " + serverPath);

                Path path = Paths.get(serverPath);
                Files.write(path, bytes);

                return "http://192.168.70.137:8080/asset/upload/"+fileName;

            } catch (IOException e) {
                throw new RuntimeException(e);

        }
    }


}