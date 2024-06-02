# 简述

Ginkgo是一个微服务系统，内置了很多功能，其它业务系统可在此基础上进行开发，提高开发效率。

# 服务划分

| 服务名             | 占用端口        | 描述                                             |
|-----------------|-------------|------------------------------------------------|
| common-all      | NA          | 通用模块，包含common-cache、common-core、common-logging |
| common-cache    | NA          | 缓存模块，内置一些缓存相关的工具                               |
| common-core     | NA          | 核心模块，内置一些数据模型、常量和通用配置                          |
| common-logging  | NA          | 日志模块，统一管理日志                                    |
| user-service    | 60101-60199 | 用户服务                                           |
| product-service | 60201-60299 | 商品服务                                           |
| order-service   | 60301-60399 | 订单服务                                           |
| gateway-service | 60001-60099 | 网关服务                                           |

# 服务部署环境变量

## 流水线变量

| 变量名                  | 描述                      |
|----------------------|-------------------------|
| SERVICE_NAME         | 服务名(camel-case)         |
| SERVICE_REPLICAS     | 服务实例数                   |
| SERVICE_PORT         | 服务端口                    |
| SERVICE_TLS_KEY      | 服务Ingress的证书key(base64) |
| SERVICE_TLS_CRT      | 服务Ingress的证书(base64)    |
| SERVICE_DOMAIN       | 服务Ingress上https证书关联的域名  |
| SERVICE_HOST         | 服务Ingress的主机名           |
| SERVICE_URI_PREFIX   | 服务Ingress的路径前缀          |
| KUBERNETES_NAMESPACE | 服务所属kubernetes的命名空间名称   |

## 运行环境变量

| 变量名                | 描述                   |
|--------------------|----------------------|
| LOG_FILE_PATH      | 日志保存路径(默认/var/log)   |
| LOG_FILE_BASE_NAME | 日志文件名(默认application) |
| LOG_FILE_MAX_SIZE  | 单个日志文件的最大容量(默认200MB) |
| LOG_FILE_MAX_COUNT | 保留的日志文件个数(默认30)      |
| LOG_LEVEL          | 日志级别(默认INFO)         |
