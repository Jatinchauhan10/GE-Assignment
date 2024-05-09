package com.application.MultiTenant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class FileListenerService {

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private DeviceService deviceService;

    @KafkaListener(topics = "upload-topic", groupId = "tenant-file-group")
    public void listenForFileUpload(String message) {
        String[] parts = message.split(",");
        String tenantId = parts[0];
        String bucketName = parts[1];
        String fileName = parts[2];

        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(s3Object.getObjectContent()))) {
            deviceService.processFile(reader, tenantId);
            s3Client.deleteObject(bucketName, fileName);
        } catch (Exception e) {
            System.err.println("Error processing file: " + e.getMessage());
            // Add alert mechanism here, e.g., send email or log to a monitoring system
        }
    }
}


