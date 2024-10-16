package com.monitor.domain.monitorpkg.converter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import com.monitor.domain.monitorpkg.model.AbstractMonitorPkg;
import com.monitor.domain.monitorpkg.model.MonitorPkgFactory;

@Service
public class MonitorPkgConverter implements IMonitorPkgConverter {
    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorPkgConverter.class);

    /**
     * convert raw yml file to monitor pkg.
     */
    @Override
    public AbstractMonitorPkg convertRawYmlFileToMonitorPkg(String rawFile) throws Exception {
        if (StringUtils.isBlank(rawFile)) {
            return null;
        }

        Yaml yaml = new Yaml();
        Map<String, Object> map = yaml.load(rawFile);

        String backend = (String) map.get("backend");
        Class<? extends AbstractMonitorPkg> cls = MonitorPkgFactory.getMonitorPkgByBackend(backend);
        Constructor<? extends AbstractMonitorPkg> con = cls.getConstructor();
        AbstractMonitorPkg pkg = con.newInstance();
        Field[] fields = FieldUtils.getAllFields(cls);
        for (Field field: fields) {
            String fieldName = field.getName();
            Object value = map.get(fieldName);
            FieldUtils.writeField(pkg, fieldName, value, true);
        }

        if (pkg.validPkg()) {
            return pkg;
        } else {
            return null;
        }
    }
}
