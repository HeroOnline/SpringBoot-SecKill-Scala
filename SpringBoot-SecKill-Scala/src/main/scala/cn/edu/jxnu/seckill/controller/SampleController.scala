package cn.edu.jxnu.seckill.controller

import scala.language.implicitConversions
import scala.collection.JavaConversions._
import scala.collection.immutable.{ List => _, _ }
import java.util.{ List => JavaList }
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.ui.Model
import cn.edu.jxnu.seckill.dao.GoodsDao
import org.springframework.beans.factory.annotation.Autowired
import cn.edu.jxnu.seckill.vo.GoodsVo
import cn.edu.jxnu.seckill.result.Result
import cn.edu.jxnu.seckill.result.CodeMsg
import cn.edu.jxnu.seckill.service.GoodsService
import cn.edu.jxnu.seckill.domain.User
import cn.edu.jxnu.seckill.redis.RedisService
import cn.edu.jxnu.seckill.redis.key.UserKey

@RestController
@RequestMapping(Array("/sample"))
class SampleController @Autowired() (val goodsDao: GoodsDao, val goodsService: GoodsService, val redisService: RedisService) {

    /**
     * Hello World
     */
    @RequestMapping(Array("/hello"))
    def home() = {
        "Hello World!"
    }

    /**
     * 测试Thymeleaf
     */
    @RequestMapping(Array("/thymeleaf"))
    def thymeleaf(model: Model) = {
        model.addAttribute("name", "HiphopMan")
        "hello"
    }

    /**
     * 测试mybatis和druid
     */
    @RequestMapping(Array("/db/get"))
    def dbGet(): JavaList[GoodsVo] = {
        goodsDao.listGoodsVo()
    }

    /**
     * 测试返回成功
     */
    @RequestMapping(Array("/success"))
    def success(): Result[String] = {
        Result.success("测试成功啦")

    }

    /**
     * 测试返回失败
     */
    @RequestMapping(Array("/error"))
    def error(): Result[CodeMsg] = {
        Result.error(CodeMsg.SERVER_ERROR)
    }

    /**
     * 测试服务层写法
     * 并测试toString方法
     */
    @RequestMapping(Array("/service"))
    def service() = {
        for (g <- goodsService.listGoodsVo()) {
            println("商品视图对象toString方法=>" + g)
        }
        goodsService.listGoodsVo()
    }

    /**
     * 测试Redis
     */
    @RequestMapping(Array("/redis/get"))
    def redisGet(): Result[User] = {
        val user = redisService.get(UserKey.getById, "" + 1, classOf[User])
        return Result.success(user)
    }

    /**
     * 测试Redis
     */
    @RequestMapping(Array("/redis/set"))
    def redisSet(): Result[Boolean] = {
        val user = new User()
        user.setId(1)
        user.setName("1111")
        redisService.set(UserKey.getById, "" + 1, user) // UserKey:id1
        return Result.success(true)
    }

}