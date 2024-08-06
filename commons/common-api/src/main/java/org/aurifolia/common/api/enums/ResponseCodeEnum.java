package org.aurifolia.common.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应码枚举
 *
 * @author danpeng
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {
    /**
     * 操作成功
     */
    SUCCESS("0", "success"),
    /**
     * 用户端错误
     */
    CLIENT_ERROR("C0000001", "client error"),
    /**
     * 用户未同意隐私协议
     */
    NOT_AGREE_PRIVACY_AGREEMENT("C0000002", "not agree privacy agreement"),
    /**
     * 注册国家或地区受限
     */
    REGISTRATION_RESTRICTED_IN_COUNTRIES_OR_REGIONS("C0000003", "registration restricted in countries or regions"),
    /**
     * 用户名已存在
     */
    USERNAME_ALREADY_EXISTS("C0000004", "username already exists"),
    /**
     * 用户名包含敏感词
     */
    USERNAME_CONTAIN_SENSITIVE_WORDS("C0000005", "username contain sensitive words"),
    /**
     * 用户名包含特殊字符
     */
    USERNAME_CONTAIN_SPECIAL_CHARACTERS("C0000006", "username contain special characters"),
    /**
     * 密码长度不够
     */
    PASSWORD_IS_NOT_LONG_ENOUGH("C0000007", "password is not long enough"),
    /**
     * 密码强度不够
     */
    INSUFFICIENT_PASSWORD_STRENGTH("C0000008", "insufficient password strength"),
    /**
     * 验证码输入错误
     */
    INCORRECT_CAPTCHA_INPUT("C0000009", "incorrect captcha input"),
    /**
     * 短信验证码输入错误
     */
    SMS_VERIFICATION_CODE_INPUT_ERROR("C0000010", "sms verification code input error"),
    /**
     * 邮件验证码输入错误
     */
    EMAIL_VERIFICATION_CODE_INPUT_ERROR("C0000011", "email verification code input error"),
    /**
     * 语音验证码输入错误
     */
    VOICE_VERIFICATION_CODE_INPUT_ERROR("C0000012", "voice verification code input error"),
    /**
     * 用户证件异常
     */
    ABNORMAL_USER_CREDENTIALS("C0000013", "abnormal user credentials"),
    /**
     * 用户证件类型未选择
     */
    CREDENTIALS_TYPE_IS_NOT_SELECTED("C0000014", "credentials type is not selected"),
    /**
     * 大陆身份证编号校验失败
     */
    MAINLAND_ID_NUMBER_VERIFICATION_FAILED("C0000015", "mainland id number verification failed"),
    /**
     * 护照编号校验失败
     */
    PASSPORT_NUMBER_VERIFICATION_FAILED("C0000016", "passport number verification failed"),
    /**
     * 军官证编号校验失败
     */
    MILITARY_ID_NUMBER_VERIFICATION_FAILED("C0000017", "military id number verification failed"),
    /**
     * 用户基本信息校验失败
     */
    USER_BASIC_INFORMATION_VALIDATION_FAILED("C0000018", "user basic information validation failed"),
    /**
     * 手机格式校验失败
     */
    PHONE_FORMAT_VALIDATION_FAILED("C0000019", "phone format validation failed"),
    /**
     * 地址格式校验失败
     */
    ADDRESS_FORMAT_VALIDATION_FAILED("C0000020", "address format validation failed"),
    /**
     * 邮箱格式校验失败
     */
    EMAIL_FORMAT_VALIDATION_FAILED("C0000021", "email format validation failed"),
    /**
     * 用户账号或密码错误
     */
    INCORRECT_USER_ACCOUNT_OR_PASSWORD("C0000022", "incorrect user account or password"),
    /**
     * 用户账户被冻结
     */
    USER_ACCOUNTS_ARE_FROZEN("C0000023", "user accounts are frozen"),
    /**
     * 用户账户已作废
     */
    USER_ACCOUNT_IS_INVALID("C0000024", "user account is invalid"),
    /**
     * 用户输入密码错误次数超限
     */
    USER_PASSWORD_ERROR_TIMES_EXCEEDED_THE_LIMIT("C0000025", "user password error times exceeded the limit"),
    /**
     * 用户身份校验失败
     */
    USER_IDENTITY_VALIDATION_FAILED("C0000026", "user identity validation failed"),
    /**
     * 用户指纹识别失败
     */
    USER_FINGERPRINTING_FAILED("C0000027", "user fingerprinting failed"),
    /**
     * 用户面容识别失败
     */
    USER_FACE_RECOGNITION_FAILED("C0000028", "user face recognition failed"),
    /**
     * 用户未获得第三方登录授权
     */
    USER_IS_NOT_AUTHORIZED_TO_LOGIN_WITH_THIRD_PARTY("C0000029", "user is not authorized to login with third party"),
    /**
     * 用户登录已过期
     */
    USER_LOGIN_HAS_EXPIRED("C0000030", "user login has expired"),
    /**
     * 用户验证码尝试次数超限
     */
    USER_AUTHENTICATION_CODE_IS_OVERTRIED("C0000031", "user authentication code is overtried"),
    /**
     * 访问权限异常
     */
    ACCESS_PERMISSION_EXCEPTION("C0000032", "access permission exception"),
    /**
     * 访问未授权
     */
    UNAUTHORIZED_ACCESS("C0000033", "unauthorized access"),
    /**
     * 用户授权申请被拒绝
     */
    USER_AUTHORIZATION_REQUEST_IS_DENIED("C0000034", "user authorization request is denied"),
    /**
     * 授权已过期
     */
    AUTHORIZATION_HAS_EXPIRED("C0000035", "authorization has expired"),
    /**
     * 用户访问被拦截
     */
    USER_ACCESS_IS_BLOCKED("C0000036", "user access is blocked"),
    /**
     * 服务已欠费
     */
    SERVICE_IS_OVERDUE("C0000037", "service is overdue"),
    /**
     * 用户签名异常
     */
    USER_SIGNATURE_EXCEPTION("C0000038", "user signature exception"),
    /**
     * 用户请求参数错误
     */
    USER_REQUEST_PARAMETER_ERROR("C0000039", "user request parameter error"),
    /**
     * 地址不在服务范围
     */
    ADDRESS_OUT_OF_SERVICE("C0000040", "address out of service"),
    /**
     * 时间不在服务范围
     */
    TIME_OUT_OF_SERVICE("C0000041", "time out of service"),
    /**
     * 金额超出限制
     */
    AMOUNT_OUT_OF_LIMIT("C0000042", "amount out of limit"),
    /**
     * 数量超出限制
     */
    QUANTITY_OUT_OF_LIMIT("C0000043", "quantity out of limit"),
    /**
     * 请求批量处理总个数超出限制
     */
    REQUEST_BATCHES_IS_OUT_OF_LIMIT("C0000044", "request batches is out of limit"),
    /**
     * 用户输入内容包含违禁敏感词
     */
    CONTENT_CONTAINS_PROHIBITED_SENSITIVE_WORDS("C0000045", "content contains prohibited sensitive words"),
    /**
     * 用户支付超时
     */
    USER_PAYS_TIMEOUT("C0000046", "user pays timeout"),
    /**
     * 确认订单超时
     */
    CONFIRM_ORDER_TIMEOUT("C0000047", "confirm order timeout"),
    /**
     * 订单已关闭
     */
    ORDER_IS_CLOSED("C0000048", "order is closed"),
    /**
     * 请求次数超出限制
     */
    REQUEST_COUNT_EXCEEDED_LIMIT("C0000049", "request count exceeded limit"),
    /**
     * 用户重复请求
     */
    REPEATED_USER_REQUESTS("C0000050", "repeated user requests"),
    /**
     * 账户余额不足
     */
    INSUFFICIENT_ACCOUNT_BALANCE("C0000051", "insufficient account balance"),
    /**
     * 用户配额不足
     */
    INSUFFICIENT_USER_QUOTA("C0000052", "insufficient user quota"),
    /**
     * 用户上传文件异常
     */
    USER_UPLOAD_FILE_EXCEPTION("C0000053", "user upload file exception"),
    /**
     * 用户上传文件类型不匹配
     */
    USER_UPLOAD_FILE_TYPE_MISMATCH("C0000054", "user upload file type mismatch"),
    /**
     * 用户上传文件太大
     */
    USER_UPLOAD_FILE_IS_TOO_LARGE("C0000055", "user upload file is too large"),
    /**
     * 用户不存在
     */
    USER_NOT_EXISTS("C0000056", "user not exists"),
    /**
     * 角色不存在
     */
    ROLE_NOT_EXISTS("C0000057", "role not exists"),
    /**
     * 权限不存在
     */
    PERM_NOT_EXISTS("C0000058", "perm not exists"),
    /**
     * 服务端出错
     */
    SERVICE_ERROR("S0000001", "service error"),
    /**
     * 服务执行超时
     */
    SERVICE_EXECUTION_TIMEOUT("S0000002", "service execution timeout"),
    /**
     * 服务容灾功能被触发
     */
    SERVICE_DISASTER_TOLERANCE_IS_TRIGGERED("S0000003", "service disaster tolerance is triggered"),
    /**
     * 服务限流
     */
    SERVICE_THROTTLING("S0000004", "service throttling"),
    /**
     * 服务功能降级
     */
    SERVICE_FUNCTION_DEGRADATION("S0000005", "service function degradation"),
    /**
     * 服务资源异常
     */
    SERVICE_RESOURCE_EXCEPTION("S0000006", "service resource exception"),
    /**
     * 系统磁盘耗尽
     */
    SYSTEM_DISK_EXHAUSTED("S0000007", "system disk exhausted"),
    /**
     * 系统内存耗尽
     */
    OUT_OF_SYSTEM_MEMORY("S0000008", "out of system memory"),
    /**
     * 文件句柄耗尽
     */
    FILE_HANDLE_IS_EXHAUSTED("S0000009", "file handle is exhausted"),
    /**
     * 系统连接池耗尽
     */
    SYSTEM_CONNECTION_POOL_IS_EXHAUSTED("S0000010", "system connection pool is exhausted"),
    /**
     * 系统线程池耗尽
     */
    SERVICE_THREAD_POOL_IS_EXHAUSTED("S0000011", "service thread pool is exhausted"),
    /**
     * 服务读取资源文件失败
     */
    SERVICE_FAILED_TO_READ_THE_RESOURCE_FILE("S0000012", "service failed to read the resource file"),
    /**
     * 调用第三方服务出错
     */
    ERROR_CALLING_THIRD_PARTY_SERVICE("T0000001", "error calling third party service"),
    /**
     * 中间件服务出错
     */
    MIDDLEWARE_SERVICE_ERROR("T0000002", "middleware service error"),
    /**
     * RPC服务出错
     */
    RPC_SERVICE_HAS_FAILED("T0000003", "rpc service has failed"),
    /**
     * RPC服务未找到
     */
    RPC_SERVICE_NOT_FOUND("T0000004", "rpc service not found"),
    /**
     * RPC服务未注册
     */
    RPC_SERVICE_IS_NOT_REGISTERED("T0000005", "rpc service is not registered"),
    /**
     * 消息服务出错
     */
    MESSAGE_SERVICE_ERROR("T0000006", "message service error"),
    /**
     * 消息投递出错
     */
    MESSAGE_DELIVERY_ERROR("T0000007", "message delivery error"),
    /**
     * 消息消费出错
     */
    MESSAGE_CONSUMPTION_ERROR("T0000008", "message consumption error"),
    /**
     * 消息订阅出错
     */
    MESSAGE_SUBSCRIPTION_ERROR("T0000009", "message subscription error"),
    /**
     * 消息分组未查到
     */
    MESSAGE_PACKET_WAS_NOT_FOUND("T0000010", "message packet was not found"),
    /**
     * 缓存服务出错
     */
    CACHE_SERVICE_ERROR("T0000011", "cache service error"),
    /**
     * 配置服务出错
     */
    CONFIGURATION_SERVICE_ERROR("T0000012", "configuration service error"),
    /**
     * 网络资源服务出错
     */
    NETWORK_RESOURCE_SERVICE_ERROR("T0000013", "network resource service error"),
    /**
     * VPN服务出错
     */
    VPN_SERVICE_FAILURE("T0000014", "vpn service failure"),
    /**
     * CDN服务出错
     */
    CDN_SERVICE_HAS_FAILED("T0000015", "cdn service has failed"),
    /**
     * 域名解析服务出错
     */
    DNS_FAILED("T0000016", "dns failed"),
    /**
     * 第三方系统执行超时
     */
    THIRD_PARTY_SYSTEM_TIMED_OUT("T0000017", "third party system timed out"),
    /**
     * RPC执行超时
     */
    RPC_EXECUTION_TIMES_OUT("T0000018", "rpc execution times out"),
    /**
     * 消息投递超时
     */
    MESSAGE_DELIVERY_HAS_TIMED_OUT("T0000019", "message delivery has timed out"),
    /**
     * 缓存服务超时
     */
    CACHE_SERVICE_TIMEOUT("T0000020", "cache service timeout"),
    /**
     * 配置服务超时
     */
    CONFIGURATION_SERVICE_TIMEOUT("T0000021", "configuration service timeout"),
    /**
     * 数据库服务超时
     */
    DATABASE_SERVICE_TIMEOUT("T0000022", "database service timeout"),
    /**
     * 数据库服务出错
     */
    DATABASE_SERVER_ERROR("T0000023", "database server error"),
    /**
     * 主键或唯一索引冲突
     */
    PRIMARY_KEY_OR_UNIQUE_INDEX_CONFLICT("T0000024", "primary key or unique index conflict"),
    /**
     * 第三方容灾系统被触发
     */
    THIRD_PARTY_DISASTER_TOLERANCE_SYSTEM_IS_TRIGGERED("T0000025", "third party disaster tolerance system is triggered"),
    /**
     * 第三方系统限流
     */
    THIRD_PARTY_SYSTEM_THROTTLING("T0000026", "third party system throttling"),

    /**
     * 第三方功能降级
     */
    DEGRADED_THIRD_PARTY_FEATURES("T0000027", "degraded third party features"),
    /**
     * 通知服务出错
     */
    NOTIFICATION_SERVICE_ERROR("T0000028", "notification service error"),
    /**
     * 短信提醒服务失败
     */
    SMS_REMINDER_SERVICE_FAILURE("T0000029", "sms reminder service failure"),
    /**
     * 语音提醒服务失败
     */
    VOICE_ALERT_SERVICE_FAILED("T0000030", "voice alert service failed"),
    /**
     * 邮件提醒服务失败
     */
    EMAIL_ALERT_SERVICE_FAILED("T0000031", "email alert service failed");

    /**
     * 响应码
     */
    private final String code;
    /**
     * 默认响应信息
     */
    private final String defaultMessage;
}
