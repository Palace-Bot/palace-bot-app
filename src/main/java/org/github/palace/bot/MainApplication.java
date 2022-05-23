package org.github.palace.bot;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import org.github.palace.bot.builder.BotConfigurationBuilder;
import org.github.palace.bot.core.EventDispatcher;
import org.github.palace.bot.core.plugin.CommandManager;
import org.github.palace.bot.core.plugin.DefaultPluginManager;
import org.github.palace.bot.core.plugin.Loc;
import org.github.palace.bot.core.plugin.PluginManager;

import static org.github.palace.bot.constant.BaseConstant.*;

/**
 * @author JHY
 * @date 2022/3/22 10:38
 */
public class MainApplication {
    public static void main(String[] args) {

        Bot bot = BotFactory.INSTANCE.newBot(QQ, PASSWORD, BotConfigurationBuilder.builder());
        bot.login();

        // 初始化管理器
        PluginManager pluginManager = new DefaultPluginManager("plugins");

        Loc.put(PluginManager.class, pluginManager);
        Loc.put(Bot.class, bot);

        pluginManager.load();
        pluginManager.start();


        EventDispatcher eventDispatcher = new EventDispatcher();
        eventDispatcher.start();

        // jvm关闭钩子函数
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // 关闭事件
            eventDispatcher.stop();
            // 关闭插件
            pluginManager.stop();
        }));
    }

}
