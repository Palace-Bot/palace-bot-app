package org.github.palace.bot.builder

import net.mamoe.mirai.utils.BotConfiguration
import java.io.File

/**
 * BotConfiguration配置
 * {@see <a href="https://github.com/mamoe/mirai/blob/dev/docs/Bots.md#1">BotConfiguration Config</a>}
 * @author JHY
 * @date 2022/3/22 10:53
 */
class BotConfigurationBuilder private constructor() {

    companion object {
        @JvmStatic
        fun builder(): BotConfiguration {
            return BotConfiguration {
                run {
                    // 心跳策略
                    heartbeatStrategy = BotConfiguration.HeartbeatStrategy.REGISTER
                    // 登录协议
                    protocol = BotConfiguration.MiraiProtocol.ANDROID_PHONE
                    // 运行目录
                    workingDir = File("./mirai")
                    // 缓存目录
                    cacheDir = File("cache")

                    // TODO 默认设备信息
                    fileBasedDeviceInfo("myDeviceInfo.json") // 存储为 "myDeviceInfo.json"

                    // 重定向日志 Bot / Net
                    redirectBotLogToFile()
                    redirectBotLogToDirectory()
                    redirectNetworkLogToFile()
                    redirectNetworkLogToDirectory()

                    contactListCache {
                        // 开启好友列表缓存
                        friendListCacheEnabled = true
                        // 开启群成员列表缓存
                        groupMemberListCacheEnabled = true
                        // 可选设置有更新时的保存时间间隔, 默认 60 秒
                        saveIntervalMillis = 60_000
                    }
                }
            }
        }
    }
}