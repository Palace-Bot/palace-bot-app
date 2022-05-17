package org.github.palace.bot.constant;

import org.github.palace.bot.MainApplication;
import org.github.palace.bot.core.io.YamlLoader;

import static org.github.palace.bot.utils.TypeUtil.cast;

/**
 * @author JHY
 * @date 2022/3/23 21:16
 */
public final class BaseConstant {

    public static final Long QQ;
    public static final String PASSWORD;
    public static final String COMMAND_PREFIX;
    static {
        QQ = cast(YamlLoader.loadYamlNames("bot.base.qq", MainApplication.class.getClassLoader()), Long.class);
        PASSWORD = cast(YamlLoader.loadYamlNames("bot.base.password", MainApplication.class.getClassLoader()), String.class);
        COMMAND_PREFIX = cast(YamlLoader.loadYamlNames("bot.base.command-prefix", MainApplication.class.getClassLoader()), String.class);
    }

    private BaseConstant() {
    }

}
