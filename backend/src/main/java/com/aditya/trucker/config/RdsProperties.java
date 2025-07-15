package com.aditya.trucker.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rds")
public class RdsProperties {
    private String url;
    private String username;
    private String password;
    
    private Aws aws;
    private Instance instance;
    private Backup backup;
    private Encryption encryption;
    private Monitoring monitoring;
    private Failover failover;
    private Proxy proxy;

    @Data
    public static class Aws {
        private String accessKey;
        private String secretKey;
        private String region;
    }

    @Data
    public static class Instance {
        private String type;  // db.t2.micro for free tier
        private boolean multiAz;  // false to save costs
        private String storageType;  // gp2 for General Purpose SSD
        private int storageSize;  // 20GB minimum
        private int maxConnections;  // 100 for t2.micro
    }

    @Data
    public static class Backup {
        private boolean enabled;
        private String schedule;  // Monthly backup
        private int retentionDays;  // 7 days minimum
        private String bucketName;
        private boolean useIamRole;  // true to save costs
    }

    @Data
    public static class Encryption {
        private boolean enabled;  // false to save costs
    }

    @Data
    public static class Monitoring {
        private boolean enabled;
        private int metricsInterval;  // 300 seconds (5 minutes)
        private int alertThresholdCpu;  // 90% to reduce alerts
        private int alertThresholdMemory;  // 90% to reduce alerts
    }

    @Data
    public static class Failover {
        private boolean enabled;  // false to save costs
        private String regionBackup;
        private String instanceType;  // db.t2.micro
    }

    @Data
    public static class Proxy {
        private boolean enabled;  // false to save costs
    }
}
