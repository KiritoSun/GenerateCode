package com.zt;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zt.zhao
 * @since 2019-1-14
 */
public class Generator {
    /**
     * 全局变量
     */
    private static final String PATH = "C:\\codeGen\\demo\\user";
    private static final String AUTHOR = "zt.zhao";
    private static final String URL = "jdbc:mysql://localhost:3306/demo?useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "abc123";
    private static final String TABLE = "user";

    /**
     * 程序执行主函数
     * @param args 参数
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig gc = new GlobalConfig()
                .setOutputDir(PATH + "/src/main/java")
                .setAuthor(AUTHOR)
                .setOpen(false);
        mpg.setGlobalConfig(gc);
        DataSourceConfig dsc = new DataSourceConfig()
                .setUrl(URL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername(USER_NAME)
                .setPassword(PASSWORD);
        mpg.setDataSource(dsc);
        final PackageConfig pc = new PackageConfig();
        pc.setParent("com.zt");
        mpg.setPackageInfo(pc);
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        String templatePath = "/templates/mapper.xml.ftl";
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return PATH + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        TemplateConfig templateConfig = new TemplateConfig()
                .setXml(null);
        mpg.setTemplate(templateConfig);
        StrategyConfig strategy = new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setInclude(TABLE)
                .setSuperEntityColumns("id")
                .setControllerMappingHyphenStyle(true)
                .setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
