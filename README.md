## Description

Ginkgo is a developement framework based on spring cloud. It contains many services, and other business systems can be
developed on this basis to improve development efficiency.

## Service partition

| Service name    | Port        | Description                                                          |
|-----------------|-------------|----------------------------------------------------------------------|
| common-all      | NA          | common module, including common-cache、common-core、common-logging     |
| common-cache    | NA          | common cache module，including some cache-related tools               |
| common-core     | NA          | common core module, including some data model, constants, and so on. |
| common-logging  | NA          | common logging module                                                |
| gateway-service | 60001-60099 | gateway service                                                      |
| user-service    | 60101-60199 | user service                                                         |
| product-service | 60201-60299 | product service                                                      |
| order-service   | 60301-60399 | order service                                                        |

## Result code partition

| Service name | Result code range             |
|--------------|-------------------------------|
| common       | \[CST\]0000000-\[CST\]0009999 |
| user-service | \[CST\]0010000-\[CST\]0019999 |

## Service deployment environment variable

## Pipeline environment variable

| Variable name        | Description                                                          |
|----------------------|----------------------------------------------------------------------|
| SERVICE_NAME         | service name(camel-case)                                             |
| SERVICE_REPLICAS     | number of service instances                                          |
| SERVICE_PORT         | service port                                                         |
| SERVICE_TLS_KEY      | Service Ingress Certificate key(base64)                              |
| SERVICE_TLS_CRT      | Service Ingress certificate(base64)                                  |
| SERVICE_DOMAIN       | Service Domain name associated with the https certificate on Ingress |
| SERVICE_HOST         | the host name of the service Ingress                                 |
| SERVICE_URI_PREFIX   | path prefix of the Ingress service                                   |
| KUBERNETES_NAMESPACE | name of the kubernetes namespace to which the service belongs        |
| SONAR_HOST_URL       | sonar url                                                            |
| SONAR_TOKEN          | sonar token                                                          |

## Runtime environment variable

| Variable name      | Description                                              |
|--------------------|----------------------------------------------------------|
| LOG_FILE_PATH      | log saving path(default /var/log)                        |
| LOG_FILE_BASE_NAME | log filename(default application)                        |
| LOG_FILE_MAX_SIZE  | the maximum capacity of a single log file(default 200MB) |
| LOG_FILE_MAX_COUNT | the number of reserved log files(default 30)             |
| LOG_LEVEL          | log level(default INFO)                                  |
