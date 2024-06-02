package org.aurifolia.ginkgo.common.core.constant;

/**
 * 返回码
 *
 * @author danpeng
 * @since 1.0
 */
public class ResultCode {
    /**
     * 操作成功
     */
    public static final String SUCCESS = "0";
    /**
     * 用户端错误
     */
    public static final String CLIENT_ERROR = "C0001";
    /**
     * 用户未同意隐私协议
     */
    public static final String NOT_AGREE_PRIVACY_AGREEMENT = "C0002";
    /**
     * 注册国家或地区受限
     */
    public static final String REGISTRATION_RESTRICTED_IN_COUNTRIES_OR_REGIONS = "C0003";
    /**
     * 用户名已存在
     */
    public static final String USERNAME_ALREADY_EXISTS = "C0004";
    /**
     * 用户名包含敏感词
     */
    public static final String USERNAME_CONTAIN_SENSITIVE_WORDS = "C0005";
    /**
     * 用户名包含特殊字符
     */
    public static final String USERNAME_CONTAIN_SPECIAL_CHARACTERS = "C0006";
    /**
     * 密码长度不够
     */
    public static final String PASSWORD_IS_NOT_LONG_ENOUGH = "C0007";
    /**
     * 密码强度不够
     */
    public static final String INSUFFICIENT_PASSWORD_STRENGTH = "C0008";
    /**
     * 验证码输入错误
     */
    public static final String INCORRECT_CAPTCHA_INPUT = "C0009";
    /**
     * 短信验证码输入错误
     */
    public static final String SMS_VERIFICATION_CODE_INPUT_ERROR = "C0010";
    /**
     * 邮件验证码输入错误
     */
    public static final String EMAIL_VERIFICATION_CODE_INPUT_ERROR = "C0011";
    /**
     * 语音验证码输入错误
     */
    public static final String VOICE_VERIFICATION_CODE_INPUT_ERROR = "C0012";
    /**
     * 用户证件异常
     */
    public static final String ABNORMAL_USER_CREDENTIALS = "C0013";
    /**
     * 用户证件类型未选择
     */
    public static final String CREDENTIALS_TYPE_IS_NOT_SELECTED = "C0014";
    /**
     * 大陆身份证编号校验失败
     */
    public static final String MAINLAND_ID_NUMBER_VERIFICATION_FAILED = "C0015";
    /**
     * 护照编号校验失败
     */
    public static final String PASSPORT_NUMBER_VERIFICATION_FAILED = "C0016";
    /**
     * 军官证编号校验失败
     */
    public static final String MILITARY_ID_NUMBER_VERIFICATION_FAILED = "C0017";
    /**
     * 用户基本信息校验失败
     */
    public static final String USER_BASIC_INFORMATION_VALIDATION_FAILED = "C0018";
    /**
     * 手机格式校验失败
     */
    public static final String PHONE_FORMAT_VALIDATION_FAILED = "C0019";
    /**
     * 地址格式校验失败
     */
    public static final String ADDRESS_FORMAT_VALIDATION_FAILED = "C0020";
    /**
     * 邮箱格式校验失败
     */
    public static final String EMAIL_FORMAT_VALIDATION_FAILED = "C0021";
    /**
     * 用户账号或密码错误
     */
    public static final String INCORRECT_USER_ACCOUNT_OR_PASSWORD = "C0022";
    /**
     * 用户账户被冻结
     */
    public static final String USER_ACCOUNTS_ARE_FROZEN = "C0023";
    /**
     * 用户账户已作废
     */
    public static final String USER_ACCOUNT_IS_INVALID = "C0024";
    /**
     * 用户输入密码错误次数超限
     */
    public static final String USER_PASSWORD_ERROR_TIMES_EXCEEDED_THE_LIMIT = "C0025";
    /**
     * 用户身份校验失败
     */
    public static final String USER_IDENTITY_VALIDATION_FAILED = "C0026";
    /**
     * 用户指纹识别失败
     */
    public static final String USER_FINGERPRINTING_FAILED = "C0027";
    /**
     * 用户面容识别失败
     */
    public static final String USER_FACE_RECOGNITION_FAILED = "C0028";
    /**
     * 用户未获得第三方登录授权
     */
    public static final String USER_IS_NOT_AUTHORIZED_TO_LOGIN_WITH_THIRD_PARTY = "C0029";
    /**
     * 用户登录已过期
     */
    public static final String USER_LOGIN_HAS_EXPIRED = "C0030";
    /**
     * 用户验证码尝试次数超限
     */
    public static final String USER_AUTHENTICATION_CODE_IS_OVERTRIED = "C0031";
    /**
     * 访问权限异常
     */
    public static final String ACCESS_PERMISSION_EXCEPTION = "C0032";
    /**
     * 访问未授权
     */
    public static final String UNAUTHORIZED_ACCESS = "C0033";
    /**
     * 用户授权申请被拒绝
     */
    public static final String USER_AUTHORIZATION_REQUEST_IS_DENIED = "C0034";
    /**
     * 授权已过期
     */
    public static final String AUTHORIZATION_HAS_EXPIRED = "C0035";
    /**
     * 用户访问被拦截
     */
    public static final String USER_ACCESS_IS_BLOCKED = "C0036";
    /**
     * 服务已欠费
     */
    public static final String SERVICE_IS_OVERDUE = "C0037";
    /**
     * 用户签名异常
     */
    public static final String USER_SIGNATURE_EXCEPTION = "C0038";
    /**
     * 用户请求参数错误
     */
    public static final String USER_REQUEST_PARAMETER_ERROR = "C0039";
    /**
     * 地址不在服务范围
     */
    public static final String ADDRESS_OUT_OF_SERVICE = "C0040";
    /**
     * 时间不在服务范围
     */
    public static final String TIME_OUT_OF_SERVICE = "C0041";
    /**
     * 金额超出限制
     */
    public static final String AMOUNT_OUT_OF_LIMIT = "C0042";
    /**
     * 数量超出限制
     */
    public static final String QUANTITY_OUT_OF_LIMIT = "C0043";
    /**
     * 请求批量处理总个数超出限制
     */
    public static final String REQUEST_BATCHES_IS_OUT_OF_LIMIT = "C0044";
    /**
     * 用户输入内容包含违禁敏感词
     */
    public static final String CONTENT_CONTAINS_PROHIBITED_SENSITIVE_WORDS = "C0045";
    /**
     * 用户支付超时
     */
    public static final String USER_PAYS_TIMEOUT = "C0046";
    /**
     * 确认订单超时
     */
    public static final String CONFIRM_ORDER_TIMEOUT = "C0047";
    /**
     * 订单已关闭
     */
    public static final String ORDER_IS_CLOSED = "C0048";
    /**
     * 请求次数超出限制
     */
    public static final String REQUEST_COUNT_EXCEEDED_LIMIT = "C0049";
    /**
     * 用户重复请求
     */
    public static final String REPEATED_USER_REQUESTS = "C0050";
    /**
     * 账户余额不足
     */
    public static final String INSUFFICIENT_ACCOUNT_BALANCE = "C0051";
    /**
     * 用户配额不足
     */
    public static final String INSUFFICIENT_USER_QUOTA = "C0052";
    /**
     * 用户上传文件异常
     */
    public static final String USER_UPLOAD_FILE_EXCEPTION = "C0053";
    /**
     * 用户上传文件类型不匹配
     */
    public static final String USER_UPLOAD_FILE_TYPE_MISMATCH = "C0054";
    /**
     * 用户上传文件太大
     */
    public static final String USER_UPLOAD_FILE_IS_TOO_LARGE = "C0055";
    /**
     * 用户不存在
     */
    public static final String USER_NOT_EXISTS = "C0056";
    /**
     * 角色不存在
     */
    public static final String ROLE_NOT_EXISTS = "C0057";
    /**
     * 权限不存在
     */
    public static final String PERM_NOT_EXISTS = "C0058";
    /**
     * 服务端出错
     */
    public static final String SERVICE_ERROR = "S0001";
    /**
     * 服务执行超时
     */
    public static final String SERVICE_EXECUTION_TIMEOUT = "S0002";
    /**
     * 服务容灾功能被触发
     */
    public static final String SERVICE_DISASTER_TOLERANCE_IS_TRIGGERED = "S0003";
    /**
     * 服务限流
     */
    public static final String SERVICE_THROTTLING = "S0004";
    /**
     * 服务功能降级
     */
    public static final String SERVICE_FUNCTION_DEGRADATION = "S0005";
    /**
     * 服务资源异常
     */
    public static final String SERVICE_RESOURCE_EXCEPTION = "S0006";
    /**
     * 系统磁盘耗尽
     */
    public static final String SYSTEM_DISK_EXHAUSTED = "S0007";
    /**
     * 系统内存耗尽
     */
    public static final String OUT_OF_SYSTEM_MEMORY = "S0008";
    /**
     * 文件句柄耗尽
     */
    public static final String FILE_HANDLE_IS_EXHAUSTED = "S0009";
    /**
     * 系统连接池耗尽
     */
    public static final String SYSTEM_CONNECTION_POOL_IS_EXHAUSTED = "S0010";
    /**
     * 系统线程池耗尽
     */
    public static final String SERVICE_THREAD_POOL_IS_EXHAUSTED = "S0011";
    /**
     * 服务读取资源文件失败
     */
    public static final String SERVICE_FAILED_TO_READ_THE_RESOURCE_FILE = "S0012";
    /**
     * 调用第三方服务出错
     */
    public static final String ERROR_CALLING_THIRD_PARTY_SERVICE = "T0001";
    /**
     * 中间件服务出错
     */
    public static final String MIDDLEWARE_SERVICE_ERROR = "T0002";
    /**
     * RPC服务出错
     */
    public static final String RPC_SERVICE_HAS_FAILED = "T0003";
    /**
     * RPC服务未找到
     */
    public static final String RPC_SERVICE_NOT_FOUND = "T0004";
    /**
     * RPC服务未注册
     */
    public static final String RPC_SERVICE_IS_NOT_REGISTERED = "T0005";
    /**
     * 消息服务出错
     */
    public static final String MESSAGE_SERVICE_ERROR = "T0006";
    /**
     * 消息投递出错
     */
    public static final String MESSAGE_DELIVERY_ERROR = "T0007";
    /**
     * 消息消费出错
     */
    public static final String MESSAGE_CONSUMPTION_ERROR = "T0008";
    /**
     * 消息订阅出错
     */
    public static final String MESSAGE_SUBSCRIPTION_ERROR = "T0009";
    /**
     * 消息分组未查到
     */
    public static final String MESSAGE_PACKET_WAS_NOT_FOUND = "T0010";
    /**
     * 缓存服务出错
     */
    public static final String CACHE_SERVICE_ERROR = "T0011";
    /**
     * 配置服务出错
     */
    public static final String CONFIGURATION_SERVICE_ERROR = "T0012";
    /**
     * 网络资源服务出错
     */
    public static final String NETWORK_RESOURCE_SERVICE_ERROR = "T0013";
    /**
     * VPN服务出错
     */
    public static final String VPN_SERVICE_FAILURE = "T0014";
    /**
     * CDN服务出错
     */
    public static final String CDN_SERVICE_HAS_FAILED = "T0015";
    /**
     * 域名解析服务出错
     */
    public static final String DNS_FAILED = "T0016";
    /**
     * 第三方系统执行超时
     */
    public static final String THIRD_PARTY_SYSTEM_TIMED_OUT = "T0017";
    /**
     * RPC执行超时
     */
    public static final String RPC_EXECUTION_TIMES_OUT = "T0018";
    /**
     * 消息投递超时
     */
    public static final String MESSAGE_DELIVERY_HAS_TIMED_OUT = "T0019";
    /**
     * 缓存服务超时
     */
    public static final String CACHE_SERVICE_TIMEOUT = "T0020";
    /**
     * 配置服务超时
     */
    public static final String CONFIGURATION_SERVICE_TIMEOUT = "T0021";
    /**
     * 数据库服务超时
     */
    public static final String DATABASE_SERVICE_TIMEOUT = "T0022";
    /**
     * 数据库服务出错
     */
    public static final String DATABASE_SERVER_ERROR = "T0023";
    /**
     * 主键或唯一索引冲突
     */
    public static final String PRIMARY_KEY_OR_UNIQUE_INDEX_CONFLICT = "T0024";
    /**
     * 第三方容灾系统被触发
     */
    public static final String THIRD_PARTY_DISASTER_TOLERANCE_SYSTEM_IS_TRIGGERED = "T0025";
    /**
     * 第三方系统限流
     */
    public static final String THIRD_PARTY_SYSTEM_THROTTLING = "T0026";
    /**
     * 第三方功能降级
     */
    public static final String DEGRADED_THIRD_PARTY_FEATURES = "T0027";
    /**
     * 通知服务出错
     */
    public static final String NOTIFICATION_SERVICE_ERROR = "T0028";
    /**
     * 短信提醒服务失败
     */
    public static final String SMS_REMINDER_SERVICE_FAILURE = "T0029";
    /**
     * 语音提醒服务失败
     */
    public static final String VOICE_ALERT_SERVICE_FAILED = "T0030";
    /**
     * 邮件提醒服务失败
     */
    public static final String EMAIL_ALERT_SERVICE_FAILED = "T0031";
}
