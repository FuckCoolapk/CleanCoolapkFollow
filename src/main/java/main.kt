import org.apache.commons.codec.net.URLCodec
import java.net.URLDecoder
import java.net.URLEncoder

fun main(args: Array<String>) {
    CoolapkConfig.uid = args[0]
    CoolapkConfig.userName = args[1]
    CoolapkConfig.token = args[2]
    val fansNumber = args[3].toInt()
    val isOperate = args[4].toBoolean()
    val coolapkService = ServiceCreator.create(CoolapkService::class.java)
    val pageTotalCount = (coolapkService.getUserDetail(CoolapkConfig.uid).execute().body()
        ?.getAsJsonObject("data")
        ?.get("follow")?.asInt)!! / 19 + 1
    var unFollowCount = 0
    for (i in 1..pageTotalCount) {
        val data =
            coolapkService.getFollowList(CoolapkConfig.uid, i.toString()).execute().body()?.getAsJsonArray("data")!!
        if (data.size()==0){
            break
        }else{
            println("------------------------------------------")
            println("正在对第 $i 页执行操作，共 $pageTotalCount 页（不准确）")
            for (j in 0 until data.size()) {
                val followObject = data.get(j).asJsonObject
                val fUserName = followObject.get("fusername").asString
                val fUID = followObject.get("fuid").asString
                val fUserAvatar = followObject.get("fUserAvatar").asString
                val fFans = followObject.getAsJsonObject("fUserInfo").get("fans").asInt
                val fVerifyStatus = followObject.getAsJsonObject("fUserInfo").get("verify_status").asInt
                if ((fUserName.contains("酷友") or (fUserAvatar == "http://avatar.coolapk.com/images/avatar_middle.gif") or (fFans<=fansNumber)) and (fVerifyStatus == 0)) {
                    if (isOperate) coolapkService.unFollow(fUID).execute()
                    unFollowCount++
                    println("已取消关注第 $unFollowCount 个关注者 名称：$fUserName UID：$fUID 跟随者：$fFans 认证状态：$fVerifyStatus 头像：$fUserAvatar")
                }else{
                    println("                      名称：$fUserName UID：$fUID 跟随者：$fFans 认证状态：$fVerifyStatus 头像：$fUserAvatar")
                }
            }
        }
        println("------------------------------------------\n")
    }
}
