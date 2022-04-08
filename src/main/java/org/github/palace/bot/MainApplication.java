package org.github.palace.bot;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import org.github.palace.bot.builder.BotConfigurationBuilder;
import org.github.palace.bot.core.EventDispatcher;
import org.github.palace.bot.core.cli.support.CommandManagerFactory;
import org.github.palace.bot.core.cli.support.DefaultCommandManager;

import static org.github.palace.bot.constant.BaseConstant.*;

/**
 * @author JHY
 * @date 2022/3/22 10:38
 */
public class MainApplication {
    public static void main(String[] args) {
        Bot bot = BotFactory.INSTANCE.newBot(QQ, PASSWORD, BotConfigurationBuilder.builder());
        bot.login();

        EventDispatcher eventDispatcher = new EventDispatcher();
        eventDispatcher.start();

        // 初始化命令管理器
        CommandManagerFactory.setCommandManager(new DefaultCommandManager(COMMAND_PREFIX, bot));
        // 启动命令推送
        CommandManagerFactory.instance().startCommandPush();

        // jvm关闭钩子函数
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // 关闭事件
            eventDispatcher.stop();
            // 关闭命令推送
            CommandManagerFactory.instance().stopCommandPush();
        }));
    }

}
