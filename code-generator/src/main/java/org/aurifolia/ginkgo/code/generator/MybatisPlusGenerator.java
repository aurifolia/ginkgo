package org.aurifolia.ginkgo.code.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collections;

/**
 * Mybatis-Plus代码生成器
 *
 * @author VNElinpe
 * @since 1.0
 **/
public class MybatisPlusGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/gk_user?useSSL=true&serverTimezone=UTC";
        String username = "temp";
        String password = "123";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("VNElinpe") // 设置作者
                            .commentDate("y/M/d")
                            .disableOpenDir()
                            .outputDir("D:\\Workspace\\Java\\idea\\product\\ginkgo\\user-service\\src\\main\\java"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    int length = metaInfo.getLength();
//                    // tinyint(1) --> Boolean
//                    if (typeCode == Types.SMALLINT && length == 1) {
//                        return DbColumnType.BOOLEAN;
//                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("org.aurifolia.ginkgo") // 设置父包名
                            .moduleName("user") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\Workspace\\Java\\idea\\product\\ginkgo\\user-service\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(".*") // 设置需要生成的表名
                            .entityBuilder()
//                            .enableFileOverride()
                            .enableRemoveIsPrefix()
                            .enableLombok()
                            .serviceBuilder()
//                            .enableFileOverride()
                            .mapperBuilder()
                            .enableBaseColumnList()
                            .enableBaseResultMap()
                            .mapperAnnotation(Mapper.class)
//                            .enableFileOverride()
                            .controllerBuilder()
//                            .enableFileOverride()
                            .enableRestStyle();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
