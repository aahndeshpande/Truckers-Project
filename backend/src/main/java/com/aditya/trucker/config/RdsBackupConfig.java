package com.aditya.trucker.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class RdsBackupConfig {

    private final RdsProperties rdsProperties;

    @Bean
    public AmazonS3 s3Client() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(rdsProperties.getAws().getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(
                                rdsProperties.getAws().getAccessKey(),
                                rdsProperties.getAws().getSecretKey()
                        )
                ))
                .build();
    }

    @Scheduled(cron = "${rds.backup.schedule}")
    public void scheduleBackup() {
        if (rdsProperties.getBackup().isEnabled()) {
            // Implement backup logic here
            // This would typically involve:
            // 1. Creating a snapshot of the RDS instance
            // 2. Uploading backup files to S3
            // 3. Cleaning up old backups based on retention policy
            System.out.println("Scheduled backup triggered at " + new java.util.Date());
        }
    }
}
