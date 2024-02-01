package org.aurifolia.ginkgo.commons.util;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 *
 * @author VNElinpe
 * @since 1.0
 **/
public class SpelUtil {
    private static final String ZERO_STR = "0";
    private static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("#.##%");
    private static final ExpressionParser PARSER = new SpelExpressionParser();
    private static final Pattern NUMBER_PATTERN = Pattern.compile("([+-]?\\d+\\.?\\d*%?)");

    static {
        PERCENT_FORMAT.setRoundingMode(RoundingMode.HALF_UP);
    }


    /**
     * 获取上下文
     *
     * @param data data
     * @return context
     */
    public static EvaluationContext genContext(Map<String, Object> data) {
        return new StandardEvaluationContext(new Entity(data));
    }

    /**
     * 计算表达式
     *
     * @param context context
     * @param expression expression
     * @return result
     */
    public static String evaluate(EvaluationContext context, String expression) {
        return PARSER.parseExpression(expression).getValue(context, String.class);
    }

    public static class Entity {
        private Map<String, Object> data;

        /**
         * Entity
         *
         * @param data data
         */
        public Entity(Map<String, Object> data) {
            this.data = data;
        }

        /**
         * 获取值
         *
         * @param key key
         * @return value
         */
        public String get(String key) {
            Object value = data.get(key);
            return value == null ? null : String.valueOf(value);
        }

        /**
         * 求和
         *
         * @param one one
         * @param another another
         * @return one + another
         */
        public String add(String one, String another) {
            return bigDecimal2String(formBigDecimal(one).add(formBigDecimal(another)));
        }

        /**
         * 减法
         *
         * @param minuend minuend
         * @param subtrahend subtrahend
         * @return minuend - subtrahend
         */
        public String sub(String minuend, String subtrahend) {
            return bigDecimal2String(formBigDecimal(minuend).subtract(formBigDecimal(subtrahend)));
        }

        /**
         * 乘法
         *
         * @param multiplicand multiplicand
         * @param multiplier multiplier
         * @return multiplicand * multiplier
         */
        public String mul(String multiplicand, String multiplier) {
            return bigDecimal2String(formBigDecimal(multiplicand).multiply(formBigDecimal(multiplier)));
        }

        /**
         * 除法
         *
         * @param dividend dividend
         * @param divisor divisor
         * @return dividend / divisor
         */
        public String div(String dividend, String divisor) {
            BigDecimal divisorDecimal = formBigDecimal(divisor);
            if (divisorDecimal.equals(BigDecimal.ZERO)) {
                return ZERO_STR;
            }
            return bigDecimal2String(formBigDecimal(dividend).divide(formBigDecimal(divisor), 5, RoundingMode.HALF_UP));
        }

        /**
         * 转百分数(带百分号)
         *
         * @param str str
         * @return percent
         */
        public String toPercent(String str) {
            return PERCENT_FORMAT.format(formBigDecimal(str));
        }

        /**
         * 从字符串种分离出数字
         *
         * @return 数字，可能带百分号
         */
        public String splitNumber(String str, int index) {
            if (!StringUtils.hasText(str)) {
                return null;
            }
            Matcher matcher = NUMBER_PATTERN.matcher(str);
            int loc = 0;
            String data = null;
            while (matcher.find()) {
                data = matcher.group();
                if (loc++ == index) {
                    break;
                }
            }
            return data;
        }

        private BigDecimal formBigDecimal(String str) {
            return StringUtils.hasText(str) ? new BigDecimal(str) : BigDecimal.ZERO;
        }

        private String bigDecimal2String(BigDecimal bigDecimal) {
            return bigDecimal.stripTrailingZeros().toString();
        }

    }
}
